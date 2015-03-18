package com.luxsoft.sx4.distribucion

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured

@Secured(["hasAnyRole('GERENTE','EMBARQUE')"])
class ChoferController {

    def index() { }


    def getChoferesAsJSON() {
		
		def list=Chofer.findAllByNombreIlikeAndActivo("%"+params.term+"%",true,[max:15,sort:"nombre",order:"desc"])

		
		list=list.collect{ c->
			def nombre="$c.nombre"
			[id:c.id,
			label:nombre,
			value:nombre,
			nombre:nombre]
		}

		def res=list as JSON
		render res
	}
}
