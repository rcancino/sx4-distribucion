package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
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
        def query=Corte.where{fin==null}
        respond query.list(params), model:[corteInstanceCount:query.count()]
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

    @Transactional
    def asignar(Corte corte){
    	String nip=params.nip
      if(!nip){
        flash.brodcast="Digite su NIP para asignar pedido $surtido.pedido"
        redirect action:'pendientes'
        return
      }
      def user=Usuario.findByNip(nip)
      if(!user){
        flash.brodcast="Operador no encontrado verifique su NIP "
        redirect action:'pendientes'
        return 
      }
      corte.asignado='OPERADOR DE CORTE'
      corte.inicio=new Date()
      corte.save(flush:true,failOnError:true)
      log.info "Producto  $corte.producto entregado  a  $corte.asignado para corte "
      flash.brodcast= "Producto  $corte.producto entregado  a  $corte.asignado para corte "
      redirect action:'pendientes'
    }
}

class ImportarCorteCommand{
	Date fecha
}
