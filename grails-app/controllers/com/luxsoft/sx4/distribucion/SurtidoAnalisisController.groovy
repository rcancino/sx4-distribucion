package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario

@Transactional(readOnly = true)
@Secured(["hasAnyRole('GERENTE')"])
class SurtidoAnalisisController {

    def index() { }


    
    def porEntregarAnalisis() {
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{entregado==null}
        def res=query.list(params).collect({new SurtidoAnalisis(surtido:it)})
        //respond surtidores, model:[surtidoInstanceCount:query.count()]
        [surtidoInstanceList:res]
    }
}
