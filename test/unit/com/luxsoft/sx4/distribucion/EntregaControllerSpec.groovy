package com.luxsoft.sx4.distribucion



import grails.test.mixin.*
import spock.lang.*

@TestFor(EntregaController)
@Mock(Entrega)
class EntregaControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.entregaInstanceList
            model.entregaInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.entregaInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def entrega = new Entrega()
            entrega.validate()
            controller.save(entrega)

        then:"The create view is rendered again with the correct model"
            model.entregaInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            entrega = new Entrega(params)

            controller.save(entrega)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/entrega/show/1'
            controller.flash.message != null
            Entrega.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def entrega = new Entrega(params)
            controller.show(entrega)

        then:"A model is populated containing the domain instance"
            model.entregaInstance == entrega
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def entrega = new Entrega(params)
            controller.edit(entrega)

        then:"A model is populated containing the domain instance"
            model.entregaInstance == entrega
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/entrega/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def entrega = new Entrega()
            entrega.validate()
            controller.update(entrega)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.entregaInstance == entrega

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            entrega = new Entrega(params).save(flush: true)
            controller.update(entrega)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/entrega/show/$entrega.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/entrega/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def entrega = new Entrega(params).save(flush: true)

        then:"It exists"
            Entrega.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(entrega)

        then:"The instance is deleted"
            Entrega.count() == 0
            response.redirectedUrl == '/entrega/index'
            flash.message != null
    }
}
