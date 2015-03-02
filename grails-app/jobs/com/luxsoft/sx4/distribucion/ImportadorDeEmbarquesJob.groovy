package com.luxsoft.sx4.distribucion

import org.quartz.StatefulJob
import org.quartz.JobExecutionContext

class ImportadorDeEmbarquesJob {
    
    def importadorDeEmbarquesService

    
    
    static triggers = {
      simple name: 'normalTrigger', startDelay: 60000, repeatInterval: 300000
    }

    def group = "Embarques"
  	def description = "Importa  embarques desde el Siipap SW2"
  	def concurrent = false

    def execute(JobExecutionContext context) {
    	try {
    		def map = context.getJobDetail().getJobDataMap()
            
            def count=map.count?:1
    		def fecha=new Date()
    		def time=fecha.format('dd/MM/yyyy HH:mm:ss')
    		log.info "Importando Embarques $time ($count)"
    		importadorDeEmbarquesService.importar(fecha)
    		map.count=count++
    		
    	}catch(Exception e) {
    		log.error e
    	}
        
    }

}
