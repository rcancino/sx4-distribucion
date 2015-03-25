package com.luxsoft.sx4.distribucion


import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode

import com.luxsoft.sx4.VentaDet
import com.luxsoft.sx4.Producto

@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='producto,origen')
class EntregaDet {

	
	Producto producto
	BigDecimal cantidad
	BigDecimal valor
	VentaDet ventaDet
    String origen

	BigDecimal asignado
	BigDecimal pendiente


	static belongsTo = [entrega: Entrega]

	static transients=['asignado','pendiente']

    static constraints = {
    	cantidad scale:3
    	valor scale:4
    	ventaDet nullable:true
    }

    public EntregaDet(){}

    public EntregaDet(VentaDet vd){
    	ventaDet=vd
        producto=vd.producto
    	cantidad=0.0
    	valor=vd.subtotal
        origen=vd.id
    }

    def getAsignado(){
    	if(!asignado){
    		asignado= EntregaDet.executeQuery("select sum(e.cantidad) from EntregaDet e where e.ventaDet=?",[ventaDet])[0]?:0.0
    	}
    	return asignado
    }

    def getPendiente(){
    	if(!pendiente)
    		pendiente=ventaDet.cantidad.abs()-getAsignado()
    	return pendiente
    }

    
    def actualizar(){
		def canti=cantidad/this.ventaDet.factor
		def precio=ventaDet.precio
		def impBruto=precio*canti.abs()
		def descuento=impBruto*(ventaDet.descuento/100)
        valor=impBruto-descuento
	}
	
}

   	
   	

	

	
	
