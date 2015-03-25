package com.luxsoft.sx4.distribucion



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured(["hasAnyRole('GERENTE')"])
@Transactional(readOnly = true)
class EntregaDetController {

    

    
    @Transactional
    def update(EntregaDet entregaDetInstance) {
        
        entregaDetInstance.save flush:true
        //entregaDetInstance.entrega.actualizar()
        entregaDetInstance.entrega.embarque.actualizar()
        flash.message = "Partida actualizada"
        redirect controller:'entrega', action:'show', params:[id:entregaDetInstance.entrega.id]
    }
    

   
}
