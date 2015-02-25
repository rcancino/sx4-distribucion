package com.luxsoft.sx4.distribucion


import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import org.grails.databinding.BindingFormat


@Secured(["hasAnyRole('ADMIN')"])
class ImportadorController {

    def importadorDeSurtidoService

    def importadorDeProductoService

    def index(Integer max) {
      params.max = Math.min(max ?: 20, 100)
      params.sort='pedidoCreado'
      params.order='asc'
      respond Surtido.list(params), model:[surtidoInstanceCount:Surtido.count()]      
    }

    //@Transactional
    def importar(ImportadorPorFechaCommand cmd) {
       
       if(!cmd.validate()){
          flash.message="Se requiere una fecha valida para importar entidads por fecha"
          redirect view:'index'
          return
       }
       log.info 'Importando '+cmd
       importadorDeSurtidoService.importar(cmd.fecha)
       redirect view:'index'
    }

    def importarProductos(){
      importadorDeProductoService.importarTodos()
      redirect action:'index'
    }

   
}

class ImportadorPorFechaCommand{
    
  //@BindingFormat('dd/MM/yyyy')  
  Date fecha

  String toString(){
    fecha.format('dd/MM/yyyy')
  }
}
