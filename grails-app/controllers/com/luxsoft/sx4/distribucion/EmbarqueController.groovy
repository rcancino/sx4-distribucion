package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario
import com.luxsoft.sx4.Periodo

@Transactional(readOnly = true)
@Secured(["hasAnyRole('GERENTE')"])
class EmbarqueController {

	def embarqueService

    def cambiarPeriodo(Periodo periodo){

        session.periodoEmbarques=periodo
        redirect action:'index'
    }

    def index(Integer max) { 
    	params.max = Math.min(max ?: 50, 100)
        params.sort='documento'
        params.order='desc'
        def periodo=session.periodoEmbarques
        def list=Embarque.findAll("from Embarque e where date(e.fecha) between ? and ?"
            ,[periodo.fechaInicial,periodo.fechaFinal],params)
        def count=Embarque.executeQuery("select count(e.id) from Embarque e  where date(e.fecha) between ? and ?"
                ,[periodo.fechaInicial,periodo.fechaFinal])[0]
    	
    	[embarqueInstanceList:list,embarqueInstanceCount:count]
    }

    def pendientes(Integer max){
    	params.max = Math.min(max ?: 50, 100)
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

    def create(){
    	[embarqueInstance:new Embarque(sucursal:findSucursal(),fecha:new Date())]
    }

    @Transactional
    def save(Embarque embarqueInstance){
        assert embarqueInstance,'Embaqrque nulo'
        // embarqueInstance.validate()
        // if(embarqueInstance.hasErrors()){
        //     render view:'create',model:[embarqueInstance:embarqueInstance]
        //     return
        // }
        try {

            embarqueInstance=embarqueService.create embarqueInstance
            flash.message='Embarque generado: '+embarqueInstance.id
            redirect action:'index'

        }catch(EmbarqueException ex) {
            render view:'create',model:[embarqueInstance:ex.embarque]
            return
        }

    }

    String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }


}


