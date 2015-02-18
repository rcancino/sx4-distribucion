package com.luxsoft.sx4.distribucion



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class SurtidoController {

    def importacionService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
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

   
   

   

    
}
