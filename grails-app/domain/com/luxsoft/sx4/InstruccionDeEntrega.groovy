package com.luxsoft.sx4

import com.luxsoft.sx4.distribucion.Entrega

class InstruccionDeEntrega {

	Venta venta
	String comentario
	Direccion direccion
	Entrega entrega

    static constraints = {
    	comentario nullable:true
    	entrega nullable:true
    }

    static embedded =['direccion']
}
