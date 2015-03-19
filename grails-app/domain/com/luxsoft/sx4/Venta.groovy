package com.luxsoft.sx4

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat

@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='origen')
class Venta {

	String origen
	Sucursal sucursal
	String nombre
	String rfc
	Cliente cliente
	Date fecha
	Long documento
	String tipoVenta
	
	BigDecimal importeComisionTarjeta
	BigDecimal descuento
	BigDecimal flete
	BigDecimal importe
	BigDecimal impuesto
	BigDecimal total


	String moneda
	BigDecimal tc=1
	String socio
	String formaPago
	String formaEntrega
	Boolean parcial
	Boolean misma
	String comprador
	Date fechaEntrega
	Date puestoFecha
	Boolean anticipo
	Date impreso
	Date creadoPedido
	Date creadoFactura
	String creadoUser
	String updateUser
	String comentario
	String comentario2
	String instruccion_id
	List partidas
	

	static hasOne = [instruccionDeEntrega:InstruccionDeEntrega]

	static hasMany=[partidas:VentaDet]

	Date dateCreated
	Date lastUpdated

    static constraints = {
    	
		rfc maxSize:13
		tipoVenta maxSize:10
		moneda maxSize:5
		tc scale:4
		socio nullable:true,maxSize:50
		formaPago maxSize:20
		formaEntrega maxSize:20
		origen nullable:true
    	instruccionDeEntrega nullable:true
		comprador nullable:true, maxSize:50
		puestoFecha nullable:true
	    impreso nullable:true
		creadoPedido nullable:true
		creadoFactura nullable:true
		creadoUser nullable:true
		updateUser nullable:true
		comentario nullable:true
		comentario2 nullable:true
		instruccion_id nullable:true
    }

    static mapping={
    	partidas cascade:"all-delete-orphan"
    }
    
}
