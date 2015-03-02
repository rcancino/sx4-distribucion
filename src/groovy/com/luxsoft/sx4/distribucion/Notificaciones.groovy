package com.luxsoft.sx4.distribucion

import org.apache.commons.logging.LogFactory
import grails.events.Listener

class Notificaciones {

	private static final log=LogFactory.getLog(this)

	@Listener(topic="surtidoEntregado")
	def surtidoEntregado(Long id){
		println "Surtido entregado: "+id
		log.info 'Surtido entregado...'

	}

	//@Listener(namespace="gorm")
	@Listener(topic="altaDeEmbarque")
	def altaDeEmbarque(Embarque e){
		println 'Registrando embarque nuevo'
	}





	
}