package com.luxsoft.sx4.distribucion

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EmbarqueService)
@Mock([Embarque])
@Build([Embarque])
class EmbarqueServiceSpec extends Specification {



    def setup() {
    }

    def cleanup() {
    }

    void "Valid Embaques are"() {
    	
    	given:' Un embarque valido'
    	def embarque=Embarque.buildWithoutSave(documento:10)

    	when:'Salvamos el embarque'
    	embarque=service.create(embarque)

    	then:
    	embarque.errors.errorCount==0
    	embarque.id
    	Embarque.get(embarque.id).documento==10
    }
}
