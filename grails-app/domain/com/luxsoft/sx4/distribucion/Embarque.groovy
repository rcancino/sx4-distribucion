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
	Chofer chofer
	long kilometroInicial=0.0
	long kilometroFinal=0.0
	BigDecimal valor=0.0
	BigDecimal kilos=0.0

	String origen


	Date dateCreated
	Date lastUpdated



	static hasMany = [partidas: Entrega]

    static constraints = {
    	comentario blak:true
    	kilos scale:4
    	origen nullable:true
    	comentario nullable:true
    	cerrado nullable:true
    	salida nullable:true
    	regreso nullable:true
    }

    
    static mapping = {
    	fecha type:'date'
		partidas cascade: "all-delete-orphan"
	}


    BigDecimal valorCalculado(){
    	return partidas.sum (0.0,{it.valor})
    }

    BigDecimal kilosCalculados(){
    	return partidas.sum (0.0,{it.kilos})
    }

	def beforeInsert() {
		actualizar()
	}

	def beforeUpdate() {
		actualizar()
	}

	def actualizar(){
		if(partidas){
			valor=valorCalculado()
			kilos=kilosCalculados()
			
		}
	}

}


