package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import groovy.sql.Sql
import groovy.time.TimeCategory
import groovy.time.TimeDuration
//import org.springframework.jdbc.core.BeanPropertyRowMapper
//import org.springframework.jdbc.core.JdbcTemplate
//import org.springframework.jdbc.core.SqlParameterValue
//import java.sql.Types

import org.apache.commons.lang.exception.ExceptionUtils

@Transactional
class ImportadorDeSurtidoService {

	static transactional = false

	def grailsApplication

	def dataSource_importacion

	def importadorDeCorteService
	
    String SQL_DETALLE="""
		select clave as producto,descripcion,cantidad,factoru as factor,kilos,pedidodet_id as origen 
		,p.convale  as conVale
		from sx_pedidosdet p
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
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,v.creado as pedidoCreado,p.PEDIDO_ID as origen
		,'ORDINARIO' as tipo,p.fentrega as formaDeEntrega,p.puesto,p.tpuesto as fechaPuesto,p.parcial
		,v.docto as venta,v.origen as tipoDeVenta,p.clasificacion_vale as clasificacion,v.comentario
		from sx_ventas v
		join sx_pedidos p on v.pedido_id=p.pedido_id
		join sw_sucursales s on v.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(v.fecha)=:fecha and p.puesto is false
    """


    def SQL_PUESTOS="""
    	select 	'PST' AS forma,s.nombre as sucursal,p.folio as pedido,p.clave as cliente,p.nombre as nombre,date(p.fecha) as fecha
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,p.tpuesto as pedidoCreado,p.PEDIDO_ID as origen
		,'ORDINARIO' as tipo,p.fentrega as formaDeEntrega,p.puesto,p.tpuesto as fechaPuesto,p.parcial
		,v.docto as venta,v.origen as tipoDeVenta,p.clasificacion_vale as clasificacion,p.comentario
		from sx_pedidos p
		left join sx_ventas v on v.pedido_id=p.pedido_id
		join sw_sucursales s on p.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(p.modificado)=:fecha and p.puesto is true
    """

    def SQL_PUESTOS_POR_ID="""
    	select 	'PST' AS forma,s.nombre as sucursal,p.folio as pedido,p.clave as cliente,p.nombre as nombre,date(p.fecha) as fecha
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,p.creado as pedidoCreado,p.PEDIDO_ID as origen
		,'ORDINARIO' as tipo,p.fentrega as formaDeEntrega,p.puesto,p.tpuesto as fechaPuesto,p.parcial
		,v.docto as venta,v.origen as tipoDeVenta,p.clasificacion_vale as clasificacion,v.comentario
		from sx_pedidos p
		left join sx_ventas v on v.pedido_id=p.pedido_id
		join sw_sucursales s on p.SUCURSAL_ID=s.SUCURSAL_ID
		where p.pedido_id=?
    """

    def SQL_TRASLADOS="""
        select 'SOL' AS forma,s.nombre as sucursal,p.documento as pedido
        ,ifnull((SELECT u.username FROM sx_usuarios u where concat(RTRIM(u.first_name),' ',u.last_name)=p.creado_usr),'N/D' ) as cliente
        ,p.CREADO_USR as nombre,date(p.fecha) as fecha
		,p.CREADO_USR as vendedor,P.modificado as facturado,p.creado as pedidoCreado,p.SOL_ID as origen
		,'TRASLADO' as tipo,'ENVIO' as  formaDeEntrega,p.fecha as tpuesto,false as parcial
		,p.DOCUMENTO as venta,'SOL' as tipoDeVenta,p.clasificacion
		,(SELECT y.nombre FROM sx_solicitud_traslados x join sw_sucursales y on(y.SUCURSAL_ID=x.SUCURSAL_ID) where x.SOL_ID=p.sol_id) as comentario
		from sx_solicitud_traslados p
		join sw_sucursales s on P.ORIGEN_ID=s.SUCURSAL_ID		
		where s.nombre=:sucursal and date(p.fecha)=:fecha and p.clasificacion not in('CONTRAVALE')
    """

    def SQL_TRASLADOS_DET="""
    	select concat(p.sol_id,'-',convert(p.renglon,char)) as origen 
    	,P.solicitado as cantidad,u.factor
    	,(p.solicitado/u.factor*x.kilos) as kilos
    	,X.clave as producto,X.descripcion
    	from sx_solicitud_trasladosdet p JOIN sx_productos X ON(X.PRODUCTO_ID=P.PRODUCTO_ID) join sx_unidades u on(u.UNIDAD=x.UNIDAD)
    	where P.SOL_ID=?
    """

    /*
	-- columna de usuario clave para el surtido de soles en el campo de cliente
	,(SELECT u.username FROM sx_usuarios u where concat(RTRIM(u.first_name),' ',u.last_name)=p.creado_usr) as username
	*/

    def SQL_CANCELADOS="""
    	SELECT 'FAC' as tipo,v.pedido_id as origen,c.creado_userid as cancelado_user,c.creado as cancelado,v.docto as venta FROM sx_cxc_cargos_cancelados c join sx_ventas v on (v.CARGO_ID=c.CARGO_ID) where date(c.fecha)=CURRENT_DATE 
		union
		SELECT 'SOL' as tipo,s.sol_id as origen,s.cancelacion_usr as cancelado_user,s.modificado as cancelado 
		,s.documento as venta FROM sx_solicitud_traslados s where date(s.modificado)=CURRENT_DATE 
		and no_atender is true
		union
		SELECT 'PED' as tipo,c.pedido_id as origen,c.modificado_usr as cancelado_user,c.creado as cancelado,c.folio as venta FROM sx_pedidos_borrados c where date(c.CREADO)=CURRENT_DATE and c.puesto is true
    """

    def SQL_TRANSFORMACIONES="""
    	SELECT 'TRS' AS forma,'Transformacion' as sucursal,p.documento as pedido
        ,ifnull((SELECT u.username FROM sx_usuarios u where concat(RTRIM(u.first_name),' ',u.last_name)=p.creado_userid),'INVENTARIOS' ) as cliente
        ,'INVENTARIOS' as nombre,date(p.fecha) as fecha
		,'INVENTARIOS' as vendedor,CURRENT_TIMESTAMP as facturado,CURRENT_TIMESTAMP as pedidoCreado,p.TRANSFORMACION_ID as origen
		,'TRANSFORMACION' as tipo,'LOCAL' as  formaDeEntrega,p.fecha as tpuesto,false as parcial
		,p.DOCUMENTO as venta,'TRS' as tipoDeVenta,'SIN_VALE'
		,P.COMENTARIO as comentario
		FROM 
		sx_transformaciones p join sw_sucursales s on (p.sucursal_id=s.SUCURSAL_ID)
		where s.nombre=:sucursal and date(P.fecha)=:fecha AND CLASE ='Transformacion'
    """

    def SQL_TRANSFORMACIONES_DET="""
    	select p.INVENTARIO_ID as origen 
    	,(P.cantidad*-1) as cantidad,u.factor
    	,((p.cantidad*-1)/u.factor*x.kilos) as kilos
    	,X.clave as producto,X.descripcion
    	from sx_inventario_trs p JOIN sx_productos X ON(X.PRODUCTO_ID=P.PRODUCTO_ID) join sx_unidades u on(u.UNIDAD=x.UNIDAD)
    	where P.TRANSFORMACION_ID=?  and DESTINO_ID is not null
    """


    def importar(Date fecha){
    	
    	importarFacturados fecha
	}

	def importarOtros(Date fecha){

		importarTraslados fecha
    	importarTransformaciones fecha
    	importarPuestos fecha
	}

	def importarFacturados(Date fecha){
		log.debug "Importando pedidos facturados del "+fecha.format('dd/MM/yyyy')
		def sucursal=findSucursal()
		assert sucursal,'No hay sucursal registrada'
		def db = new Sql(dataSource_importacion)
		
		db.eachRow( [sucursal:sucursal,fecha:Sql.DATE(fecha)],SQL_FACTURADOS) { row->

			
			def surtido=Surtido.findByOrigenAndVenta(row.origen,row.venta)
			//def surtido=Surtido.findByOrigen(row.origen)
			if(!surtido ||(surtido && surtido.cancelado && surtido.venta!=row.venta.toString() && !surtido.reimportado)){
				//if(!surtido ){
					if (surtido && surtido.cancelado){
					surtido.reimportado=true
					surtido.save(flush:true,failOnError:true)

				}
				

				surtido=new Surtido(row.toRowResult())
					surtido.autorizacionSurtido=new Date()
     				surtido.autorizoSurtir='FAC'

    
				db.eachRow(SQL_DETALLE,[row.origen]){ det->
					if(!det.conVale || (det.conVale && surtido.clasificacion=='EXISTENCIA_VENTA')){
						def sdet=new SurtidoDet(det.toRowResult())
						surtido.addToPartidas(sdet)	
					}
				}

				
				if(surtido.partidas){

					surtido.partidas.each{ sdet->
					def sectores=db.rows(SQL_SECTORES,[sdet.producto])
					sdet.sectores=sectores.collect{it.sector}.join(',')

				}

					surtido.save(flush:true,failOnError:true)
					importadorDeCorteService.importar(surtido)
					event('registroDeSurtido', surtido)
				}
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
			if(!surtido ||(surtido && surtido.cancelado && surtido.venta!=row.venta.toString() && !surtido.reimportado)){
			//if(!surtido){

				if (surtido && surtido.cancelado){
					surtido.reimportado=true
					surtido.save(flush:true,failOnError:true)

				}

				surtido=new Surtido(row.toRowResult())


				db.eachRow(SQL_DETALLE,[row.origen]){ det->
					if(!det.conVale || (det.conVale && surtido.clasificacion=='EXISTENCIA_VENTA')){

						def sdet=SurtidoDet.findByOrigen(det.origen)

						if(!sdet){

							 sdet=new SurtidoDet(det.toRowResult())
							surtido.addToPartidas(sdet)	
						}

						
					}
				}

				
				if(surtido.partidas){

					surtido.partidas.each{ sdet->
					def sectores=db.rows(SQL_SECTORES,[sdet.producto])
					sdet.sectores=sectores.collect{it.sector}.join(',')

				}

					surtido.save(flush:true,failOnError:true)
					importadorDeCorteService.importar(surtido)
					event('registroDeSurtido', surtido)
				}
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
				
				db.eachRow(SQL_TRASLADOS_DET,[row.origen]){ det->
					def sdet=new SurtidoDet(det.toRowResult())
					surtido.addToPartidas(sdet)
				}
				surtido.partidas.each{ sdet->
					def sectores=db.rows(SQL_SECTORES,[sdet.producto])
					sdet.sectores=sectores.collect{it.sector}.join(',')

				}
				surtido.save(flush:true,failOnError:true)
				//importadorDeCorteService.importar(surtido)
				importadorDeCorteService.importar(surtido)
				event('registroDeSurtido', surtido)
			}
		}

	}



	def importarTransformaciones(Date fecha){
		println "Importando surtido de transformaciones fecha:"+fecha.format('dd/MM/yyyy')
		def sucursal=findSucursal()
		assert sucursal,'No hay sucursal registrada'
		def db = new Sql(dataSource_importacion)
		db.eachRow( [sucursal:sucursal,fecha:Sql.DATE(fecha)],SQL_TRANSFORMACIONES) { row->

			def surtido=Surtido.findByOrigen(row.origen)
			if(!surtido){
				surtido=new Surtido(row.toRowResult())
				surtido.puesto=true
				
				db.eachRow(SQL_TRANSFORMACIONES_DET,[row.origen]){ det->
					def sdet=new SurtidoDet(det.toRowResult())
					surtido.addToPartidas(sdet)
				}
				surtido.partidas.each{ sdet->
					def sectores=db.rows(SQL_SECTORES,[sdet.producto])
					sdet.sectores=sectores.collect{it.sector}.join(',')

				}
				surtido.save(flush:true,failOnError:true)
				importadorDeCorteService.importar(surtido)
				event('registroDeSurtido', surtido)
			}
		}

	}





	def actualizarSurtidosPuestos(Date fecha){
		println "Actualizando surtidos Puestos fecha:"+fecha.format('dd/MM/yyyy')
		def surtidos=Surtido.findAll("from Surtido s where  s.forma=? and s.venta=null",['PST'])
		if(surtidos){
			def db = new Sql(dataSource_importacion)
			surtidos.each{ surtido->
				def row=db.firstRow(SQL_PUESTOS_POR_ID,[surtido.origen])
				if(row){
					if(row.venta){
						surtido.venta=row.venta
						surtido.facturado=row.facturado
						surtido.tipoDeVenta=row.tipoDeVenta  
						surtido.save()
					}

					if(row.formaDeEntrega !=surtido.formaDeEntrega){
						if(row.formaDeEntrega=='ENVIO'){
							if(surtido.revision){
								surtido.entrego=surtido.asignado
								surtido.entregado=surtido.revision
								surtido.revisionUsuario=null
								surtido.revision=null
							}
						}
						if(row.formaDeEntrega=='LOCAL'){
							if(surtido.entregado){
								surtido.entregado=null
								surtido.entrego=null
								surtido.revision=null
								surtido.revisionUsuario=null
							}

						}
						surtido.formaDeEntrega=row.formaDeEntrega	
						surtido.nombre=row.nombre

					}

					db.eachRow(SQL_DETALLE,[row.origen]){ det->
					if(!det.conVale  || (det.conVale && surtido.clasificacion=='EXISTENCIA_VENTA')){

						def sdet=SurtidoDet.findByOrigen(det.origen)

							if(!sdet){
							sdet=new SurtidoDet(det.toRowResult())
	    					surtido.addToPartidas(sdet)	
							}	
						}
					}

					if(surtido.partidas){

						surtido.partidas.each{ sdet->
						def sectores=db.rows(SQL_SECTORES,[sdet.producto])
						sdet.sectores=sectores.collect{it.sector}.join(',')

					}

						surtido.save(flush:true,failOnError:true)
						importadorDeCorteService.importar(surtido)
						event('registroDeSurtido', surtido)
					}
				}
				

			}
		}
	}

	def actualizarSurtidosCancelados(Date fecha){
		log.debug "Actualizando surtidos cancelados  fecha:"+fecha.format('dd/MM/yyyy')
		def sucursal=findSucursal()
		assert sucursal,'No hay sucursal registrada'
		def db = new Sql(dataSource_importacion)
		db.eachRow( SQL_CANCELADOS) { row->
		

			def surtido=Surtido.findByOrigenAndVenta(row.origen,row.venta)

			if(surtido){

				surtido.cancelado=row.cancelado
				surtido.canceladoUser=row.cancelado_user
				
				surtido.partidas.each{ sdet->
					if(sdet.corte){ 
						sdet.corte.cancelado=row.cancelado
						sdet.corte.canceladoUser=row.cancelado_user
						sdet.save flush:true
					}
				}
				//println 'Cancelando: '+surtido
				surtido.save(flush:true,failOnError:true)
				event('surtidoCancelado', surtido)
			}
		}
	}

	def cancelarSurtidosNoAtendidos(Date fecha){
		log.debug "Cancelando Surtidos no atendidos"

		def surtidos=Surtido.where{(forma=='SOL' || forma=='PST' || forma == 'TRS') && iniciado==null && depurado==null && cancelado== null}.list().each{ surtido ->

			TimeDuration tiempo= TimeCategory.minus(fecha,surtido.fecha)

			if(tiempo.days>=7){
				surtido.cancelado=new Date()
				surtido.canceladoUser='sx4'

				surtido.save(flush:true , failOnError : true)
			}

		}
	}

	 String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }


}
