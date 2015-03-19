package com.luxsoft.sx4

class InstruccionDeEntrega {

	Venta venta
	String comentario
	Direccion direccion

    static constraints = {
    	comentario nullable:true
    }

    static embedded =['direccion']
}
