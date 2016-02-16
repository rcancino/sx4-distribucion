package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('CORTADOR')"])
class CorteController {

  def importacionService

  def corteService

    @Secured(["hasAnyRole('GERENTE')"])
    def index() { 
    }

    def pendientes(int max){
      params.max = Math.min(max ?: 10, 100)
        params.sort='pedido'
        params.order='asc'
        /*
        def query=Corte.where{empacadoFin==null}
        respond query.list(params), model:[corteInstanceCount:query.count()]
        */
        def list=Corte.findAll("from Corte c where c.fin=null and c.surtidoDet.surtido.asignado!=null and c.surtidoDet.surtido.cancelado=null")
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

    @Transactional
    def entregarACorte(Corte corte){
      
      def cortador=getAuthenticatedUser()
      assert cortador,'No esta firmado al sistema'
      assert validarOperacionDeCortado(),'El sistema esta registrado sin rol de CORTADOR'
      assert corte.statusCorte=='PENDIENTE'

      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operacin"
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
      corte.asignacion=new Date()
     corteService.registrarAsignacionDeCorteEnSurtido corte

      corte.save(flush:true)
      event('surtidoEntregadoACorte', corte)

      if(params.cortes){
        def adicionales=params.cortes.findAll({it.toLong()!=corte.id})
        adicionales.each{
          def ca=Corte.get(it)
          if(ca.surtidor==corte.surtidor ){
              ca.asignado=cortador.username
              ca.asignacion=new Date()
              ca.save(flush:true)
              event('surtidoEntregadoACorte', ca)
          }
        }
        //print 'Surtidos adicionales: '+adicionales
      }
      log.info "Producto  $corte.producto entregado  a  $cortador.username "
      flash.success= "Producto  $corte.producto entregado  a  $cortador.username "  
      redirect action:'pendientes'
    }

    @Transactional
    def iniciarCorte(Corte corte){
      
      def cortador=getAuthenticatedUser()
      assert cortador,'No esta firmado al sistema'
      assert validarOperacionDeCortado(),'El sistema esta registrado sin rol de CORTADOR'
      assert corte.statusCorte=='PENDIENTE'

      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operacin"
        redirect action:'pendientes'
        return
      }
     

      if(nip!=cortador.nip){
        flash.error= "Esta partida solo la puede cortada por: $corte.asignado verifique su NIP"  
        redirect action:'pendientes'
        return 
      }
      corte.inicio=new Date()
      corte.empacadoInicio=corte.inicio
      corte.save(flush:true)
      //Actualizando el inicio del corte en surtido
      corteService.registrarInicioDeCorteEnSurtido corte
      //event('corteIniciado', corte)
      if(params.cortes){
        def adicionales=params.cortes.findAll({it.toLong()!=corte.id})
        adicionales.each{
          def ca=Corte.get(it)
          if(ca.asignado==corte.asignado && (ca.inicio==null) ){
              ca.inicio=new Date()
              ca.empacadoInicio=corte.inicio
              ca.save(flush:true)
              //event('corteIniciado', ca)
              corteService.registrarInicioDeCorteEnSurtido ca
          }
        }
        //print 'Surtidos adicionales: '+adicionales
      }
      
      log.info " Corte de producto  $corte.producto iniciado por:$cortador.username "
      flash.success=  " Corte de producto  $corte.producto iniciado por:$cortador.username " 
      redirect action:'pendientes'
    }

    @Transactional
    def iniciarCorte2(Corte corte){
      //println 'Inicinado corte: '+params
      
      def cortador=getAuthenticatedUser()
      assert cortador,'No esta firmado al sistema'
      assert validarOperacionDeCortado(),'El sistema esta registrado sin rol de CORTADOR'
      assert corte.statusCorte=='PENDIENTE'
      
      corte.inicio=new Date()
      corte.empacadoInicio=corte.inicio
      corte.asignado=cortador.username
      corte.save(flush:true)
      //Actualizando el inicio del corte en surtido
      corteService.registrarInicioDeCorteEnSurtido corte
      //event('corteIniciado', corte)
      if(params.cortes){
        
        def adicionales=params.cortes.findAll({it.toLong()!=corte.id})
        adicionales.each{

          def ca=Corte.get(it)
          if(ca.asignado==corte.asignado && (ca.inicio==null) ){
              ca.inicio=new Date()
              ca.empacadoInicio=corte.inicio
              ca.asignado=cortador.username
              ca.save(flush:true)
              //event('corteIniciado', ca)
              corteService.registrarInicioDeCorteEnSurtido ca
          }
        }
        //print 'Surtidos adicionales: '+adicionales
      }
      
      log.info " Corte de producto  $corte.producto iniciado por:$cortador.username "
      flash.success=  " Corte de producto  $corte.producto iniciado por:$cortador.username " 
      
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
          //event('corteTerminado', corte)
          
          

          log.info "Corte terminado para  $corte.producto entregado por:  $cortador.nombre "
          flash.success= "Corte terminado para  $corte.producto. Entregado por:  $cortador.nombre " 

          if(params.cortes){
            def adicionales=params.cortes.findAll({it.toLong()!=corte.id})
              adicionales.each{
              def ca=Corte.get(it)
              if(ca.asignado==corte.asignado && (ca.fin==null) ){
                  ca.fin=new Date()
                  ca.save(flush:true)
                  corteService.registrarFinDeCorteEnSurtido(ca)
                  //event('corteTerminado', ca)
              }
            }
            //print 'Surtidos adicionales: '+adicionales
          }
      }else{
          flash.error="NIP incorrecto"
      }
      redirect action:'pendientes'
    }

    @Transactional
    def terminarCorte2(Corte corte){
      assert corte, 'Corte nulo no puede ser terminado'
      assert corte.statusCorte=='EN CORTE','Corte con estatus incorrecto'
      def cortador=getAuthenticatedUser()
      corte.fin=new Date()
      //corte.asignado=cortador.username
      corte.save(flush:true)

      log.info "Corte terminado para  $corte.producto entregado por:  $cortador.nombre "
      flash.success= "Corte terminado para  $corte.producto. Entregado por:  $cortador.nombre " 

      if(params.cortes){ // cortes adicionales
        def adicionales=params.cortes.findAll({it.toLong()!=corte.id})
        adicionales.each{
          def ca=Corte.get(it)
          if(ca.asignado==corte.asignado && (ca.fin==null) ){
            ca.fin=new Date()
            //ca.asignado=cortador.username
            ca.save(flush:true)
            //println 'Registrando fin de corte.....'
            //corteService.registrarFinDeCorteEnSurtido(ca)
                  //event('corteTerminado', ca)
          }
        }
            //print 'Surtidos adicionales: '+adicionales
      }
      def surt=corte.surtidoDet.surtido
      def cortesPend= surt.partidas.findAll{it.corte!=null && it.corte.fin==null}
       cortesPend.each{
         println"Corte"+it
       }
      if(!cortesPend){
        println "Ya  no hay mas cortes pendientes"
        corteService.registrarFinDeCorteEnSurtido(corte)
      }else
      {
        println "Todavia hay cortes pendientes"
      }
      redirect action:'pendientes'
    }


 @Transactional
    def iniciarCorteGlobal(Corte corte){
      //println 'Inicinado corte: '+params
      
      def cortador=getAuthenticatedUser()
      assert cortador,'No esta firmado al sistema'
      assert validarOperacionDeCortado(),'El sistema esta registrado sin rol de CORTADOR'
      assert corte.statusCorte=='PENDIENTE'
      
      
      def surt=corte.surtidoDet.surtido
      def parts= surt.partidas.findAll{it.corte!=null && it.corte.inicio==null}
       parts.each{
          it.corte.inicio=new Date()
          it.corte.empacadoInicio=corte.inicio
          
          if(!it.corte.asignado){
            it.corte.asignado=cortador.username
            it.corte.asignacion=new Date()
          }
          it.corte.asignado=cortador.username

          it.corte.save(flush:true)

       }
    
      //Actualizando el inicio del corte en surtido

      corteService.registrarAsignacionDeCorteEnSurtido corte
      corteService.registrarInicioDeCorteEnSurtido corte

      
      
      log.info " Corte de producto  $corte.producto iniciado por:$cortador.username "
      flash.success=  " Corte de producto  $corte.producto iniciado por:$cortador.username " 
      
      redirect action:'pendientes'
    }

     @Transactional
    def terminarCorteGlobal(Corte corte){
      assert corte, 'Corte nulo no puede ser terminado'
      assert corte.statusCorte=='EN CORTE','Corte con estatus incorrecto'
      def cortador=getAuthenticatedUser()
     


      def surt=corte.surtidoDet.surtido
      def parts= surt.partidas.findAll{it.corte!=null && it.corte.fin==null}
       parts.each{
         it.corte.fin=new Date()
          //corte.asignado=cortador.username
          it.corte.save(flush:true)
       }

      corteService.registrarFinDeCorteEnSurtido(corte)

      log.info "Corte terminado para  $corte.producto entregado por:  $cortador.nombre "
      flash.success= "Corte terminado para  $corte.producto. Entregado por:  $cortador.nombre " 

      
     
      redirect action:'pendientes'
    }
    

    @Transactional
    def iniciarEmpacado(Corte corte){

      assert !corte.empacador,'Corte ya asignado para empaque'
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operacin"
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
    @Secured(['permitAll'])
    def terminarEmpacado(Corte corte){
      
      //def cortador=getAuthenticatedUser()
      //assert cortador,'No esta firmado al sistema'
      //assert validarOperacionDeCortado(),'El sistema esta registrado sin rol de CORTADOR'
      def cortador=Usuario.findByUsername(corte.asignado)
      //assert corte.statusEmpaque=='EN EMPACADO','Corte  no esta EN EMPACADO'
      assert corte.fin,'No se ha terminado de cortar por lo que no se puede terminar el empacado'

      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operacin"
         redirect action:'enProceso',params:[id:cortador.id]
        return
      }
      def empacador=Usuario.findByNip(nip)
      if(!empacador){
        flash.error="Empacador no encontrado verifique su NIP "
         redirect action:'enProceso',params:[id:cortador.id]
        return 
      }
      if(!empacador.getAuthorities().find{it.authority=='EMPACADOR'}){
        flash.error="No tiene el ROL de EMPACADOR verifique su NIP "
         redirect action:'enProceso',params:[id:cortador.id]
        return 
      }
      /*
      if(empacador.username!=corte.empacador){
          flash.error="Este corte solo puede ser terminado el empaque por $corte.empacador"
          redirect action:'pendientes'
          return 
        }
        */
      corte.empacador=empacador.username
      corte.empacadoFin=new Date()
      corte.save(flush:true)
      //event('empacadoTerminado', corte.surtidoDet.surtido.id)
      //Actualizar surtido
      

      log.info "Empacado terminado para $corte.producto  "

      if(params.cortes){

        def adicionales=params.cortes.findAll({it.toLong()!=corte.id})
        
        adicionales.each{
          def ca=Corte.get(it)
          if( corte.pedido==ca.pedido && !ca.empacador && !ca.empacadoFin){
              ca.empacador=empacador.username
              ca.empacadoFin=corte.empacadoFin
              ca.save(flush:true)
              log.info "Empacado terminado para $ca.producto  "
          }
        }
            //print 'Surtidos adicionales: '+adicionales
      }

      //corteService.registrarFinDeCorteEnSurtido(corte)
      flash.success= "Empacado terminado para $corte.producto  "
      redirect action:'enProceso',params:[id:cortador.id]

    }


    @Transactional
    @Secured(['permitAll'])
    def terminarEmpacadoGlobal(Corte corte){
        println "Terminando empaque global para:  "+corte.surtidoDet.surtido.pedido
      def cortador=Usuario.findByUsername(corte.asignado)
      assert corte.surtidoDet.surtido.corteFin,'No se ha terminado de cortar por lo que no se puede terminar el empacado'

      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operacin"
         redirect action:'enProceso',params:[id:cortador.id]
        return
      }
      def empacador=Usuario.findByNip(nip)
      if(!empacador){
        flash.error="Empacador no encontrado verifique su NIP "
         redirect action:'enProceso',params:[id:cortador.id]
        return 
      }
      if(!empacador.getAuthorities().find{it.authority=='EMPACADOR'}){
        flash.error="No tiene el ROL de EMPACADOR verifique su NIP "
         redirect action:'enProceso',params:[id:cortador.id]
        return 
      }
    

      def surt=corte.surtidoDet.surtido
      def parts= surt.partidas.findAll{it.corte!=null && it.corte.fin!=null && it.corte.empacadoFin==null}
      if(parts){
        parts.each{
          println "actualizando partida del corte:  " +corte.id
        it.corte.empacador=empacador.username
        it.corte.empacadoFin=new Date()
        it.corte.save(flush:true)
        flash.success= "Empacado terminado para $corte.surtidoDet.surtido.pedido "
       }
        }else{
            log.info "El surtido no tiene partidas con corte  "  
            flash.success= "No se cerro el empaque  "        
        }
       

      /*corte.empacador=empacador.username
      corte.empacadoFin=new Date()
      println "Corte salvado"
      corte.save(flush:true)*/
      
      

      log.info "Empacado terminado para $corte.producto  "

      //flash.success= "Empacado terminado para $corte.producto  "
      redirect action:'enProceso',params:[id:cortador.id]

    }

    @Secured(['permitAll'])
    def enProceso(Integer max,Usuario cortador){
      //params.max = Math.min(max ?: 10, 100)
      params.sort='pedido'
      params.order='asc'

      def cortadores=UsuarioRole.executeQuery("select l.usuario from UsuarioRole l where l.role.authority='CORTADOR'")

      if(cortador==null){
        flash.error="No hay cortador registrado"
        [corteInstanceList:[],corteInstanceCount:0,cortadores:cortadores]
        return
      }
      //def query=Corte.where{asignado==cortador.username }

/*
def list=Vacaciones.findAll("from Vacaciones i  where year(i.lastUpdated)=? order by i.lastUpdated desc"
      ,[ejercicio])
*/
        def query=Corte.where{asignado==cortador.username  }

        query=query.where{surtidoDet.surtido.entregado==null  }
       // query=query.where{surtidoDet.surtido.cancelado==null }
        
     //def query=Corte.findAll("from Corte c where asignado=? and c.surtidoDet.surtido.entregado is null",[cortador.username])

        def list = query.list(params).findAll{!it.surtidoDet.surtido.cancelado && !it.surtidoDet.surtido.depurado}
      
   
      [corteInstanceList:list
     // [corteInstanceList:query(params)
        ,corteInstanceCount:list.size(),cortadores:cortadores
        ,cortador:cortador]
   
    }

    @Transactional
    @Secured(['permitAll'])
    def agregarAuxiliar(){
      Corte corte=Corte.get(params.id)
      def cortador=Usuario.findByUsername(corte.asignado)
      assert corte,'No existe el corte '+params.id
      String tipo=params.tipo
      assert tipo,'Auxiliar sin tipo no se puede generar'
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para agregar un auxiliar $tipo para el pedido $corte.pedido"
        redirect action:'enProceso',params:[id:cortador.id]
        return
      }
      def auxiliar=Usuario.findByNip(nip)
      if(!auxiliar){
        flash.error="Operador no encontrado verifique su NIP "
        redirect action:'enProceso',params:[id:cortador.id]
        return 
      }

      def auth=tipo
      if(!auxiliar.getAuthorities().find{it.authority==auth}){
        flash.error="No tiene el ROL de $auth verifique su NIP "
        redirect action:'enProceso',params:[id:cortador.id]
        return 
      }

      if(auxiliar.username==corte.asignado){
        flash.error="El corte ya esta asignado a: ($auxiliar.username) no puede ser auxiliar"
        redirect action:'enProceso',params:[id:cortador.id]
        return 
      }

      if(corte.auxiliares.find{it.nombre==auxiliar.username && it.tipo==tipo}){
        flash.error="$auxiliar.username ya esta registrado como auxiliar del corte/empaque"
        redirect action:'enProceso',params:[id:cortador.id]
        return 
      }
      
      corte.addToAuxiliares(nombre:auxiliar.username,tipo:tipo)
      corte.save(flush:true,failOnError:true)
      log.info "Cortador auxiliar $auxiliar.username asignado al  pedido: $corte.pedido   "
      flash.success="Auxiliar $tipo $auxiliar.username asignado al  pedido: $corte.pedido   "
      redirect action:'enProceso',params:[id:cortador.id]

    }

    private  boolean validarOperacionDeCortado(){
       return getAuthenticatedUser().getAuthorities().find{it.authority=='CORTADOR'}
    }

}

class ImportarCorteCommand{
  Date fecha
}
