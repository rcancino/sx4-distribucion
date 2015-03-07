package com.luxsoft.sx4.distribucion

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Corte)
@Build([Corte,AuxiliarDeCorte])
class CorteSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void 'Alta de corte'(){
    	given:'Un corte valido'
    	def corte=Corte.buildWithoutSave(asignado:'cortador')
    	when:'Salvamos la instancia'
    	corte.save()
    	then:'La entidad persiste correctamente en la base de datos'
    	corte.errors.errorCount==0
    	corte.id
    	Corte.get(corte.id).asignado=='cortador'

    }

    void "Agregar un auxiliar de corte"() {
    	given:'Un corte registrado'
    	def corte=Corte.build()
    	when:'Le acregamos auxiliares de corte'
    	def found=Corte.get(corte.id)
    	found.addToAuxiliares(nombre:'Auxiliar 1',tipo:'CORTADOR')
    	.addToAuxiliares(nombre:'Auxiliar 2',tipo:'CORTADOR')
    	and:'Salvamos la entidad'
    	found.save flush:true

    	then:'Los auxiliares son registrados en la base de datos'
    	Corte.get(found.id).auxiliares.size()==2

    }

    void "Agregar un auxiliar de empaque"(){
    	given:'Un corte registrado'
    	def corte=Corte.build()
    	when:'Le acregamos auxiliares de empaque'
    	def found=Corte.get(corte.id)
    	found.addToAuxiliares(nombre:'Auxiliar 1',tipo:'EMPACADOR')
    	.addToAuxiliares(nombre:'Auxiliar 2',tipo:'EMPACADOR')
    	and:'Salvamos la entidad'
    	found.save flush:true

    	then:'Los auxiliares son registrados en la base de datos'
    	Corte.get(found.id).auxiliares.size()==2
    }
}
