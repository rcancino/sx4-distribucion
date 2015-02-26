package com.luxsoft.sx4.distribucion



class ImportadorDeSurtidoJob {

	def importadorDeSurtidoService
    
    static triggers = {
      simple name: 'normalTrigger', startDelay: 60000, repeatInterval: 30000
    }

    def group = "DistribucionGroup"
  	def description = "Importador de Surtidos desde el Siipap SW2"
  	def concurrent = false

    def execute() {
    	try {
    		def time=new Date().format('dd/MM/yyyy HH:mm:ss')
    		log.info "Importando surtidos $time"
    		importadorDeSurtidoService.importar(time)
    		//def res=socioService.suspender()
    		//log.info "Resultado de la suspencion automatica: $res Ejecutado: $time"
    	}catch(Exception e) {
    		log.error e
    	}
        
    }
}
