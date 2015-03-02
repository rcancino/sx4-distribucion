package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario

@Transactional(readOnly = true)
@Secured(["hasAnyRole('GERENTE')"])
class EmbarqueController {

	def afterInterceptor = { model ->
	    //if(!session.periodoEmbarque) session.periodoEmbarque=com.luxsoft.sx4.Periodo.getCurrentMonthToday()
	    if(model.periodo==null)
	    	model.periodo=com.luxsoft.sx4.Periodo.getCurrentMonthToday()
	}

    def index(Integer max) { 
    	params.max = Math.min(max ?: 20, 100)
        params.sort='documento'
        params.order='desc'
    	// def embarques=Embarque.findAll("from Embarque e where e.fecha between ? and ? order by e.documento desc "
    	// 	,[session.fecha,session.fecha-5])
		
    	[embarqueInstanceList:Embarque.list(params),embarqueInstanceCount:Embarque.count(),title:'Embarques del periodo']
    }

    def pendientes(Integer max){
    	params.max = Math.min(max ?: 10, 100)
        params.sort='documento'
        params.order='desc'
    	def embarques=Embarque.findAllByCerradoIsNull(params)
    	render view:'index',model:[embarqueInstanceList:embarques,embarqueInstanceCount:Embarque.countByCerradoIsNull(),title:'Pendientes']
    	// 	,[session.fecha,session.fecha-5])
    }

    def transito(Integer max){
		params.max = Math.min(max ?: 10, 100)
	    params.sort='documento'
	    params.order='desc'
		def embarques=Embarque.findAllBySalidaIsNotNullAndRegresoIsNull(params)
		render view:'index',model:[embarqueInstanceList:embarques
			,embarqueInstanceCount:Embarque.countBySalidaIsNotNullAndRegresoIsNull()
			,title:'En transito']
    }

    def atendidos(Integer max){
		params.max = Math.min(max ?: 10, 100)
	    params.sort='documento'
	    params.order='desc'
		def embarques=Embarque.findAllByRegresoIsNotNull(params)
		render view:'index',model:[embarqueInstanceList:embarques
			,embarqueInstanceCount:Embarque.countByRegresoIsNotNull()
			,title:'Atendidos']
    }




}
