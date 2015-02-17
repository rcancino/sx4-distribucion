package com.luxsoft.sx4.distribucion



import grails.test.mixin.*
import spock.lang.*

@TestFor(SurtidoController)
@Mock(Surtido)
class SurtidoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.surtidoInstanceList
            model.surtidoInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.surtidoInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def surtido = new Surtido()
            surtido.validate()
            controller.save(surtido)

        then:"The create view is rendered again with the correct model"
            model.surtidoInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            surtido = new Surtido(params)

            controller.save(surtido)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/surtido/show/1'
            controller.flash.message != null
            Surtido.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def surtido = new Surtido(params)
            controller.show(surtido)

        then:"A model is populated containing the domain instance"
            model.surtidoInstance == surtido
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def surtido = new Surtido(params)
            controller.edit(surtido)

        then:"A model is populated containing the domain instance"
            model.surtidoInstance == surtido
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/surtido/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def surtido = new Surtido()
            surtido.validate()
            controller.update(surtido)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.surtidoInstance == surtido

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            surtido = new Surtido(params).save(flush: true)
            controller.update(surtido)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/surtido/show/$surtido.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/surtido/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def surtido = new Surtido(params).save(flush: true)

        then:"It exists"
            Surtido.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(surtido)

        then:"The instance is deleted"
            Surtido.count() == 0
            response.redirectedUrl == '/surtido/index'
            flash.message != null
    }
}
