package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import com.luxsoft.sx4.Folio
import org.apache.commons.lang.exception.ExceptionUtils
import com.luxsoft.sx4.*

@Transactional
class EmbarqueService {

	def springSecurityService

    Embarque create(Embarque embarque){
    	
    	try {
            
            def serie="EMBARQUES"
            def folio=Folio.findBySerie(serie)
            if(!folio) 
                folio=new Folio(serie:serie,folio:1).save()
            embarque.documento=folio.next()
    		embarque=embarque.save flush:true,failOnError:true
    		event('altaDeEmbarque',embarque)
    		return embarque
    	}
    	catch(grails.validation.ValidationException e) {
            //def msg=ExceptionUtils.getRootCauseMessage(e)
    		throw new EmbarqueException(message:e.message,embarque:embarque)
    	}
    	
    }

    def agregarEntregaDeVentas(Embarque embarque,def ventas){
        ventas.each{
            def ins=InstruccionDeEntrega.get(it)
            def venta=ins.venta
            def entrega=new Entrega(
                documento:venta.documento,
                tipoDeDocumento:venta.tipoVenta,
                fechaDeDocumento:venta.fecha,
                documentoOrigen:venta.id,
                totalDocumento:venta.total,
                cliente:venta.cliente.clave,
                nombre:venta.nombre,
                kilos :venta.kilos,
                cantidad:venta.partidas.sum (0.0,{it.cantidad}),
                valor:venta.total
                )
            embarque.addToPartidas(entrega)
            entrega.validate()
            if(entrega.hasErrors()){
                throw new RuntimeException("Errores de validacion en entrega: "+entrega.errors)
            }
            ins.entrega=entrega
            ins.save flush:true
        }
        embarque.save failOnError:true
        return embarque
    }
}

class EmbarqueException extends RuntimeException{
	String message
	Embarque embarque
}


