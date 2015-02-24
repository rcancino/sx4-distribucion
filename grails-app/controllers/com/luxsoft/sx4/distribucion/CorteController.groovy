package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario

@Transactional(readOnly = true)
@Secured(["hasAnyRole('CORTADOR')"])
class CorteController {

	def importacionService

    def index() { }

    def pendientes(int max){
    	params.max = Math.min(max ?: 10, 100)
        params.sort='pedido'
        params.order='asc'
        /*
        def query=Corte.where{empacadoFin==null}
        respond query.list(params), model:[corteInstanceCount:query.count()]
        */
        def list=Corte.findAll("from Corte c where c.empacadoFin=null and c.surtidoDet.surtido.asignado!=null")
        respond list, model:[corteInstanceCount:list.size()]
    }

    @Transactional
    def importar(ImportarCorteCommand cmd){
    	//def fecha=params.toDate('yyyy-mm-dd')
    	if(cmd.validate()){
			importacionService.importarCortes(fecha)
    	}else{
    		flash.message="Se requiere una fecha valida para la importacion de cortes"
    	}
    	log.info 'Importando cortes :'+fecha?.format('dd/MM/yyyy')
        log.info 'Params: '+params
    	redirect action:'pendientes'
    }

    @NotTransactional
    def iniciarCorte(Corte corte){
      
      def cortador=getAuthenticatedUser()
      assert cortador,'No esta firmado al sistema'
      assert validarOperacionDeCortado(),'El sistema esta registrado sin rol de CORTADOR'
      assert corte.statusCorte=='PENDIENTE'

    	String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operación"
        redirect action:'pendientes'
        return
      }
      def surtidor=Usuario.findByNip(nip)
      if(!surtidor){
        flash.error="Operador no encontrado verifique su NIP "
        redirect action:'pendientes'
        return 
      }

      if(surtidor.username!=corte.surtidor){
        flash.error= "Esta partida solo la puede entregar a corte: $corte.surtidor no por $surtidor.username"  
        redirect action:'pendientes'
        return 
      }

      corte.asignado=cortador.username
      corte.inicio=new Date()
      corte.save(flush:true)
      event('corteAsignado', corte)
      log.info "Producto  $corte.producto entregado  a  $cortador.username "
      flash.success= "Producto  $corte.producto entregado  a  $cortador.username "  
      redirect action:'pendientes'
    }


    @Transactional
    def terminarCorte(Corte corte){
      assert corte, 'Corte nulo no puede ser terminado'
      assert corte.statusCorte=='EN CORTE','Corte con estatus incorrecto'
      
      String nip=params.nip

      if(!nip){
        flash.error="Digite su NIP para terminar el corte"
        redirect action:'pendientes'
        return
      }
      def cortador=getAuthenticatedUser()

      if(cortador.nip==nip){
          corte.fin=new Date()
          corte.save(flush:true)
          log.info "Corte terminado para  $corte.producto entregado por:  $cortador.nombre "
          flash.success= "Corte terminado para  $corte.producto. Entregado por:  $cortador.nombre " 
      }else{
          flash.error="NIP incorrecto"
      }
      redirect action:'pendientes'
    }


    @Transactional
    def iniciarEmpacado(Corte corte){

      assert !corte.empacador,'Corte ya asignado para empaque'
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operación"
        redirect action:'pendientes'
        return
      }
      def empacador=Usuario.findByNip(nip)
      if(!empacador){
        flash.error="Empacador no encontrado verifique su NIP "
        redirect action:'pendientes'
        return 
      }
      if(empacador.getAuthorities().find{it.authority=='EMPACADOR'}){
        corte.empacador=empacador.username
        corte.empacadoInicio=new Date()
        corte.save(flush:true)
        event('empacadoIniciado', corte)
        log.info "Empacado de  $corte.producto asignado a:  $empacador.username "
        flash.success= "Empacado de  $corte.producto asignado a:  $empacador.username "
      }else{
        flash.error="No tiene el ROL de EMPACADOR verifique su NIP "
        
      }
      redirect action:'pendientes'
    }

    @Transactional
    def terminarEmpacado(Corte corte){
      
      def cortador=getAuthenticatedUser()
      assert cortador,'No esta firmado al sistema'
      assert validarOperacionDeCortado(),'El sistema esta registrado sin rol de CORTADOR'
      assert corte.statusEmpaque=='EN EMPACADO','Corte  no esta EN EMPACADO'

      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operación"
        redirect action:'pendientes'
        return
      }
      def empacador=Usuario.findByNip(nip)
      if(!empacador){
        flash.error="Empacador no encontrado verifique su NIP "
        redirect action:'pendientes'
        return 
      }
      if(!empacador.getAuthorities().find{it.authority=='EMPACADOR'}){
        flash.error="No tiene el ROL de EMPACADOR verifique su NIP "
        redirect action:'pendientes'
        return 
      }
      if(empacador.username!=corte.empacador){
          flash.error="Este corte solo puede ser terminado el empaque por $corte.empacador"
          redirect action:'pendientes'
          return 
        }
      corte.empacadoFin=new Date()
      corte.save(flush:true)
      event('empacadoTerminado', corte.surtidoDet.surtido.id)
      log.info "Empacado terminado para $corte.producto  "
      flash.success= "Empacado terminado para $corte.producto  "
      redirect action:'pendientes'
    }

    private  boolean validarOperacionDeCortado(){
       return getAuthenticatedUser().getAuthorities().find{it.authority=='CORTADOR'}
    }

}

class ImportarCorteCommand{
	Date fecha
}