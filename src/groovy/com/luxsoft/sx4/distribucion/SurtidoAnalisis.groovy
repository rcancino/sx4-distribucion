package com.luxsoft.sx4.distribucion

import org.apache.commons.logging.LogFactory
import groovy.time.*

class SurtidoAnalisis {

	private static final log=LogFactory.getLog(this)

	Surtido surtido

	public Surtido(Surtido surtido){
		this.surtido=surtido
	}


	def getStatus(){
		if(surtido.entregado)
			return 'ENTREGADO'
		if(surtido.cortes){
			if(surtido.corteFin) return 'POR ENTREGAR'
			if(surtido.corteInicio) return 'EN CORTE'
			if(surtido.asignado) return 'EN SURTIDO'
			else return 'PENDIENTE'
		}else{
			if(surtido.asignado) return 'POR ENTREGAR'
			else return 'PENDIENTE'
		}
	}

	def getCortador(){
		if(surtido.cortes){
			return surtido.partidas.findAll{it.corte!=null}.first().corte.asignado
		}
		else return null
	}

	def getUltimoAsignado(){
		if(surtido.entregado) return surtido.entrego
		else if(surtido.cortes && surtido.corteInicio){
			return getCortador()
		}else{
			return surtido.asignado
		}
	}

	def getTiempoAcumulado(){
		
		if(surtido.entregado){
			return TimeCategory.minus(surtido.entregado, surtido.iniciado)
		}

		def start = surtido.iniciado?:surtido.pedidoCreado
		def stop = new Date() 
		if(surtido.cortes){
			if(surtido.corteFin) 
				start=surtido.corteFin
			else if(surtido.corteInicio) 
				start=surtido.corteInicio
		}else if(surtido.asignado){
			start=surtido.iniciado
		}
		
		TimeDuration duration = TimeCategory.minus(stop, start)
		def dias=duration.getDays()?duration.getDays()+' dias':''
		def horas=duration.getHours()?duration.getHours()+ 'hrs':''
		def minutos=duration.getMinutes()?duration.getMinutes()+ 'min':''
		return "$dias $horas $minutos"
	}

	def getMinutosAcumulados(){
		if(surtido.entregado){
			def duration=TimeCategory.minus(surtido.entregado, surtido.iniciado)
			return duration.toMilliseconds()/(1000*60)
		}

		def start = surtido.iniciado?:surtido.pedidoCreado
		def stop = new Date() 
		if(surtido.cortes){
			if(surtido.corteFin) 
				start=surtido.corteFin
			else if(surtido.corteInicio) 
				start=surtido.corteInicio
		}else if(surtido.asignado){
			start=surtido.iniciado
		}
		
		TimeDuration duration = TimeCategory.minus(stop, start)
		return duration.toMilliseconds()/(1000*60)
	}

	def getKilos(){
		return surtido.partidas.sum (0.0) {it.kilos}
	}

	
}