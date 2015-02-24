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

	String origen

	Integer cortes

	List partidas

	Date dateCreated
	Date lastUpdated

    Boolean empacadoTerminado

	static hasMany = [partidas: SurtidoDet]


    static constraints = {
    	cliente maxSize:20
    	nombre()
    	sucursal maxSize:20
    	tipo inList:['ORDINARIO','PARCIAL']
    	asignado nullable:true,maxSize:50
    	iniciado nullable:true
    	corteInicio nullable:true
    	corteFin nullable:true
    	entrego nullable:true
    	entregado nullable:true
        venta nullable:true,maxSize:20
        tipoDeVenta nullable:true,maxSize:10
    }

    static mapping = {
    	fecha type:'date'
    }

    static transients = ['cortes','status','empacadoTerminado']

    def getCortes(){
    	return partidas.sum{return it.corte?it.corte.cortes:0}
    }

    def getStatus(){
    	if(entregado){
    		return 'ENTREGADO'
    	}
    	else if(getCortes()>0){
    		if(corteFin)
    			return 'POR ENTREGAR'
    		if(corteInicio)
    			return 'EN CORTE'
    	}
    	if(asignado)
    		return 'EN SURTIDO'
    	else
    		return 'PENDIENTE'
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
    

}
