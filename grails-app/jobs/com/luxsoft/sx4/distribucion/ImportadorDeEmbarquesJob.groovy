package com.luxsoft.sx4.distribucion



class ImportadorDeEmbarquesJob {
    
    def importadorDeEmbarquesService
    
    static triggers = {
      simple name: 'normalTrigger', startDelay: 60000, repeatInterval: 30000
    }

    def group = "DistribucionGroup"
  	def description = "Importador de Embarques desde el Siipap SW2"
  	def concurrent = false

    def execute() {
    	try {
    		def fecha=new Date()
    		def time=fecha.format('dd/MM/yyyy HH:mm:ss')
    		log.info "Importando Embarques $time"
    		importadorDeEmbarquesService.importar(fecha)
    		//def res=socioService.suspender()
    		//log.info "Resultado de la suspencion automatica: $res Ejecutado: $time"
    	}catch(Exception e) {
    		log.error e
    	}
        
    }

}
