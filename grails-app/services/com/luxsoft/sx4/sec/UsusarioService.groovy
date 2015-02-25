package com.luxsoft.sx4.sec

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import groovy.sql.Sql
import org.springframework.jdbc.datasource.SingleConnectionDataSource


@Transactional
class UsusarioService {

	static transactional = false

	def grailsApplication

    def importarEmpleado(Integer numeroDeEmpleado) {
    	def sql=sql()
    	def row=db.firstRow( """
			select e.*,p.numeroDeTrabajador as numeroDeEmpleado 
			from empleado e join perfil_de_empleado p on(e.id=p.empleado_id)
			where p.numeroDeTrabajador=?
    		""",[numeroDeEmpleado])
    	log.info 'Importando empleado: '+row 
    	def user=Ususario.findByNumeroDeEmpleado(row.numeroDeEmpleado)
    }

    def sql(){
    	def db=grailsApplication.config.luxor.empleadosDb
    	SingleConnectionDataSource ds=new SingleConnectionDataSource(
            driverClassName:'com.mysql.jdbc.Driver',
            url:db.url,
            username:db.usuario,
            password:db.password)
         Sql sql=new Sql(ds)
         return sql
    }
}
