package com.luxsoft.sx4

import grails.transaction.Transactional
import grails.transaction.NotTransactional

import groovy.sql.Sql

import com.luxsoft.sx4.Producto

@Transactional
class ImportadorDeProductoService {

	static transactional = false

	def dataSource_importacion
    

    @NotTransactional
    def importar(String clave) {
        println 'Importando producto:'+clave
        def sql = new Sql(dataSource_importacion)
        String select="""
            SELECT 
            p.producto_id as origen
            ,p.clave
            ,p.descripcion
            ,p.ancho
            ,p.largo
            ,p.kilos
            ,p.gramos
            ,p.unidad
            ,p.calibre
            ,p.caras
            ,p.presentacion
            ,p.color
            ,p.acabado
            ,p.precioContado as precioDeCredito
            ,p.precioCredito as precioDeContado
            ,p.modoDeVenta
            ,p.delinea
            ,p.nacional
            ,p.inventariable
            ,l.nombre as linea
            ,m.nombre as marca
            ,c.nombre as clase
            FROM sx_productos p
            left join sx_lineas l on(l.LINEA_ID=p.LINEA_ID)
            left join sx_clases c on(c.CLASE_ID=p.CLASE_ID)
            left join sx_marcas m on(m.MARCA_ID=p.MARCA_ID)
            where p.clave=?
            
        """
        def row=sql.firstRow(select,[clave])

        println 'Row para producto: '+row
        def prod=Producto.findByOrigen(row.origen)
        if(!prod){
            prod=new Producto(row)
            prod.presentacion=row.presentacion?:'ND'
            prod.linea=row.linea?:'ND'
            prod.marca=row.marca?:'ND'
            prod.clase=row.clase?:'ND'
        }
        prod.save(flush:true)
        log.debug 'Producto importado: '+prod
        return prod
    }

	

	@NotTransactional
    def importarTodos() {
    	def db = new Sql(dataSource_importacion)
    	String select="""
    		SELECT 
    		p.producto_id as origen
    		,p.clave
    		,p.descripcion
    		,p.ancho
    		,p.largo
    		,p.kilos
    		,p.gramos
    		,p.unidad
    		,p.calibre
    		,p.caras
    		,p.presentacion
    		,p.color
    		,p.acabado
    		,p.precioContado as precioDeCredito
    		,p.precioCredito as precioDeContado
    		,p.modoDeVenta
    		,p.delinea
    		,p.nacional
    		,p.inventariable
    		,l.nombre as linea
    		,m.nombre as marca
    		,c.nombre as clase
			FROM sx_productos p
			join sx_lineas l on(l.LINEA_ID=p.LINEA_ID)
			join sx_clases c on(c.CLASE_ID=p.CLASE_ID)
			join sx_marcas m on(m.MARCA_ID=p.MARCA_ID)
			where Activo=true
			
		"""
    	db.eachRow(select){row->
    		
    		
    		def prod=toProducto(row)
    		if(!prod.id){
    			prod.save(flush:true)
				log.debug 'Producto importado: '+prod
    		}
    		
	
    	}
    }

    private Producto toProducto(def row){
    	def prod=Producto.findByOrigen(row.origen)
		if(!prod){
			prod=new Producto(row)
			prod.presentacion=row.presentacion?:'ND'
			prod.linea=row.linea?:'ND'
			prod.marca=row.marca?:'ND'
			prod.clase=row.clase?:'ND'
		}
		return prod
    }


    
}

