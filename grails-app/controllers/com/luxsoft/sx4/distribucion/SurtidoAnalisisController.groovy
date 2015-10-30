package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import grails.validation.Validateable
import org.grails.databinding.BindingFormat

import com.luxsoft.sx4.sec.Usuario
import com.luxsoft.sx4.Cliente
import groovy.transform.ToString
import com.luxsoft.sx4.Periodo

@Transactional(readOnly = true)
@Secured(["hasAnyRole('GERENTE')"])
class SurtidoAnalisisController {


    def beforeInterceptor = {
        if(!session.periodoDeAnalisis){
            session.periodoDeAnalisis=new Date()
        }
    }

    def cambiarPeriodo(){
        def fecha=params.date('fecha', 'dd/MM/yyyy')
        session.periodoDeAnalisis=fecha
        redirect(uri: request.getHeader('referer') )
    }

    def index() { }


    
    def porEntregarAnalisis() {
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{entregado==null}
        def res=query.list(params).collect({new SurtidoAnalisis(surtido:it)})
        //respond surtidores, model:[surtidoInstanceCount:query.count()]
        [surtidoInstanceList:res]
    }

    def buscarClientes(){
    	
    	def clientes=Cliente.findAllByNombreIlike(params.term+"%",[max:50,sort:"nombre",order:"desc"])
    	def clientesList=clientes.collect { cliente ->
    		[id:cliente.id,label:cliente.nombre,value:cliente.nombre]
    	}
    	def res = clientesList as JSON
    	render res
    }

    def buscarPedido(){
    	
    	//def pedidos=Surtido.findAllByPedidoIlike(params.term+"%",[max:50,sort:"pedido",order:"desc"])
    	def term=params.long('term')
    	def query=Surtido.where{
            (pedido>=term) 
        }
        
        def pedidos=query.list(max:30, sort:"pedido",order:'asc')

    	def pedidosList=pedidos.collect { surtido ->
    		def label="Surtido $surtido.id Pedido: $surtido.pedido"
    		[id:surtido.pedido,label:label,value:label]
    	}
    	def res = pedidosList as JSON
    	render res
    }

    def filtrar(SearchCommand command){
    	println 'Params: '+params
    	println 'Filtrando: '+command
        def q = Surtido.where {
            fecha >= command.fechaInicial && fecha <= command.fechaFinal
        }
        params.max = 40
        params.sort='fecha'
        params.order='desc'
        [surtidoInstanceList:q.list(params),surtidoInstanceCount:q.count(),searchCommand:command]
    }

    def analisis(Surtido surtido){
        //def cortes=Corte.findAll("from Corte c where c.surtidoDet.surtido=?",surtido)
        def q=Corte.where {
            surtidoDet.surtido==surtido
        }
        respond surtido,model:[cortes:q.list()]
    }
}

@Validateable 
@ToString(includeNames=true,includePackage=false)
class SearchCommand {

	//Long pedido

	@BindingFormat('dd/MM/yyyy')
	Date fechaInicial=new Date()-30
	
	@BindingFormat('dd/MM/yyyy')
	Date fechaFinal=new Date()

	Cliente cliente

	static constraints={
		fechaInicial nullable:false
		fechaFinal nullable:false
		cliente  nullable:true
		//pedido nullable:true
	}

	Periodo toPeriodo(){
	 	return new Periodo(fechaInicial,fechaFinal)
	}


}

