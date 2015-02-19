package com.luxsoft.sx4.distribucion

class Corte {

	String producto
	String descripcion
	String sucursal
	Long pedido
	//Surtido surtido
	String tipo

	BigDecimal cantidad
	BigDecimal entregado=0 //? confunde no es necesaria
	BigDecimal pendiente=0 //? confunde no es necesaria
	BigDecimal percioCorte
	Integer cortes

	String instruccion

	Date inicio
	Date fin

	String asignado

	String origen
	Date dateCreated
	Date lastUpdated


    static constraints = {
    	producto maxSize:20
    	descripcion maxSize:250
    	sucursal maxSize:20
    	tipo inList:['ORDINARIO','PARCIAL']
    	asignado nullable:true
    	inicio nullable:true
    	fin nullable:true
    	cantidad scale:4
    	entregado scale:4
    	pendiente scale:4
    }
}
