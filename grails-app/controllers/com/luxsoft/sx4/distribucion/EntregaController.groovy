package com.luxsoft.sx4.distribucion



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import org.grails.databinding.BindingFormat
import com.luxsoft.sx4.*


@Secured(["hasAnyRole('GERENTE')"])
@Transactional(readOnly = true)
class EntregaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Entrega.list(params), model:[entregaInstanceCount: Entrega.count()]
    }

    def show(Entrega entregaInstance) {
        respond entregaInstance
    }

    def create() {
        respond new Entrega(params)
    }

    @Transactional
    def save(Entrega entregaInstance) {
        if (entregaInstance == null) {
            notFound()
            return
        }

        if (entregaInstance.hasErrors()) {
            respond entregaInstance.errors, view:'create'
            return
        }

        entregaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'entrega.label', default: 'Entrega'), entregaInstance.id])
                redirect entregaInstance
            }
            '*' { respond entregaInstance, [status: CREATED] }
        }
    }

    def edit(Entrega entregaInstance) {
        respond entregaInstance
    }

    @Transactional
    def update(Entrega entregaInstance) {
        if (entregaInstance == null) {
            notFound()
            return
        }

        if (entregaInstance.hasErrors()) {
            respond entregaInstance.errors, view:'edit'
            return
        }

        entregaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Entrega.label', default: 'Entrega'), entregaInstance.id])
                redirect entregaInstance
            }
            '*'{ respond entregaInstance, [status: OK] }
        }
    }

    @Transactional
    def asignarFechaHora(Entrega entregaInstance){
        if (entregaInstance == null) {
            notFound()
            return
        }
        def tipo=params.tipo
        def time=params.date('fechaHora','dd/MM/yyyy HH:mm')
        if(tipo=='arribo'){
            
            entregaInstance.arribo=time
            entregaInstance.save flush:true
            flash.message="Arribo registrado "+params.fechaHora

        }else if(tipo=='recepcion'){
            if(!entregaInstance.arribo){
                flash.message="No puede asignar la recepcion antes del arribo"
                redirect controller:'embarque',action:'show',params:[id:entregaInstance.embarque.id]
                return
            }
            if(time){
                def dif=time.getTime()-entregaInstance.arribo.getTime()
                if(dif< ( (60*1000)*5) ){
                    flash.message="Recepción inconsitente (Menor a 5 minutos posteriores al arribo)"
                    redirect controller:'embarque',action:'show',params:[id:entregaInstance.embarque.id]
                    return
                }
            }
            
            entregaInstance.recepcion=time
            entregaInstance.save flush:true
            flash.message="Recepcion registrada "+params.fechaHora
        }
        redirect controller:'embarque',action:'show',params:[id:entregaInstance.embarque.id]
    }

    @Transactional
    def delete(Entrega entregaInstance) {

        if (entregaInstance == null) {
            notFound()
            return
        }

        entregaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Entrega.label', default: 'Entrega'), entregaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrega.label', default: 'Entrega'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


     @Transactional
     def registrarParcial(Entrega entregaInstance){

        if (entregaInstance == null) {
            notFound()
            return
        }

        if(entregaInstance.parcial){
            flash.message="La entrega ya esta registrada como parcial"
            respond entregaInstance, view:'show'
            return
        }
        flash.message = "Entrega $entregaInstance.id registrada para su atención parcial"
        def instruccion=InstruccionDeEntrega.findByEntrega(entregaInstance)
        assert instruccion,'Debe existir instruccion de entrega para la entrega: '+entregaInstance.id
        
        instruccion.venta.partidas.each{
            if(it.producto.inventariable){
                def det=new EntregaDet(it)
                def asignado= EntregaDet.executeQuery("select sum(e.cantidad) from EntregaDet e where e.ventaDet=?",[it])[0]?:0.0
                det.cantidad=it.cantidad.abs()-asignado
                entregaInstance.addToPartidas(det)
            }
        }
        entregaInstance.parcial=true
        entregaInstance.actualizar()
        entregaInstance.save failOnError:true
        
        respond entregaInstance, view:'show'
     }

     @Transactional
     def cancelarParcial(Entrega entregaInstance){
        if (entregaInstance == null) {
            notFound()
            return
        }

        if(!entregaInstance.parcial){
            flash.message="La entrega NO esta registrada como parcial"
            respond entregaInstance, view:'show'
            return
        }
        entregaInstance.partidas.clear()
        entregaInstance.parcial=false
        entregaInstance.actualizar()
        entregaInstance.save failOnError:true,flush:true
        entregaInstance.embarque.actualizar()
        flash.message = "Cancelacion de entrega parcial: $entregaInstance.id "
        respond entregaInstance, view:'show'
     }
}

class AsignarFechaHoraCommand{
    
    @BindingFormat('dd/MM/yyyy')
    Date fechaHora

    String tipo
}
