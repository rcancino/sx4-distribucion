package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import com.luxsoft.sx4.Folio
import org.apache.commons.lang.exception.ExceptionUtils

@Transactional
class EmbarqueService {

	def springSecurityService

    Embarque create(Embarque embarque){
    	
    	try {
            
            def serie="EMBARQUES"
            def folio=Folio.findBySerie(serie)
            if(!folio) 
                folio=new Folio(serie:serie,folio:1).save()
            embarque.documento=folio.next()
    		embarque=embarque.save flush:true,failOnError:true
    		event('altaDeEmbarque',embarque)
    		return embarque
    	}
    	catch(grails.validation.ValidationException e) {
            //def msg=ExceptionUtils.getRootCauseMessage(e)
    		throw new EmbarqueException(message:e.message,embarque:embarque)
    	}
    	
    }
}

class EmbarqueException extends RuntimeException{
	String message
	Embarque embarque

    
}
