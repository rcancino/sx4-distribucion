package com.luxsoft.sx4.distribucion

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Surtido)
@Build([Surtido])
class SurtidoSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Alta de una orden de surtido"() {
    	given:'Un surtido nuevo'
    	def surtido=Surtido.buildWithoutSave(pedido:'450')

    	when:'Salvamos el surtido'
    	surtido.save()

    	then:'El surtido persite exitosamente en la base de datos'
    	surtido.errors.errorCount==0
    	surtido.id
    	Surtido.get(surtido.id).pedido=='450'
    }
}
