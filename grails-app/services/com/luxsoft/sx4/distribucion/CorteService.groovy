package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional

@Transactional
class CorteService {

	@NotTransactional
    @grails.events.Listener(topic="corteAsignado")
	def registroDeSurtido(Corte corte){
		Surtido.withNewSession { session ->
			log.info 'Corte registrado actualizando Surtido'
			def found=Surtido.find("from Surtido s where s.id=?",[corte.surtidoDet.surtido.id])
			if(!found.corteInicio){
				found.corteInicio=corte.inicio
				found.save(flush:true)
			}
		}

	}

	//@NotTransactional
    //@grails.events.Listener(topic="empacadoTerminado")
	def empacadoTerminado(Long surtidoId){
		
		Surtido.withNewSession { session ->

			def surtido=Surtido.find("from Surtido s where s.id=?",[surtidoId])
			
			//println 'Partidas: '+surtido.partidas
			try {
				def cortes=surtido.partidas.findAll{it.corte!=null}
				def found=cortes.find{
					it.corte.empacadoFin==null
				}
				if(found==null){
					log.info 'Actualizando  fin del proceso de corte en el surtido '+surtido.id
					surtido.corteFin=new Date()
					surtido.save(flush:true)
				}
				
				
			}
			catch(Exception e) {
				println e.message
			}
			
			
			surtido.partidas.each{
				if(it.corte){
					//println it.corte.empacadoFin
				}
				
			}
			//
			
			
			// if(found==null){
			// 	surtido.corteFin=new Date()
			// 	surtido.save(flush:true)	
			// }
		}
	}


	def registrarFinDeCorteEnSurtido(Corte corte){
		def surtido=corte.surtidoDet.surtido
		println 'Actualizando fin de corte en surtido: '+surtido.id
		def cortes=surtido.partidas.findAll{it.corte!=null && it.corte!=corte}
		println 'Partidas de corte: '+cortes.size()
		def found=cortes.find{
			it.corte.empacadoFin==null
		}
		println 'Partida en corte pendiente: '+found

		if(found==null){
			log.info 'Actualizando  fin del proceso de corte en el surtido '+surtido.id
			surtido.corteFin=new Date()
			surtido.save(flush:true)
		}
	}

	def registrarInicioDeCorteEnSurtido(Corte corte){
		def surtido=corte.surtidoDet.surtido
		if(surtido.corteInicio==null){
		  surtido.corteInicio=corte.inicio
		  surtido.save(flush:true)
		}
	}
}
