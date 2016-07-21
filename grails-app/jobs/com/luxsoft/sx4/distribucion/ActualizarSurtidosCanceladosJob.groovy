package com.luxsoft.sx4.distribucion



class ActualizarSurtidosCanceladosJob {
    
      def importadorDeSurtidoService
      
      static triggers = {
        //cron cronExpression:"0 0/1 8-19 ? * MON-SAT"
        cron cronExpression:"0 0/1 8-19 ? * MON-SAT"

      }

      def group = "sx4-importadores"

    	def description = "Actualizar de surtidos cancelados desde el Siipap SW2"
    	
      def concurrent = false

      def execute(context) {
      	try {
            def counter = context.jobDetail.jobDataMap['counter'] ?: 0
            counter++
      		  def fecha=new Date() 
      		  def time=fecha.format('dd/MM/yyyy HH:mm:ss')
      		  log.debug "Actualizando  surtidos cancelados ($counter) las time: "+context.jobDetail.jobDataMap['lastJob']
      		  importadorDeSurtidoService.actualizarSurtidosCancelados(fecha)
            context.jobDetail.jobDataMap['counter'] = counter
            def end=new Date()
            context.jobDetail.jobDataMap['lastJob'] = end
      	}catch(Exception e) {
      		log.error e
      	}
          
      }

}
