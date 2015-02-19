package com.luxsoft.sx4.distribucion



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class SurtidoController {

    def importacionService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.sort='pedidoCreado'
        params.order='asc'
        respond Surtido.list(params), model:[surtidoInstanceCount:Surtido.count()]
    }

    @Secured(['permitAll'])
    def pendientes() {
        params.max = 100
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{asignado==null}
        respond query.list(params), model:[surtidoInstanceCount:query.count()]
    }

    @Transactional
    def importar() {
       def fecha=new Date()
       importacionService.importarSurtido(fecha)
       render view:'index'
    }

    @Transactional
    def asignar(Surtido surtido){
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
      surtido.asignado=user.nombre
      surtido.iniciado=new Date()
      surtido.save(flush:true,failOnError:true)
      log.info "Surtido de pedido: $surtido.pedido asignado a  $user.nombre "
      flash.brodcast="Surtido de pedido: $surtido.pedido asignado a  $user.nombre "
      redirect action:'pendientes'

    }

    
}
