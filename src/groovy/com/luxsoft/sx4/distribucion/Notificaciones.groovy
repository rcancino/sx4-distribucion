package com.luxsoft.sx4.distribucion

import org.apache.commons.logging.LogFactory

class Notificaciones {

	private static final log=LogFactory.getLog(this)

	@grails.events.Listener(topic="surtidoEntregado")
	def surtidoEntregado(Long id){
		println "Surtido entregado: "+id
		log.info 'Surtido entregado...'

	}


	
}