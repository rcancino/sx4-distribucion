package com.luxsoft.sx4.distribucion


import grails.transaction.Transactional
import grails.transaction.NotTransactional
import groovy.sql.Sql
//import org.springframework.beans.BeanUtils
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

@Transactional
class ImportadorDeEmbarquesService {

	static transactional = false

	def grailsApplication

	def importacionJdbcTemplate

	@NotTransactional
    def importarEmbarque(String origen) {
    	log.info "Importando embarque $origen "
    	String select="""
    		select e.embarque_id as origen from sx_embarque e 
    	"""
    	def list=importacionJdbcTemplate.query(select,[origen],new BeanPropertyRowMapper(Embarque.class))
    	list.each{
    		log.info "Importando embarque $it.documento"
    	}
    }

    String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }
}
