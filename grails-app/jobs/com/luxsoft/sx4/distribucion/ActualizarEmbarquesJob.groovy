package com.luxsoft.sx4.distribucion

import org.quartz.StatefulJob
import org.quartz.JobExecutionContext



class ActualizarEmbarquesJob {
    
    def importadorDeEmbarquesService
    
    static triggers = {
      //simple name: 'normalTrigger', startDelay: 60000, repeatInterval: 300000
      cron cronExpression:"0 0/5 8-19 ? * MON-SAT"
    }

    def group = "sx4-importadores"

  	def description = "Actualiza embarques importados desde SIIPAP SW2"
  	def concurrent = false

    def execute(context) {
        try {
            
            def counter = context.jobDetail.jobDataMap['counter'] ?: 0
            counter++
            def fecha=new Date()
            def time=fecha.format('dd/MM/yyyy HH:mm:ss')
            log.debug "Actualizando embarques ($counter)  $time las time: "+context.jobDetail.jobDataMap['lastJob']
            importadorDeEmbarquesService.actualizar(fecha)
            context.jobDetail.jobDataMap['counter'] = counter
            def end=new Date()
            context.jobDetail.jobDataMap['lastJob'] = end
            //log.debug "Actualizacion terminada $end"
        }catch(Exception e) {
            e.printStackTrace()
            log.error e
        }
        
    }

}
