package com.luxsoft.sx4.distribucion

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString


@EqualsAndHashCode(includes='producto,origen')
@ToString(includes='producto,descripcion,cantidad,entregado,pendiente',includeNames=true,includePackage=false)
class SurtidoDet {

	String producto
	String descripcion
	BigDecimal cantidad
	BigDecimal entregado=0 //? confunde no es necesaria
	BigDecimal pendiente=0 //? confunde no es necesaria
	
	Date fechaHora

	String origen

	String sectores

	static belongsTo = [surtido: Surtido]

	static hasOne = [corte: Corte]

    static constraints = {
    	producto maxSize:20
    	descripcion maxSize:250
    	cantidad scale:4
    	entregado scale:4
    	pendiente scale:4
    	fechaHora nullable:true
    	corte nullable:true
    	sectores nullable:true
    }
}
