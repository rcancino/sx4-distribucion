package com.luxsoft.sx4

import grails.test.spock.IntegrationSpec
import groovy.sql.Sql

class ImportadorDeVentaServiceIntegrationSpec extends IntegrationSpec {

	def dataSource_importacion
	def importadorDeVentaService
	def sql

    def setup() {
    	assert dataSource_importacion
    	sql = new Sql(dataSource_importacion)

    }

    def cleanup() {
    }



    void "Importar por fecha"() {
    	given:'Una fecha con ventas: '
    	def fecha=Date.parse('dd/MM/yyyy','18/03/2015')
    	def row=sql.firstRow( [fecha:Sql.DATE(fecha)],
    		"""
    			select count(*) as count from SX_PEDIDOS where date(fecha)=:fecha
    		""")
    	assert row.count

    	when:'Importamos las ventas'
    	importadorDeVentaService.importar(fecha)
    	then:'Las ventas son importadas exitosamente'
    	Venta.count()==row.count
    	println 'Registros: '+row.count
    }



}
