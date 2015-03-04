package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import groovy.sql.Sql
//import org.springframework.jdbc.core.BeanPropertyRowMapper
//import org.springframework.jdbc.core.JdbcTemplate
//import org.springframework.jdbc.core.SqlParameterValue
//import java.sql.Types


@Transactional
class ImportadorDeSurtidoService {

	static transactional = false

	def grailsApplication

	def dataSource_importacion

	

    String SQL_DETALLE="""
		select clave as producto,descripcion,cantidad,factoru as factor,kilos,pedidodet_id as origen from sx_pedidosdet p
    	where p.pedido_id=? 
    """

    String SQL_SECTORES="""
    	select s.sector
    	from SX_SECTORDET D join SX_SECTOR S on(s.sector_id=d.sector_id)
    	where d.clave=?
    """
   

    def SQL_MESTREO="""
    	select 
		s.nombre as sucursal,p.folio as pedido,v.clave as cliente,v.nombre as nombre,date(v.creado) as fecha
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,p.creado as pedidoCreado,p.PEDIDO_ID as origen
		,'ORDINARIO' as tipo
		,p.FENTREGA AS formaDeEntrega
		,v.docto as venta,v.origen as tipoDeVenta
		from sx_ventas v
		join sx_pedidos p on v.pedido_id=p.pedido_id
		join sw_sucursales s on v.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(v.fecha)=:fecha
    """

    def SQL_FACTURADOS="""
    	select 	'FAC' AS forma,s.nombre as sucursal,p.folio as pedido,v.clave as cliente,v.nombre as nombre,date(p.fecha) as fecha
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,p.creado as pedidoCreado,p.PEDIDO_ID as origen
		,'ORDINARIO' as tipo,p.fentrega as formaDeEntrega,p.puesto,p.tpuesto as fechaPuesto,p.parcial
		,v.docto as venta,v.origen as tipoDeVenta
		from sx_ventas v
		join sx_pedidos p on v.pedido_id=p.pedido_id
		join sw_sucursales s on v.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(v.fecha)=:fecha and p.puesto is false
    """

    def SQL_PUESTOS="""
    	select 	'PST' AS forma,s.nombre as sucursal,p.folio as pedido,v.clave as cliente,v.nombre as nombre,date(p.fecha) as fecha
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,p.creado as pedidoCreado,p.PEDIDO_ID as origen
		,'ORDINARIO' as tipo,p.fentrega as formaDeEntrega,p.puesto,p.tpuesto as fechaPuesto,p.parcial
		,v.docto as venta,v.origen as tipoDeVenta
		from sx_pedidos p
		left join sx_ventas v on v.pedido_id=p.pedido_id
		join sw_sucursales s on p.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(v.fecha)=:fecha and p.puesto is true
    """

    def SQL_TRASLADOS="""
    	select 	'TRD' AS forma,s.nombre as sucursal,p.documento as pedido,'1' as cliente,p.MODIFICADO_USR as nombre,date(p.fecha) as fecha
		,p.MODIFICADO_USR as vendedor,P.modificado as facturado,p.creado as pedidoCreado,p.TRASLADO_ID as origen
		,'TRASLADO' as tipo,'ENVIO' as  formaDeEntrega,p.fecha as tpuesto,false as parcial
		,p.DOCUMENTO as venta,'TRS' as tipoDeVenta
		from sx_traslados p
		join sw_sucursales s on P.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(p.fecha)=:fecha
    """


    def importar(Date fecha){
		importarFacturados fecha
	}

	def importarFacturados(Date fecha){
		log.debug "Importando pedidos facturados del "+fecha.format('dd/MM/yyyy')
		def sucursal=findSucursal()
		assert sucursal,'No hay sucursal registrada'
		def db = new Sql(dataSource_importacion)
		db.eachRow( [sucursal:sucursal,fecha:Sql.DATE(fecha)],SQL_FACTURADOS) { row->
			def surtido=Surtido.findByOrigen(row.origen)
			if(!surtido){
				surtido=new Surtido(row.toRowResult())

				db.eachRow(SQL_DETALLE,[row.origen]){ det->
					def sdet=new SurtidoDet(det.toRowResult())
					surtido.addToPartidas(sdet)
				}
				surtido.partidas.each{ sdet->
					def sectores=db.rows(SQL_SECTORES,[sdet.producto])
					sdet.sectores=sectores.collect{it.sector}.join(',')

				}
				surtido.save(flush:true,failOnError:true)
				event('registroDeSurtido', surtido)
			}
			if(!surtido.facturado && row.facturado){
				surtido.facturado=row.facturado
				surtido.save(flush:true,failOnError:true)
			}
		}

	}

	def importarPuestos(Date fecha){
		log.debug "Importando pedidos puestos del "+fecha.format('dd/MM/yyyy')
		def sucursal=findSucursal()
		assert sucursal,'No hay sucursal registrada'
		def db = new Sql(dataSource_importacion)
		db.eachRow( [sucursal:sucursal,fecha:Sql.DATE(fecha)],SQL_PUESTOS) { row->
			def surtido=Surtido.findByOrigen(row.origen)
			if(!surtido){
				surtido=new Surtido(row.toRowResult())

				db.eachRow(SQL_DETALLE,[row.origen]){ det->
					def sdet=new SurtidoDet(det.toRowResult())
					surtido.addToPartidas(sdet)
				}
				surtido.partidas.each{ sdet->
					def sectores=db.rows(SQL_SECTORES,[sdet.producto])
					sdet.sectores=sectores.collect{it.sector}.join(',')

				}
				surtido.save(flush:true,failOnError:true)
				event('registroDeSurtido', surtido)
			}
		}
	}

	def importarTraslados(Date fecha){
		log.debug "Importando surtido de traslados fecha:"+fecha.format('dd/MM/yyyy')
		def sucursal=findSucursal()
		assert sucursal,'No hay sucursal registrada'
		def db = new Sql(dataSource_importacion)
		db.eachRow( [sucursal:sucursal,fecha:Sql.DATE(fecha)],SQL_TRASLADOS) { row->
			def surtido=Surtido.findByOrigen(row.origen)
			if(!surtido){
				surtido=new Surtido(row.toRowResult())
				surtido.puesto=true
				/*
				db.eachRow(SQL_DETALLE,[row.origen]){ det->
					def sdet=new SurtidoDet(det.toRowResult())
					surtido.addToPartidas(sdet)
				}
				surtido.partidas.each{ sdet->
					def sectores=db.rows(SQL_SECTORES,[sdet.producto])
					sdet.sectores=sectores.collect{it.sector}.join(',')

				}
				*/
				surtido.save(flush:true,failOnError:true)
				//event('registroDeSurtido', surtido)
			}
		}

	}

	 String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }


}
