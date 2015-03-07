package com.luxsoft.sx4.distribucion

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode

@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='nombre')
class AuxiliarDeSurtido {

	String nombre

	Date dateCreated

	static belongsTo = [surtido: Surtido]

    static constraints = {
    }

    static mappedBy = [surtido: 'none']
}
