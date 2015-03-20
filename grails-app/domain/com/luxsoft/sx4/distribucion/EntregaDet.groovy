package com.luxsoft.sx4.distribucion


import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode

import com.luxsoft.sx4.VentaDet

@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='producto,origen')
class EntregaDet {

	//String origen 
	//String documentoOrigen //ventaDet
	String producto
	String descripcion
	BigDecimal cantidad
	//BigDecimal entregaAnterior
	BigDecimal valor
	VentaDet ventaDet

	BigDecimal asignado
	BigDecimal pendiente


	static belongsTo = [entrega: Entrega]

	static transients=['asignado','pendiente']

    static constraints = {
    	producto maxSize:20
    	descripcion maxSize:250
    	cantidad scale:3
    	//entregaAnterior scale:3
    	valor scale:4
    	ventaDet nullable:true
    }

    public EntregaDet(){}

    public EntregaDet(VentaDet vd){
    	ventaDet=vd
    	producto=vd.clave
    	descripcion=vd.descripcion
    	cantidad=vd.cantidad
    	valor=vd.subtotal
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



    /*
    def actualizar(){
		double canti=this.cantidad/this.ventaDet.getFactor();
		CantidadMonetaria precio=CantidadMonetaria.pesos(ventaDet.getPrecio());
		
		CantidadMonetaria impBruto=precio.multiply(canti).abs();
		CantidadMonetaria descuento=impBruto.multiply(ventaDet.getDescuento()/100);
		setValor(impBruto.subtract(descuento).amount());
	}
	*/
}

   	
   	

	

	
	
