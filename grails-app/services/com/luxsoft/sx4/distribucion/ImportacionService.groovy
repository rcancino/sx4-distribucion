package com.luxsoft.sx4.distribucion

import org.apache.commons.lang.exception.ExceptionUtils;
import grails.transaction.Transactional
import grails.transaction.NotTransactional
import groovy.sql.Sql
import org.springframework.beans.BeanUtils

//@NotTransactional
class ImportacionService {
	
	static transactional = false
	
	def dataSource_importacion

	def grailsApplication

	def importarSurtidoSql="""
    	select 
		s.nombre as sucursal,p.folio as pedido,v.clave as cliente,v.nombre as nombre,date(p.fecha) as fecha
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,p.creado as pedidoCreado,p.PEDIDO_ID as origen
		from sx_ventas v
		join sx_pedidos p on v.pedido_id=p.pedido_id
		join sw_sucursales s on v.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(p.fecha)=:fecha
    """
    def importarSurtidoDetSql="""
    	select clave as producto,descripcion,cantidad,pedidodet_id as origen from sx_pedidosdet p 
    	where p.pedido_id=?
    """

    @NotTransactional
	def importarSurtido(Date fecha){
		def sucursal=grailsApplication.config.luxor.sx4.sucursal
		log.info "Importando surtidos de $sucursal  para el ${fecha.format('dd/MM/yyyy')}"
		def db = new Sql(dataSource_importacion)
		db.eachRow( [sucursal:'TACUBA',fecha:Sql.DATE(new Date())],importarSurtidoSql) { row->
			
			def surtido=Surtido.findBySucursalAndPedido(row.sucursal,row.pedido)
			if(!surtido){
				surtido=new Surtido(
					sucursal:row.sucursal,
					pedido:row.pedido,
					tipo:"ORDINARIO",
					cliente:row.cliente,
					nombre:row.NOMBRE,
					fecha:row.fecha,
					corte:false,
					origen:row.origen,
					pedidoCreado:row.pedidoCreado,
					vendedor:row.vendedor
				)
				db.eachRow(importarSurtidoDetSql,[row.origen]){ det->
					surtido.addToPartidas(producto:det.producto,descripcion:det.descripcion
						,cantidad:det.cantidad,origen:det.origen)
				}
			}
			surtido.facturado=row.facturado
			surtido.save(flush:true,failOnError:true)
		}

	}

	
	@NotTransactional
	def importarCortes(Date fecha){
		def sucursal=grailsApplication.config.luxor.sx4.sucursal
		log.info "Importando cortres de $sucursal  - ${fecha.format('dd/MM/yyyy')}"
		def sql="""
			select s.nombre as sucursal,p.clave as producto,p.descripcion,p.cantidad,p.cortes
			,p.CORTES_INSTRUCCION as instruccion,p.CORTES_PRECIO as precioCorte,ped.folio as pedido,s.nombre
			,v.DOCTO,ped.puesto,ped.FACTURAR,p.pedidodet_id as origen
			from sx_pedidosdet p 
			join sx_pedidos ped on p.pedido_id=ped.pedido_id
			join sx_ventas v on v.PEDIDO_ID=ped.PEDIDO_ID
			join sw_sucursales s on v.SUCURSAL_ID=s.SUCURSAL_ID
			where cortes>0 and date(ped.fecha)=:fecha and s.nombre=:sucursal 
			order by ped.creado asc
		"""
		def db = new Sql(dataSource_importacion)
		db.eachRow( [sucursal:sucursal,fecha:Sql.DATE(fecha)],sql) { row->
			def corte=Corte.findBySucursalAndOrigen(row.sucursal,row.origen)
			if(!corte){
				corte=new Corte(
					sucursal:row.sucursal,
					producto:row.producto,
					descripcion:row.descripcion,
					pedido:row.pedido,
					tipo:"ORDINARIO",
					cortes:row.cortes,
					cantidad:row.cantidad,
					origen:row.origen,
					percioCorte:row.precioCorte?:0.0,
					instruccion:row.instruccion?:'ND'
					)
				corte.save(flush:true,failOnError:true)
			}
		}
	}
	
	
    def importarEmbarque(String origen){
    	
    }
	
	
	

	
}
