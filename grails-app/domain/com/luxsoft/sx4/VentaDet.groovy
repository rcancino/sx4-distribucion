package com.luxsoft.sx4

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat


@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='id,producto,cantidad')
class VentaDet {

	Producto producto
	String origen
	String clave
	String descripcion
	String unidad
	BigDecimal factor
	BigDecimal kilos
	BigDecimal cantidad
	BigDecimal precioDeLista
	BigDecimal precio
	BigDecimal importe
	BigDecimal descuento
	BigDecimal descuentoOrigen
	BigDecimal importeDescuento
	BigDecimal importeNeto
	BigDecimal importeCortes
	BigDecimal subtotal 
	BigDecimal cortes
	BigDecimal precioCortes
	String    instruccionDeCortes



    static constraints = {
    	origen nullable:true
    	clave maxSize:20
    	descripcion maxSize:300
    	unidad maxSize:3
    	kilos scale:3
    	cantidad scale:3
		instruccionDeCortes nullable:true,maxSize:50
    }

    static belongsTo=[venta:Venta]
}
