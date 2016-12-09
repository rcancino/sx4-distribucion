package com.luxsoft.sx4.distribucion



class CancelarNoAtendidosJob {

   def importadorDeSurtidoService
      
      static triggers = {
        //cron cronExpression:"0 0/1 8-19 ? * MON-SAT"
           cron cronExpression:"0 0 9 ? * MON-SAT"

      }

      def group = "sx4-importadores"

    	def description = "Cancelar Surtidos no atendidos"
    	
      def concurrent = false

      def execute(context) {
      	try {
            
      		  def fecha=new Date() 
      		 
  				println "Cancelando Surtidos No Atendidos con antiguedad mayor a 7 dias"

      		  importadorDeSurtidoService.cancelarSurtidosNoAtendidos(fecha)
            
      	}catch(Exception e) {
      		log.error e
      	}
          
      }
}
