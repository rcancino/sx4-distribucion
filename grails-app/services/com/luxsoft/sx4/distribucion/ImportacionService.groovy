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
	
	
    /*def importarClientes() {
		log.info 'Importando clientes desde siipap-gasoc'
		def db = new Sql(dataSource_importacion)
		def tipo
		def importados=0
		def res=db.eachRow("select * from sx_clientes order by modificado desc"){row->
			//log.info 'Importando: '+row
			def found=Cliente.findByOrigen(row.CLIENTE_ID.toLong())
			if(!found){
				if(!tipo){
					tipo=TipoDeCliente.findOrSaveWhere(clave:'GENERAL',descripcion:'Cliente sin clasificación')
				}
				//log.info "Importando cliente:${row.NOMBRE} (${row.CLAVE})"
				
				def cliente=new Cliente(
					origen:row.CLIENTE_ID,
					nombre:row.NOMBRE,
					rfc:row.RFC,
					tipo:tipo,
					emailCfdi:row.EMAIL1)
				cliente.validate()
				if(cliente.hasErrors()){
					cliente.errors.allErrors.each{
						//println "Validation error : "+it.
					}
				}
				def direccion=new Direccion(
					calle:row.CALLE?:''
					,numeroExterior:row.NUMERO?:''
					,colonia:row.COLONIA?:''
					,municipio:row.DELMPO?:''
					,estado:row.ESTADO?:''
					,codigoPostal:row.CP?:''
					)
				cliente.direccion=direccion
				try{
					found=cliente.save(failOnError:true)
					importados++
				}catch(Exception th){
					//log.error "Error importando ${row.NOMBRE} ${row.CLAVE} Messge:"+ExceptionUtils.getRootCauseMessage(th)
					log.info "Error importando: "+row.CLIENTE_ID+"  "+ExceptionUtils.getRootCauseMessage(th)
				}
				
			}else{
				//log.info "YA Importado :${row.NOMBRE} (${row.CLAVE})"
				
			}
			
		}
		['importados':importados]
    }*/
	
	
	

	/*def importarProductos(){
		log.info 'Importando socios desde siipap-gasoc'
		def db = new Sql(dataSource_importacion)
		def servicio=TipoDeProducto.findOrSaveWhere(clave:'SERVICIO',descripcion:'Serivicio generico') 
		def articulo=TipoDeProducto.findOrSaveWhere(clave:'GENERAL',descripcion:'Articulo de caracter general') 
		def importados=0
		def res=db.eachRow("select s.* from sx_productos s where s.linea_id!=13"){row->
			def found=Producto.findByClave(row.CLAVE)
			if(!found){
				def producto=new Producto(
					clave:row.CLAVE,
					descripcion:row.DESCRIPCION,
					descripcion2:'Importado',
					inventariable:row.INVENTARIABLE,
					tipo:row.INVENTARIABLE?articulo:servicio,
					precioBruto:row.PRECIOCONTADO*1.16,
					descuento:0.0,
					precioNeto:row.PRECIOCONTADO*1.16,
					unidad:row.INVENTARIABLE?'PIEZA':'SERVICIO'
					)
				try{
					producto.validate()
					producto.save failOnError:true
				}catch(Exception ex){
					log.error "Error importando ${row.CLAVE} "+ExceptionUtils.getRootCauseMessage(ex)

				}
			}
		}

	}*/
}
