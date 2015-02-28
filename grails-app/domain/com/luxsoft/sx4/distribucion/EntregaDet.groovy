package com.luxsoft.sx4.distribucion


import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode


@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='producto,origen')
class EntregaDet {

	String origen 
	String documentoOrigen //ventaDet
	String producto
	String descripcion
	BigDecimal cantidad
	BigDecimal entregaAnterior
	BigDecimal valor


	static belongsTo = [entrega: Entrega]

    static constraints = {
    	producto maxSize:20
    	descripcion maxSize:250
    	cantidad scale:3
    	entregaAnterior scale:3
    	valor scale:4
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

   	
   	

	

	
	
