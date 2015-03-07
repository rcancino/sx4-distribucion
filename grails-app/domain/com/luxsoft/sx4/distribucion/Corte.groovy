package com.luxsoft.sx4.distribucion

class Corte {

	SurtidoDet surtidoDet

	String producto
	String descripcion
	String sucursal
	Long pedido
	//Surtido surtido
	String tipo

	BigDecimal cantidad
	BigDecimal entregado=0 //? confunde no es necesaria
	BigDecimal pendiente=0 //? confunde no es necesaria
	BigDecimal precioCorte
	Integer cortes

	String instruccion

	Date inicio
	Date fin

	String asignado

	Date empacadoInicio
	Date empacadoFin
	String empacador

	String origen
	Date dateCreated
	Date lastUpdated

    /** Transiente properties ***/
    String status
    String statusCorte
    String statusEmpaque

    static hasMany = [auxiliares: AuxiliarDeCorte]


    static constraints = {
    	producto maxSize:20
    	descripcion maxSize:250
    	sucursal maxSize:20
    	tipo inList:['ORDINARIO','PARCIAL']
    	asignado nullable:true
    	inicio nullable:true
    	fin nullable:true
    	cantidad scale:4
    	entregado scale:4
    	pendiente scale:4
    	precioCorte scale:4,nullable:true
    	surtidoDet unique:true
    	empacadoInicio nullable:true
    	empacadoFin nullable:true
    	empacador nullable:true
    }

    static transients = ['status','surtidor','statusCorte','statusEmpaque']

    static mapping = {
        auxiliares cascade: "all-delete-orphan"
    }


    def getStatus(){
    	if(empacadoFin)
    		return 'EMPACADO'
    	else if(empacadoInicio)
    		return 'EN EMPACADO'
        else if(fin)
            return 'CORTADO'
    	else if(asignado)
    		return 'EN CORTE'
    	else 
    		return 'PENDIENTE'
    	
    }

    def getSurtidor(){
    	return surtidoDet.surtido.asignado
    }

    def getStatusCorte(){
        if(fin){
            return 'TERMINADO'
        }else if(inicio){
            return 'EN CORTE'
        }else
            return 'PENDIENTE'
    }

    def getStatusEmpaque(){
        if(empacadoFin)
            return 'EMPACADO'
        else if(empacadoInicio)
            return 'EN EMPACADO'
        else
            return 'PENDIENTE'
    }
}
