package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario

//@NotTransactional
@Secured(["hasAnyRole('GERENTE')"])
class TablerosController {

	/**
	* Despliega el tablero de control y monitoreo para el surtido
	**/
    def surtido() { 
    }
}
