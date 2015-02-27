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

	def SQL_MESTREO="""
    	select 
		s.nombre as sucursal,p.folio as pedido,v.clave as cliente,v.nombre as nombre,date(p.fecha) as fecha
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,p.creado as pedidoCreado,p.PEDIDO_ID as origen
		,'ORDINARIO' as tipo
		,v.docto as venta,v.origen as tipoDeVenta
		from sx_ventas v
		join sx_pedidos p on v.pedido_id=p.pedido_id
		join sw_sucursales s on v.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(p.fecha)=:fecha
    """

    String SQL_DETALLE="""
		select clave as producto,descripcion,cantidad,pedidodet_id as origen from sx_pedidosdet p 
    	where p.pedido_id=? 
    """

    String SQL_SECTORES="""
    	select s.sector
    	from SX_SECTORDET D join SX_SECTOR S on(s.sector_id=d.sector_id)
    	where d.clave=?
    """

	def importar(Date fecha){
		def sucursal=findSucursal()
		log.debug 'Importando surtidos para '+fecha.format('dd/MM/yyyy')+ " Sucursal: "+sucursal
		
		def db = new Sql(dataSource_importacion)
		db.eachRow( [sucursal:sucursal,fecha:Sql.DATE(fecha)],SQL_MESTREO) { row->
			//println 'Procesando: '+row
			def surtido=Surtido.findByOrigen(row.origen)
			if(!surtido){
				surtido=new Surtido(row.toRowResult())
				db.eachRow(SQL_DETALLE,[row.origen]){ det->
					surtido.addToPartidas(
						producto:det.producto
						,descripcion:det.descripcion
						,cantidad:det.cantidad
						,origen:det.origen)
				}
				surtido.partidas.each{ sdet->
					//Buscar sectores
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


    

    String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }


}
