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
		s.nombre as sucursal,p.folio as pedido	,v.nombre as cliente,date(p.fecha) as fecha
		,p.MODIFICADO_USR as vendedor,v.modificado as facturado,p.creado as pedidoCreado,p.PEDIDO_ID as origen
		from sx_ventas v
		join sx_pedidos p on v.pedido_id=p.pedido_id
		join sw_sucursales s on v.SUCURSAL_ID=s.SUCURSAL_ID
		where s.nombre=:sucursal and date(p.fecha)=:fecha
    """

    @NotTransactional
	def importarSurtido(Date fecha){
		def sucursal=grailsApplication.config.luxor.sx4.sucursal

		log.info "Importando surtidos de $sucursal  para el ${fecha.format('dd/MM/yyyy')}"
		println importarSurtidoSql
		def db = new Sql(dataSource_importacion)
		db.eachRow( [sucursal:'TACUBA',fecha:Sql.DATE(new Date())],importarSurtidoSql) { row->
			println row
			def surtido=Surtido.findBySucursalAndPedido(row.sucursal,row.pedido)
			if(!surtido){
				surtido=new Surtido(
					sucursal:row.sucursal,
					pedido:row.pedido,
					tipo:"ORDINARIO",
					cliente:row.cliente,
					fecha:row.fecha,
					corte:false,
					origen:row.origen,
					pedidoCreado:row.pedidoCreado,
					vendedor:row.vendedor
				)
			}
			surtido.facturado=row.facturado
			surtido.save(flush:true,failOnError:true)
			

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
