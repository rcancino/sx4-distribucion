package com.luxsoft.sx4.distribucion



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class SurtidoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Surtido.list(params), model:[surtidoInstanceCount: Surtido.count()]
    }

    def show(Surtido surtidoInstance) {
        respond surtidoInstance
    }

    def create() {
        respond new Surtido(params)
    }

    @Transactional
    def save(Surtido surtidoInstance) {
        if (surtidoInstance == null) {
            notFound()
            return
        }

        if (surtidoInstance.hasErrors()) {
            respond surtidoInstance.errors, view:'create'
            return
        }

        surtidoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'surtido.label', default: 'Surtido'), surtidoInstance.id])
                redirect surtidoInstance
            }
            '*' { respond surtidoInstance, [status: CREATED] }
        }
    }

    def edit(Surtido surtidoInstance) {
        respond surtidoInstance
    }

    @Transactional
    def update(Surtido surtidoInstance) {
        if (surtidoInstance == null) {
            notFound()
            return
        }

        if (surtidoInstance.hasErrors()) {
            respond surtidoInstance.errors, view:'edit'
            return
        }

        surtidoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Surtido.label', default: 'Surtido'), surtidoInstance.id])
                redirect surtidoInstance
            }
            '*'{ respond surtidoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Surtido surtidoInstance) {

        if (surtidoInstance == null) {
            notFound()
            return
        }

        surtidoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Surtido.label', default: 'Surtido'), surtidoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'surtido.label', default: 'Surtido'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
