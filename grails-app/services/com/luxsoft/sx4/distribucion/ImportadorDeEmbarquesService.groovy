package com.luxsoft.sx4.distribucion


import grails.transaction.Transactional
import grails.transaction.NotTransactional
import groovy.sql.Sql


@Transactional
class ImportadorDeEmbarquesService {

	static transactional = false

	def grailsApplication
    def dataSource_importacion

	def SQL_MESTREO="""
        select e.embarque_id as origen
        ,e.fecha
        ,e.sucursal
        ,e.chofer
        ,e.comentario
        ,e.documento
        ,e.creado
        ,e.modificado
        ,e.regreso
        ,e.salida
        ,e.valor
        ,e.TRANSPORTE_ID
        ,t.DESCRIPCION as transporte
        ,'TEST' as comentario
        from sx_embarques e
        join sx_transportes t on (e.TRANSPORTE_ID=t.TRANSPORTE_ID)
        where date(e.fecha)=:fecha
    """

    def SQL_DETALLE="""
        select 
        e.entrega_id as origen,
        e.arribo, 
        e.cantidad,
        e.clave as cliente,
        e. nombre as nombre,
        v.DOCTO as documento,
        v.fecha as fechaDeDocumento,
        v.origen as tipoDeDocumento,
        e.total_docto as totalDocumento,
        e.comentario,
        e.comision,
        e.documento as folio,
        e.COMISION_IMP as importeComision,
        e.kilos,
        e.paquetes,
        e.parcial,
        e.por_cobrar as porCobrar,
        e.recepcion,
        e.recibio,
        e.surtido as recepcionDeSurtido,
        e.valor,
        e.instruccion_id as instruccion,
        e.venta_id as ventaOrigen,
        e.comision_comentario as comentarioDeComision,
        e.COMISION_POR_TON as comisionPorTonelada,
        e.COMISION_FECHA as comisionFecha
        from sx_entregas e
        join sx_ventas v on e.VENTA_ID=v.CARGO_ID
        where e.EMBARQUE_ID =?
    """

    def SQL_ENTREGADET="""
        select 
        e.entregadet_id as origen,
        e.VENTADET_ID as documentoOrigen,
        e.producto as producto,
        e.descripcion,
        e.cantidad as cantidad,
        e.ENTREGADO_ANTERIOR as entregaAnterior,
        e.valor
        from sx_entregas_det e
        where e.ENTREGA_ID =?

    """

	@NotTransactional
    def importar(Date fecha) {
        log.debug 'Importando embarques para '+fecha.format('dd/MM/yyyy')
        def db = new Sql(dataSource_importacion)
            db.eachRow( [fecha:Sql.DATE(fecha)],SQL_MESTREO) { row->
                //println 'Procesando: '+row
            def embarque=Embarque.findByOrigen(row.origen)
            if(!embarque){
                embarque=new Embarque(row.toRowResult())
                db.eachRow(SQL_DETALLE,[row.origen]){ det->
                    def entrega=new Entrega(det.toRowResult())
                    db.eachRow(SQL_ENTREGADET,[det.origen]){ ed->
                        def entregaDet=new EntregaDet(ed.toRowResult())
                        entrega.addToPartidas(entregaDet)
                    }
                    embarque.addToPartidas(entrega)
                }
                embarque.save(flush:true,failOnError:true)
                event('registroDeembarque', embarque)
            }
        }
            
    }


    	
    

    String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }
}
