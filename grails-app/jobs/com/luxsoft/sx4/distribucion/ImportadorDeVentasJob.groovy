package com.luxsoft.sx4.distribucion



class ImportadorDeVentasJob {

	def group = "sx4-importadores"
	def importadorDeVentaService

    static triggers = {
      //cron cronExpression:"0 1 8-19 ? * MON-SAT"
      cron cronExpression:"0/15 * 8-19 ? * MON-SAT"
    }

    def description = "Importador de Ventas desde el Siipap SW2"
  	
    def concurrent = false

    def execute(context) {
    	try {
            def counter = context.jobDetail.jobDataMap['counter'] ?: 0
            counter++
    		def fecha=new Date() 
    		def time=fecha.format('dd/MM/yyyy HH:mm:ss')
    		log.debug "Importando ventas ($counter)  $time las time: "+context.jobDetail.jobDataMap['lastJob']
            println "Importando ventas ($counter)  $time las time: "+context.jobDetail.jobDataMap['lastJob']
    		importadorDeVentaService.importar(fecha)
            context.jobDetail.jobDataMap['counter'] = counter
            def end=new Date()
            context.jobDetail.jobDataMap['lastJob'] = end
    		//log.debug "Importacion terminada $end"
    	}catch(Exception e) {
    		log.error e
    	}
        
    }
}
