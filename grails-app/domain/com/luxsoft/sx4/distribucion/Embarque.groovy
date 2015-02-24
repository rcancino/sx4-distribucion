package com.luxsoft.sx4.distribucion

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat


/**
 *    
 *    
 * @author Ruben Cancino Ramos
 *
 */
@ToString(excludes='dateCreated,lastUpdated,id,version',includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='sucursal,documento')
class Embarque {

	String sucursal
	Long documento
	Date fecha
	Date cerrado
	Date salida
	Date regreso
	String comentario
	//Transporte transporte
	String chofer
	long kilometroInicial=0.0
	long kilometroFinal=0.0
	BigDecimal valor

	//Date recepcionDeSurtido


	Date dateCreated
	Date lastUpdated


	static hasMany = [partidas: Entrega]

    static constraints = {
    	comentario blak:true
    }

    static mapping = {
    	fecha type:'date'
    }

    BigDecimal valorCalculado(){
    	return partidas.sum (0.0,{it.valor})
    }

    BigDecimal kilos(){
    	return partidas.sum (0.0,{it.kilos})
    }
    

	def beforeInsert() {
		valor=valorCalculado()
	}

	def beforeUpdate() {
		valor=valorCalculado()
	}

	

}
