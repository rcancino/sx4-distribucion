package com.luxsoft.sx4.distribucion

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode

@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='nombre,tipo')
class AuxiliarDeCorte {

	String nombre
	String tipo

	Date dateCreated

	static belongsTo = [corte: Corte]

    static constraints = {
    	tipo inList:['CORTADOR','EMPACADOR']
    }

    
}
