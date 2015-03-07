package com.luxsoft.sx4.distribucion

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat

@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='sucursal,pedido')
class Surtido {

	String sucursal
	
	Long pedido

	String cliente

	String nombre
	
	String tipo
	
	@BindingFormat('dd/MM/yyyy')
	Date fecha

	String asignado

	Date iniciado

	Date corteInicio

	Date corteFin

	String entrego

	Date entregado

	String vendedor

	Date pedidoCreado

	Date facturado
    String venta
    String tipoDeVenta
    String formaDeEntrega

	String origen

	Integer cortes

	List partidas

	Date dateCreated
	Date lastUpdated

    Boolean empacadoTerminado

    String forma

    Boolean puesto
    Date fechaPuesto

    SurtidoAnalisis analisis




	static hasMany = [partidas: SurtidoDet,auxiliares:AuxiliarDeSurtido]


    static constraints = {
    	cliente maxSize:20
    	nombre()
    	sucursal maxSize:20
    	tipo inList:['ORDINARIO','PARCIAL','TRASLADO']
    	asignado nullable:true,maxSize:50
    	iniciado nullable:true
    	corteInicio nullable:true
    	corteFin nullable:true
    	entrego nullable:true
    	entregado nullable:true
        venta nullable:true,maxSize:20
        tipoDeVenta nullable:true,maxSize:10
        forma nullable:true,maxSize:10
        fechaPuesto nullable:true
        facturado nullable:true
        formaDeEntrega maxSize:20

    }

    static mapping = {
    	fecha type:'date'
        partidas cascade: "all-delete-orphan"
        auxiliares cascade: "all-delete-orphan"
    }

    static transients = ['cortes','status','empacadoTerminado','analisis']

    def getCortes(){
    	return partidas.sum{return it.corte?1:0}
    }

    def getStatus(){
        if(entregado){
    		return 'ENTREGADO'
    	}

    	if(getCortes()>0){
    		if(corteFin!=null)
    			return 'POR ENTREGAR'
    		if(corteInicio!=null)
    			return 'EN CORTE'
            else
                return 'PENDIENTE'
    	}else{
            return asignado?'POR ENTREGAR':'PENDIENTE'
        }
    }

    def getEmpacadoTerminado(){
        if(cortes){
            def cortes=partidas.findAll{it.corte!=null}
            def found=cortes.find{
                it.corte.empacadoFin==null
            }
            if(found==null){
                return true
            }
            return true
        }
        return true
        
    }

    def getAnalisis(){
        if(analisis==null){
            analisis=new SurtidoAnalisis()
        }
        return analisis
    }
    

}
