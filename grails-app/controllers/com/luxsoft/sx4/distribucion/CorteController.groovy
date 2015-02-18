package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured(["hasAnyRole('CORTADOR')"])
class CorteController {

	def importacionService

    def index() { }

    def pendientes(){

    }

    def importar(Date fecha){
    	log.info 'Importando cortes :'+fecha
    	if(!fecha)
    		fecha=new Date()
    	importacionService.importarCortes(fecha)
    	redirect action:'pendientes'
    }
}
