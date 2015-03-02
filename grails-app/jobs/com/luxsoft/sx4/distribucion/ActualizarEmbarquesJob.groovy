package com.luxsoft.sx4.distribucion

import org.quartz.StatefulJob
import org.quartz.JobExecutionContext



class ActualizarEmbarquesJob {
    
    def importadorDeEmbarquesService
    
    static triggers = {
      simple name: 'normalTrigger', startDelay: 60000, repeatInterval: 300000
    }

    def group = "Embarques"
  	def description = "Actualiza embarques importados desde SIIPAP SW2"
  	def concurrent = false

    def execute(JobExecutionContext context) {
    	try {
    		def fecha=new Date()
    		def time=fecha.format('dd/MM/yyyy HH:mm:ss')
    		log.info "Actualizando embarques JOB :$time"
    		importadorDeEmbarquesService.actualizar(fecha)
    	}catch(Exception e) {
    		log.error e
    	}
        
    }

}
