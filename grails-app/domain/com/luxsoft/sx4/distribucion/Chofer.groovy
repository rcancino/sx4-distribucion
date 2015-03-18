package com.luxsoft.sx4.distribucion

class Chofer {

	String nombre

	String telefono

	Boolean activo=true

    static constraints = {
    	telefono nullable:true
    }

    String toString(){
    	return nombre
    }
}
