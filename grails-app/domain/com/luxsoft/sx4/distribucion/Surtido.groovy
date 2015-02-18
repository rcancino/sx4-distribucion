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

	Boolean corte

	String asignado

	Date iniciado

	Date finalizado

	Date entregado
	

	String vendedor
	Date pedidoCreado
	Date facturado
	String origen

	List partidas

	Date dateCreated
	Date lastUpdated

	static hasMany = [partidas: SurtidoDet]


    static constraints = {
    	cliente maxSize:20
    	nombre()
    	sucursal maxSize:20
    	tipo inList:['ORDINARIO','PARCIAL']
    	asignado nullable:true,maxSize:50
    	iniciado nullable:true
    	finalizado nullable:true
    	entregado nullable:true
    	
    }
    static mapping = {
    	fecha type:'date'
    }


}
