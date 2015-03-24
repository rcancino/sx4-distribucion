package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario
import com.luxsoft.sx4.Periodo
import com.luxsoft.sx4.InstruccionDeEntrega
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray

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
        try {
            embarqueInstance.sucursal=findSucursal()
            embarqueInstance=embarqueService.create embarqueInstance
            flash.message='Embarque generado: '+embarqueInstance.id
            redirect action:'index'
        }catch(EmbarqueException ex) {
            render view:'create',model:[embarqueInstance:ex.embarque]
            return
        }

    }

    def show(Embarque embarqueInstance){
        [embarqueInstance:embarqueInstance]
    }

    def agregarEntrega(Embarque embarqueInstance){
        def pendientes=InstruccionDeEntrega.findAllByEntregaIsNull()
        [embarqueInstance:embarqueInstance,pendientes:pendientes]
    }

    @Transactional
    def registrarEntregas(Embarque embarqueInstance){
        assert request.xhr,'No es un Ajax request'
        def dataToRender=[:]
        JSONArray facturas=JSON.parse(params.partidas);
        embarqueInstance=embarqueService.agregarEntregaDeVentas(embarqueInstance,facturas)
        dataToRender.id=embarqueInstance.id
        render dataToRender as JSON
    }

    @Transactional
    def hacerParcial(Entrega entregaInstance){
        assert entregaInstance,'No existe la entrega: '+params.id
        def instruccion=InstruccionDeEntrega.findByEntrega(entregaInstance)
        assert instruccion,'No localizo la instruccion de entrega para la entrega: '+entregaInstance.id
        def documentos=[]
        instruccion.venta.partidas.each{
            def entregaDet=new EntregaDet(it)
            documentos.add(entregaDet)
        }
        [entregaInstance:entregaInstance,documentos:documentos]
    }

     @Transactional
    def agregarEntregaParcial(Entrega entregaInstance){
        println 'Generando entrega parcial:'+params
        flash.message="Generando entrega parcial: "+params.partidas
        redirect action:'show',params:[id:entregaInstance.embarque.id]
    }

    String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }


}


