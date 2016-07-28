package com.luxsoft.sx4.distribucion

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import groovy.sql.Sql

@Transactional
class ImportadorDeCorteService {

	static transactional = false

	def grailsApplication
	
	def dataSource_importacion

	def SQL_MESTREO="""
    	select 
    	s.nombre as sucursal
    	,p.clave as producto
    	,p.descripcion
    	,p.cantidad
    	,p.cortes
		,p.CORTES_INSTRUCCION as instruccion
		,p.CORTES_PRECIO as precioCorte
		,ped.folio as pedido
		,s.nombre
		,ped.puesto
		,ped.FACTURAR
		,p.pedidodet_id as origen
		,case when p.convale is true and ped.clasificacion_vale  in('RECOGE_CLIENTE','PASA_CAMIONETA','ENVIA_SUCURSAL') THEN true else false end as conVale
		from sx_pedidosdet p 
		join sx_pedidos ped on p.pedido_id=ped.pedido_id
		join sw_sucursales s on ped.SUCURSAL_ID=s.SUCURSAL_ID
		where p.cortado is true and  ped.pedido_id=:pedido and p.pedidodet_id=:origen
    """

    def SQL_CORTES_TRS="""
        select 
        concat(p.sol_id,'-',convert(p.renglon,char)) as origen
        ,s.nombre as sucursal,X.clave as producto,X.descripcion,p.solicitado as cantidad,p.cortes
		,p.CORTES_INSTRUCCION as instruccion,0 as precioCorte,ped.documento as pedido,s.nombre
		,ped.documento,false as puesto,true  as FACTURAR 
		,false as conVale
		from sx_solicitud_trasladosdet p 
		join sx_solicitud_traslados ped on p.sol_id=ped.sol_id
		 JOIN sx_productos X ON(X.PRODUCTO_ID=P.PRODUCTO_ID) join sx_unidades u on(u.UNIDAD=x.UNIDAD)
		join sw_sucursales s on PED.ORIGEN_ID=s.SUCURSAL_ID
		where p.CORTES_INSTRUCCION is not null and p.CORTES_INSTRUCCION <>'' and ped.CLASIFICACION NOT IN('EXISTENCIA_VENTA','EXISTENCIA')
		 and concat(p.sol_id,'-',convert(p.renglon,char))=:origen
    """

    def SQL_CORTES_TRANS="""
			select
			p.INVENTARIO_ID as origen
			,s.nombre as sucursal,X.clave as producto,X.descripcion,(p.cantidad*-1) as cantidad,1 as cortes
			,'' as instruccion,0 as precioCorte,p.documento as pedido,s.nombre
			,p.documento,false as puesto,true  as FACTURAR 
			,false as conVale
			from sx_inventario_trs p JOIN sx_productos X ON(X.PRODUCTO_ID=P.PRODUCTO_ID) join sx_unidades u on(u.UNIDAD=x.UNIDAD)
			join sw_sucursales s on p.SUCURSAL_ID=s.SUCURSAL_ID
			where P.INVENTARIO_ID=:origen  and DESTINO_ID is not null
    """

    def importar(Surtido surtido){
		
		//log.info "Importando instrucciones de corte para el  pedido $surtido.pedido ($surtido.sucursal)"
		def db = new Sql(dataSource_importacion)
		surtido.partidas.each{det->
			log.debug "Buscando instruccion de corte para $surtido.pedido $det.origen"
			def select=SQL_MESTREO
			if(surtido.forma=='SOL')
				select=SQL_CORTES_TRS
			if(surtido.forma=='TRS')
				select=SQL_CORTES_TRANS
			db.eachRow( [pedido:surtido.origen,origen:det.origen],select) { row->
				
				
				def corte=Corte.findByOrigen(det.origen)
				//if(!surtido ||(surtido && surtido.cancelado && surtido.venta!=row.venta.toString() && !surtido.reimportado)){
				/*if(corte){
					log.debug 'Corte ya importado'
					
					
				}else{*/

					if((!corte || (corte && corte.cancelado)) && !row.conVale){
				println "Importando Cortes "+ corte
					
					corte=new Corte(row.toRowResult())
					corte.tipo=det.surtido.tipo
					corte.surtidoDet=det
					corte.with{
						precioCorte=precioCorte?:0.0
					}
					log.debug "Importando instruccion  corte: "+corte.instruccion
					det.corte=corte
					corte.validate()
					if(corte.hasErrors()){
						throw new RuntimeException("Errores de validacion al importar corte: "+row+"  Errores: "+corte.errors)
					}
					det.save(failOnError:true)
					
				}else{
						println "El corte no ha sido importado"
				}
				
			}
			
		}
	}



	//@grails.events.Listener(topic="registroDeSurtido")
	def registroDeSurtido(Surtido surtido){
		//println 'Surtido importado registrar posibles cortes'
		importar(surtido)

	}

    String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }
}
