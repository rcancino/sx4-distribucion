package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import grails.validation.Validateable
import org.grails.databinding.BindingFormat

import com.luxsoft.sx4.Periodo
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

