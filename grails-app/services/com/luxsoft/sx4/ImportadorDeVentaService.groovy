package com.luxsoft.sx4

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import groovy.sql.Sql

class ImportadorDeVentaService {

	static transactional = false

	def dataSource_importacion
	def grailsApplication
	def importadorDeProductoService

	def importar(){
		importar(new Date())
	}

   	def importar(Date fecha) {
   		log.debug "Importando pedidos del "+fecha.format('dd/MM/yyyy')
   		def sucursal=findSucursal()
   		assert sucursal,'No hay sucursal registrada'
   		def sql = new Sql(dataSource_importacion)
   		sql.eachRow( [fecha:Sql.DATE(fecha)],SQL_VENTAS) { row->
   			
   			def venta=Venta.findByOrigen(row.origen)
   			if(!venta){
   				venta=new Venta(row.toRowResult())
   				def cliente=Cliente.findOrSaveWhere(nombre:row.nombre,clave:row.clave,rfc:row.rfc)
   				venta.sucursal=sucursal
   				venta.cliente=cliente
   				venta.fechaEntrega=venta.fechaEntrega?:venta.fecha
   				if(venta.instruccion_id)
   					importarInstruccion(venta)
   				sql.eachRow(SQL_VENTAS_DET,[row.origen]){ det->
					def sdet=new VentaDet(det.toRowResult())
					def producto=Producto.findByClave(sdet.clave)
					if(producto==null){
						producto=importadorDeProductoService.importar(sdet.clave)
					}
					assert producto,'No existe el producto: '+sdet.clave
					sdet.producto=producto
					venta.addToPartidas(sdet)
				}
   				venta.save(flush:true,failOnError:true)
   				//event('ventaImportada', venta)
   			}
   			/*
   			if(!venta.facturado && row.facturado){
   				venta.facturado=row.facturado
   				venta.save(flush:true,failOnError:true)
   			}
   			*/
   		}

    }

    def importarInstruccion(Venta venta){
    	assert venta.instruccion_id,'No se puede importar instruccion de entrega para venta'
    	if(venta.instruccionDeEntrega){
    		def instruccion=venta.instruccionDeEntrega
    		venta.instruccionDeEntrega=null
    		instruccion.delete flush:true
    	}
    	def sql = new Sql(dataSource_importacion)
    	def row=sql.firstRow("select * from sx_pedidos_entregas where instruccion_id=?",venta.instruccion_id)
    	println 'Instruccion: '+row
    	def instruccion=new InstruccionDeEntrega()
    	instruccion.direccion=new Direccion(
    		calle:row.calle,
    		numeroExterior:row.numero,
    		numeroInterior:row.interior,
    		colonia:row.colonia,
    		municipio:row.poblacion,
    		estado:row.estado,
    		codigoPostal:row.cp,
    		pais:'MEXICO')
    	instruccion.comentario=row.comentario
    	venta.instruccionDeEntrega=instruccion
    	
    }

    def sucursal

    Sucursal findSucursal(){
    	if(!sucursal)
    		sucursal=Sucursal.findOrSaveWhere(nombre:grailsApplication.config.luxor.sx4.sucursal)
    	return sucursal
    	
    }

     def SQL_MESTREO="""
    	SELECT 
    	p.PEDIDO_ID as origen
    	,p.nombre 
    	,p.clave
    	,c.rfc 
    	,p.folio as documento
    	,p.total
    	from sx_pedidos p 
    	join sx_clientes c on(p.cliente_id=c.cliente_id)
    	join sw_sucursales s on(p.sucursal_id=s.sucursal_id)
    	where fecha=:fecha
    """

    def SQL_VENTAS="""
    	SELECT cargo_id as origen
    	    	,(SELECT c.rfc FROM sx_clientes c where c.cliente_id=v.cliente_id) as rfc
    	    	,v.clave 
    	    	,v.nombre 
    	    	,v.fecha
    	    	,docto as documento
    	    	,origen as tipoVenta    	
    	    	,(case when v.fpago like 'TARJET%' then v.cargos else 0 end) as importeComisionTarjeta 
    	    	,v.descuento    	
    	    	,v.flete
    	    	,v.importe
    	    	,v.impuesto    	
    	    	,v.total
    	    	,v.moneda
    	    	,v.tc
    	    	,v.socio_id
    	    	,(SELECT x.nombre FROM sx_socios x where x.SOCIO_ID=v.SOCIO_ID) as socio    
    	    	,v.fpago as formaPago
    	    	,v.pedido_fentrega as formaEntrega
    	    	,p.instruccion_id
    	    	,v.misma,v.comprador
    	    	,p.parcial
    	    	,(SELECT x.FECHA_ENTREGA FROM sx_pedidos_entregas x where x.instruccion_id=p.instruccion_id) as fechaEntrega    
    	    	,(SELECT x.instruccion_id FROM sx_pedidos_entregas x where x.instruccion_id=p.instruccion_id) as instruccion_id
    	    	,p.tpuesto as puestoFecha
    	    	,v.anticipo
    	    	,v.impreso
    	    	,v.pedido_creado as creadoPedido
    	    	,v.creado
    	    	,v.creado_userid as creadoUser    	
    	    	,v.modificado_userid as updatedUser
    	    	,v.comentario
    	    	,v.comentario2	    	
    	    	from sx_ventas v join sx_pedidos p on(p.pedido_id=v.pedido_id)
    	    	where v.fecha=:fecha
    """

    def SQL_VENTAS_DET="""
    	SELECT 
    	venta_id
    	,inventario_id as origen
    	,clave
    	,descripcion
    	,(SELECT P.UNIDAD FROM sx_productos p where p.PRODUCTO_ID=d.PRODUCTO_ID) AS unidad
    	,factoru as factor
    	,kilos
    	,cantidad
    	,precio_l as precioDeLista
    	,precio
    	,importe
    	,dscto as descuento
    	,dscto_orig as descuentoOrigen
    	,round(importe-importe_neto,2) as importeDescuento
    	,importe_neto as importeNeto
    	,cortes*precio_cortes as importeCortes
    	,subtotal 
    	,cortes
    	,precio_cortes as precioCortes
    	,cortes_instruccion   as instruccionDeCortes
    	FROM sx_ventasdet d WHERE venta_id=?

    """


}



