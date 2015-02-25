package com.luxsoft.sx4

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat


@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='id,clave')
class Producto {

	String clave
	String descripcion

	//Clasificacion del producto
	String linea
	String marca
	String clase
	String unidad

	BigDecimal ancho=0.0
	BigDecimal largo=0.0
	BigDecimal kilos=0.0
	Integer gramos=0.0
	Integer calibre=0
	Integer caras=0

	BigDecimal precioDeCredito
	BigDecimal precioDeContado

	Boolean inventariable=true
	Boolean activo=true
	String presentacion
	String color
	String acabado
	String modoDeVenta
	Boolean deLinea=true
	Boolean nacional=true
	

	Long origen

	Date dateCreated
	Date lastUpdated
	
	
	
	

    static constraints = {
    	clave size:3..20,unique:true
    	descripcion(size:4..300)
    	linea(size:1..50)
    	clase(size:1..50)
    	marca(size:1..50)
    	unidad(size:2..20)
    	precioDeCredito(scale:4)
    	precioDeContado(scale:4)
    	inventariable()
    	activo()
    	ancho scale:3
    	largo scale:3
    	kilos scale:3
    	presentacion nullable:true,maxSize:20
    	color nullable:true,maxSize:20
    	acabado nullable:true,maxSize:20
    	modoDeVenta nullable:true,maxSize:10
    	origen nullable:true

    }
}


