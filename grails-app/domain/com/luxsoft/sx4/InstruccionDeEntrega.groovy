package com.luxsoft.sx4

import com.luxsoft.sx4.distribucion.Entrega
import com.luxsoft.sx4.distribucion.Surtido

class InstruccionDeEntrega {

	Venta venta
	String comentario
	Direccion direccion
	Entrega entrega
	Surtido surtido

	static transients = ['surtido']

    static constraints = {
    	comentario nullable:true
    	entrega nullable:true
    }

    static embedded =['direccion']

    def getSurtido(){
    	return Surtido.findByTipoDeVentaAndVenta(venta.tipoVenta,venta.documento)
    }
}
