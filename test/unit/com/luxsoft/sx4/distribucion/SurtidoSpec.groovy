package com.luxsoft.sx4.distribucion

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Surtido)
@Build([Surtido,AuxiliarDeSurtido])
class SurtidoSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Alta de una orden de surtido"() {
    	given:'Un surtido nuevo'
    	def surtido=Surtido.buildWithoutSave(pedido:450)

    	when:'Salvamos el surtido'
    	surtido.save()

    	then:'El surtido persite exitosamente en la base de datos'
    	surtido.errors.errorCount==0
    	surtido.id
    	Surtido.get(surtido.id).pedido==450
    }

    void "Alta de auxiliares de surtido"(){
        given:'Un surtido existente'
        def surtido=Surtido.build(pedido:99)

        when:'Agregamos un auxiliar'
        def found=Surtido.get(surtido.id)
        assert found
        found.addToAuxiliares(nombre:"Auxiliar 1")
        found.addToAuxiliares(nombre:"Auxiliar 2")
        found.save flush:true

        then:'Los auxiliares son persistidos en la base de datos'
        Surtido.get(found.id).auxiliares.size()==2

    }
}
