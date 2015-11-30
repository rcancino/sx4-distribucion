package com.luxsoft.sx4.distribucion

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat

@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='sucursal,pedido')
class Surtido {

    String sucursal
    
    Long pedido

    String cliente

    String nombre
    
    String tipo
    
    @BindingFormat('dd/MM/yyyy')
    Date fecha

    String asignado

    Date iniciado

    Date corteInicio

    Date corteFin

    String entrego

    Date entregado

    String vendedor

    Date pedidoCreado

    Date facturado
    String venta
    String tipoDeVenta
    String formaDeEntrega

    String origen

    Integer cortes

    List partidas

    Date dateCreated
    Date lastUpdated

    Boolean empacadoTerminado

    String forma

    Boolean puesto

    Date fechaPuesto

    SurtidoAnalisis analisis

    String comentario

    String clasificacion='SIN_VALE'

    Date revision

    String revisionUsuario

    Date cancelado
    String canceladoUser

    Date depurado
    String depuradoUser

    Date asignacionCorte

    static hasMany = [partidas: SurtidoDet,auxiliares:AuxiliarDeSurtido]


    static constraints = {
        cliente maxSize:20
        nombre()
        sucursal maxSize:20
        tipo inList:['ORDINARIO','PARCIAL','TRASLADO']
        asignado nullable:true,maxSize:50
        iniciado nullable:true
        corteInicio nullable:true
        corteFin nullable:true
        entrego nullable:true
        entregado nullable:true
        venta nullable:true,maxSize:20
        tipoDeVenta nullable:true,maxSize:10
        forma nullable:true,maxSize:10
        fechaPuesto nullable:true
        facturado nullable:true
        formaDeEntrega maxSize:20
        comentario nullable:true
        clasificacion maxSize:30,nullable:true
        revision nullable:true
        revisionUsuario nullable:true,maxSize:40
        cancelado nullable:true
        canceladoUser nullable:true,maxSize:50
        depurado nullable:true
        depuradoUser nullable:true,maxSize:50
        asignacionCorte nullable:true

    }

    static mapping = {
        //id generator:'guid'
        fecha type:'date'
        cancelado type:'date'
        partidas cascade: "all-delete-orphan"
        auxiliares cascade: "all-delete-orphan"
    }


    static transients = ['cortes','status','empacadoTerminado','analisis']

    def getCortes(){
        return partidas.sum{return it.corte?1:0}
    }

   def getStatus(){
        if(entregado && formaDeEntrega=='LOCAL'){
            return 'ENTREGADO'
        }

        if(revision && (formaDeEntrega=='ENVIO' || formaDeEntrega=='ENVIO_FORANEO')){
            return 'ENTREGADO'
        }

        if(getCortes()>0){
            if(corteFin!=null)
                return 'POR ENTREGAR'
            if(corteInicio!=null)
                return 'EN CORTE'
            else
                return 'PENDIENTE'
        }else{
            return asignado?'POR ENTREGAR':'PENDIENTE'
        }
    }

    def getEstado(){
         if(!iniciado){
            return 'PENDIENTE'
        }

        if(formaDeEntrega=='LOCAL'){
            if(iniciado){
                if(cancelado){
                    return 'CANCELADO'
                }else if(depurado){
                    return 'DEPURADO'
                }else if(entregado){
                    return 'ENTREGADO'
                }else if(iniciado && getCortes()==0 && revision ==null){
                return 'POR REVISAR'
                }else if(iniciado && getCortes()==0 && revision !=null){
                    return 'POR ENTREGAR'
                }else if(iniciado && getCortes()>0 && asignacionCorte==null){
                    return 'EN SURTIDO'
                }else if(iniciado && getCortes()>0 && asignacionCorte!=null && corteInicio== null){
                    return 'POR CORTAR'
                }else if(corteInicio!=null && corteFin==null && revision== null){
                    return 'EN CORTE'
                }else if(corteInicio!=null && corteFin!=null && !empacadoFinEstado && revision== null){
                    return 'EN EMPAQUE'
                }else if(corteInicio!=null && corteFin!=null && empacadoFinEstado && revision== null){
                    return 'POR REVISAR'
                }else if(corteInicio!=null && corteFin!=null && empacadoFinEstado && revision!= null){
                    return 'POR ENTREGAR'
                }
                return 'EN SURTIDO'
            }
           
        }
        if(formaDeEntrega=='ENVIO'){
            if(iniciado){
                if(cancelado){
                    return 'CANCELADO'
                }else if(depurado){
                    return 'DEPURADO'
                }else if(revision){
                    return 'REVISADO'
                }else if(iniciado && getCortes()==0 && entregado ==null){
                return 'POR ENTREGAR'
                }else if(iniciado && getCortes()==0 && entregado !=null){
                    return 'POR REVISAR'
                }else if(iniciado && getCortes()>0 && asignacionCorte==null){
                    return 'EN SURTIDO'
                }else if(iniciado && getCortes()>0 && asignacionCorte!=null && corteInicio== null){
                    return 'POR CORTAR'
                }else if(corteInicio!=null && corteFin==null && entregado== null){
                    return 'EN CORTE'
                }else if(corteInicio!=null && corteFin!=null && !empacadoFinEstado && entregado== null){
                    return 'EN EMPAQUE'
                }else if(corteInicio!=null && corteFin!=null && empacadoFinEstado && entregado== null){
                    return 'POR REVISAR'
                }else if(corteInicio!=null && corteFin!=null && empacadoFinEstado && revision!= null){
                    return 'POR ENTREGAR'
                }
                return 'EN SURTIDO'
            }
        }
    }



    def getEmpacadoTerminado(){
        if(cortes){
            def cortes=partidas.findAll{it.corte!=null}
            def found=cortes.find{
                it.corte.empacadoFin==null
            }
            if(found==null){
                return true
            }
            return true
        }
        return true
        
    }


    def getEmpacadoFinEstado(){
        if(getCortes()>0){
            def cortes=partidas.findAll{it.corte!=null}
            def found=cortes.find{
                it.corte.empacadoFin==null
            }
            if(found==null){
                return true
            }
            return false
        }
        return false
        
    }

    def getAnalisis(){
        if(analisis==null){
            analisis=new SurtidoAnalisis()
            analisis.surtido=this
        }
        return analisis
    }
    

}
