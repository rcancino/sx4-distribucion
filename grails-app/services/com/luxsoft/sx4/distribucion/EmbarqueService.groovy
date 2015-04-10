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
            //embarque.actualizar()
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
        embarque.actualizar()
        embarque.save failOnError:true,flush:true
        return embarque
    }

    def eliminarEntrega(Embarque embarque,Entrega entrega){

        def instruccion=InstruccionDeEntrega.findByEntrega(entrega)
        instruccion.entrega=null

        embarque.removeFromPartidas(entrega)
        embarque.actualizar()
        embarque.save (failOnError:true,flush:true)
        return embarque
    }

    def registrarSalida(Embarque embarque){
        embarque.salida=new Date()
        embarque.save flush:true
        return embarque
    }

    def cancelarSalida(Embarque embarque){
        embarque.salida=null
        embarque.save flush:true
        return embarque
    }

    def registrarRegreso(Embarque embarque){
        embarque.regreso=new Date()
        embarque.save flush:true
        return embarque
    }
    def cancelarRegreso(Embarque embarque){
        embarque.regreso=null
        embarque.save flush:true
        return embarque
    }
}

class EmbarqueException extends RuntimeException{
	String message
	Embarque embarque
}


