package com.luxsoft.sx4.distribucion

import grails.test.spock.IntegrationSpec
//import grails.buildtestdata.mixin.Build
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


//@Build([Embarque])
class EmbarqueIntegrationSpec extends IntegrationSpec {

	def embarqueService
	def grailsEventsRegistry
	

    def setup() {
    	assert embarqueService,'Embarque services not injected'
    	assert grailsEventsRegistry,'Grails event registry not injected'
    }

    def cleanup() {
    }

    void "Valid Embaques are"() {
    	
    	given:' Un embarque valido'
    	def embarque=Embarque.buildWithoutSave(documento:10)

    	when:'Salvamos el embarque'
    	embarque=embarqueService.create(embarque)

    	then:
    	embarque.errors.errorCount==0
    	embarque.id
    	Embarque.get(embarque.id).documento==10
    }

    void "Mensaje enviado al salvar un Embarque nuevo "(){
    	
    	given: "Setup for event listener"
    	def result = []
    	def latch = new CountDownLatch(1)
	    grailsEventsRegistry.on("app", "altaDeEmbarque") {event ->
	      result << "EmbarqueNuevo" 
	      latch.countDown()
	    }
	    when:
	    embarqueService.create(Embarque.buildWithoutSave(documento:11))
	    latch.await(5,TimeUnit.SECONDS) //can have optional timeout        

	    then:
	      result[0] == "EmbarqueNuevo"

    }
}
