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
    	select s.nombre as sucursal,p.clave as producto,p.descripcion,p.cantidad,p.cortes
		,p.CORTES_INSTRUCCION as instruccion,p.CORTES_PRECIO as precioCorte,ped.folio as pedido,s.nombre
		,v.DOCTO,ped.puesto,ped.FACTURAR,p.pedidodet_id as origen
		from sx_pedidosdet p 
		join sx_pedidos ped on p.pedido_id=ped.pedido_id
		join sx_ventas v on v.PEDIDO_ID=ped.PEDIDO_ID
		join sw_sucursales s on v.SUCURSAL_ID=s.SUCURSAL_ID
		where cortes>0 and  ped.pedido_id=:pedido and p.pedidodet_id=:origen
		order by ped.creado asc
    """

    def importar(Surtido surtido){
		
		//log.info "Importando instrucciones de corte para el  pedido $surtido.pedido ($surtido.sucursal)"
		def db = new Sql(dataSource_importacion)
		surtido.partidas.each{det->
			//log.info "Buscando instruccion de corte para $surtido.pedido $det.origen"
			
			db.eachRow( [pedido:surtido.origen,origen:det.origen as Integer],SQL_MESTREO) { row->
				
				
				def corte=Corte.findBySurtidoDet(det)
				if(corte){
					log.info 'Corte ya importado'
				}else{
					corte=new Corte(row.toRowResult())
					corte.tipo=det.surtido.tipo
					corte.surtidoDet=det
					corte.with{
						precioCorte=precioCorte?:0.0
					}
					
					log.info "Importando instruccion  corte: "+corte.instruccion
					det.corte=corte
					corte.validate()
					if(corte.hasErrors()){
						log.info corte.errors
					}else{
						det.save(flush:true,failOnError:true)
					}
					
				}
				/*
				if(!corte){
					corte=new Corte(row.toRowResult())
					det.corte=corte
					det.save(flush:true,failOnError:true)
				}
				*/
			}
			
		}
		
		
		
	}

	@grails.events.Listener(topic="registroDeSurtido")
	def registroDeSurtido(Surtido surtido){
		//println 'Surtido importado registrar posibles cortes'
		importar(surtido)

	}

    String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }
}
