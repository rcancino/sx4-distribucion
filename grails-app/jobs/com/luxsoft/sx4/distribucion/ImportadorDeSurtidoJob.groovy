package com.luxsoft.sx4.distribucion



class ImportadorDeSurtidoJob {

	def importadorDeSurtidoService
    
    static triggers = {
      //simple name: 'normalTrigger', startDelay: 60000, repeatInterval: 30000
      cron cronExpression:"0 0/1 8-17 ? * MON-SAT"
    }

    def group = "sx4-importadores"

  	def description = "Importador de Surtidos desde el Siipap SW2"
  	
    def concurrent = false

    def execute(context) {
    	try {
            def counter = context.jobDetail.jobDataMap['counter'] ?: 0
            counter++
    		def fecha=new Date()
    		def time=fecha.format('dd/MM/yyyy HH:mm:ss')
    		log.info "Importando surtidos ($counter)  $time las time: "+context.jobDetail.jobDataMap['lastJob']
    		importadorDeSurtidoService.importar(fecha)
            
            context.jobDetail.jobDataMap['counter'] = counter
            def end=new Date()
            context.jobDetail.jobDataMap['lastJob'] = end
    		log.info "Importacion terminada $end"
    	}catch(Exception e) {
    		log.error e
    	}
        
    }
}
