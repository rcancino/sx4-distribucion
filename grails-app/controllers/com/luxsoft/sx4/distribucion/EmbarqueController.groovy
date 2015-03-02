package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario

@Transactional(readOnly = true)
@Secured(["hasAnyRole('GERENTE')"])
class EmbarqueController {

    def index() { 
    	// def embarques=Embarque.findAll("from Embarque e where e.fecha between ? and ? order by e.documento desc "
    	// 	,[session.fecha,session.fecha-5])
    	[embarqueInstanceList:Embarque.list(params),embarqueInstanceCount:Embarque.count()]
    }
}
