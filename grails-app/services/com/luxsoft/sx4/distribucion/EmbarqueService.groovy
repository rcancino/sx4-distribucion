package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional

@Transactional
class EmbarqueService {

	def springSecurityService

    Embarque create(Embarque embarque){
    	
    	try {
    		embarque=embarque.save flush:true,failOnError:true
    		event('altaDeEmbarque',embarque)
    		return embarque
    	}
    	catch(Exception e) {
    		throw new EmbarqueException("Embarque invalido",embarque:embarque)
    	}
    	
    }
}

class EmbarqueException extends RuntimeException{
	String message
	Embarque embarque
}
