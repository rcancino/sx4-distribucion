package com.luxsoft.sx4.distribucion

import org.quartz.StatefulJob
import org.quartz.JobExecutionContext

class ImportadorDeEmbarquesJob {
    
    def importadorDeEmbarquesService

    
    
    static triggers = {
      simple name: 'normalTrigger', startDelay: 60000, repeatInterval: 60000*5
    }

    def group = "DistribucionGroup"
  	def description = "Importador de Embarques desde el Siipap SW2"
  	def concurrent = false

    def execute(JobExecutionContext context) {
    	try {
    		
    		def map = context.getJobDetail().getJobDataMap()
    		
    		map.last=map.last?:0
    		def fecha=new Date()
    		def time=fecha.format('dd/MM/yyyy HH:mm:ss')
    		log.info "Importando Embarques $time ($map.last)"
    		importadorDeEmbarquesService.importar(fecha)
    		
    		map.last=map.last++
    		
    	}catch(Exception e) {
    		log.error e
    	}
        
    }

}
