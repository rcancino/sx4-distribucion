package com.luxsoft.sx4

class Cliente {

	String clave
	String nombre
	String rfc='XAXX010101000'

    static constraints = {
    	clave maxSize:15
    	rfc maxSize:13
    }
}
