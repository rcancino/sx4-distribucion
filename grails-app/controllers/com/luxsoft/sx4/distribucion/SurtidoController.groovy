package com.luxsoft.sx4.distribucion



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario
import grails.converters.JSON

@Transactional(readOnly = true)
@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class SurtidoController {

    def importacionService

    def afterInterceptor = { model ->
        if(model.fechaDeSurtido==null)
            model.fechaDeSurtido=new Date()
    }

    

    @Secured(['permitAll'])
    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.sort='pedidoCreado'
        params.order='asc'
        respond Surtido.list(params), model:[surtidoInstanceCount:Surtido.count()]
    }

    @Secured(['permitAll'])
    def todos(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.sort='pedidoCreado'
        params.order='asc'
        respond Surtido.list(params), model:[surtidoInstanceCount:Surtido.count()]
    }

   
    

    @Secured(['permitAll'])
    def pendientes() {
        //params.max = 100
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{asignado==null}
        respond query.list(params), model:[surtidoInstanceCount:query.count()]
        //respond Surtido.list(params), model:[surtidoInstanceCount:Surtido.count()]
    }

    

    @Secured(['permitAll'])
    def porEntregar() {
        params.sort='pedidoCreado'
        params.order='asc'
        def list=Surtido.where{asignado!=null}.list(params)
        def res=list.findAll{it.getStatus()=='POR ENTREGAR'}
        respond res, model:[surtidoInstanceCount:res.size()]
        //respond Surtido.list(params), model:[surtidoInstanceCount:Surtido.count()]
    }

    @Transactional
    def importar(ImportadorPorFechaCommand cmd) {
       if(!cmd.isValid()){
          flash.message="Se requiere una fecha valida para importar entidads por fecha"
          return
       }
       log.info 'Importando '+cmd
       importacionService.importarSurtido(fecha)
       render view:'index'
    }

    @Secured(['permitAll']) 
    @Transactional
    def asignar(Surtido surtido){
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para asignar pedido $surtido.pedido"
        redirect action:'pendientes'
        return
      }
      def user=Usuario.findByNip(nip)
      if(!user){
        flash.error="Operador no encontrado verifique su NIP "
        redirect action:'pendientes'
        return 
      }
      if(!user.getAuthorities().find{it.authority=='SURTIDOR'}){
        flash.error="No tiene el ROL de SURTIDOR verifique su NIP "
        redirect action:'pendientes'
        return 
      }

      surtido.asignado=user.username
      surtido.iniciado=new Date()
      surtido.save(flush:true,failOnError:true)
      log.info "Surtido de pedido: $surtido.pedido asignado a  $user.nombre "
      flash.success="Surtido de pedido: $surtido.pedido asignado a  $user.nombre "
      redirect action:'pendientes'

    }

    
    @Secured(['permitAll'])
    @Transactional
    def entregarSurtido(Surtido surtido){
      assert surtido.status=='POR ENTREGAR','El surtido no esta listo para entregar Status: '+surtido.getStatus()
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operación"
        redirect action:'porEntregar'
        return
      }
      def surtidor=Usuario.findByNip(nip)
      if(!surtidor){
        flash.error="Operador no encontrado verifique su NIP "
        redirect action:'porEntregar'
        return 
      }
      if(!surtidor.getAuthorities().find{it.authority=='SURTIDOR'}){
        flash.error="No tiene el ROL de SURTIDOR verifique su NIP "
        redirect action:'porEntregar'
        return 
      }
      surtido.entrego=surtidor.username
      surtido.entregado=new Date()
      surtido.save(flush:true)
      event('surtidoEntregado',surtido.id)
      log.info "Surtido de pedido: $surtido.pedido entregado por  $surtidor.nombre "
      flash.success="Surtido de pedido: $surtido.pedido entregado por  $surtidor.nombre "
      redirect action:'porEntregar'

    }




    @Secured(["hasAnyRole('GERENTE')"])
    def porEntregarAnalisis() {
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{entregado==null}
        def res=query.list(params).collect({new SurtidoAnalisis(surtido:it)})
        //respond surtidores, model:[surtidoInstanceCount:query.count()]
        [surtidoInstanceList:res]
    }
        
    @Secured(['permitAll'])
    def entregados(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{entregado!=null}
        respond query.list(params), model:[surtidoInstanceCount:query.count()]
    }

     @Secured(["hasAnyRole('GERENTE')"])
    def resumen(Date fecha) {
        params.sort='pedidoCreado'
        params.order='asc'
        if(fecha==null) fecha=new Date()
        [surtidoInstanceList:Surtido.findAllByFecha(fecha),surtidoInstanceCount:Surtido.countByFecha(fecha,params)]
    }

    @Secured(["hasAnyRole('GERENTE')"])
    def porDia(Integer max){
      
      params.max = Math.min(max ?: 20, 100)
      params.sort='pedidoCreado'
      params.order='asc'
      def fecha=params.date('fecha',['dd/MM/yyyy'])
      
      def surtidosMap=Surtido.findAllByFecha(fecha).groupBy{it.asignado?:'PENDIENTE'}
     
      [fechaDeSurtido:fecha
        ,surtidosMap:surtidosMap]
    }

    

    def getResumenPorDiaAsJSON(){
       def map=[:]
       map['Pendientes']=5
       map['Por Surtir']=5
       map['Por Cortar']=3
       map['En Corte']=8
       def res= map as JSON
       render res
    }

    def getStatusPorDiaAsJSON(){
      def map=[:]
      map['Pendientes']=5
      map['Por Surtir']=5
      map['Por Entregar']=3
      def res= map as JSON
      render res
    }

    def getStatusDeCorteAsJSON(){
      log.info 'Calculando status de cortes '+params
      def map=[:]
      //map['Pendientes']=4
      map['Cortando']=5
      map['Por Empacar']=5
      map['Por Entregar']=3
      def res= map as JSON
      render res
    }


    @Secured(['permitAll'])  
    def enProceso(Integer max){
      params.max = Math.min(max ?: 10, 100)
      params.sort='pedidoCreado'
      params.order='asc'
      def query=Surtido.where{asignado!=null && entregado==null}
      [surtidoInstanceList:query.list(params),surtidoInstanceCount:query.count()]
      //respond query.list(params), model:[surtidoInstanceCount:query.count(params)]

    }

    @Secured(['permitAll']) 
    @Transactional
    def agregarAuxiliar(Surtido surtido){
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para agregar un auxiliar de surtido para el pedido $surtido.pedido"
        redirect action:'enProceso'
        return
      }
      def surtidor=Usuario.findByNip(nip)
      if(!surtidor){
        flash.error="Operador no encontrado verifique su NIP "
        redirect action:'enProceso'
        return 
      }
      if(!surtidor.getAuthorities().find{it.authority=='SURTIDOR'}){
        flash.error="No tiene el ROL de SURTIDOR verifique su NIP "
        redirect action:'enProceso'
        return 
      }
      if(surtidor.username==surtido.asignado){
        flash.error="El surtido ya esta asignado a: ($surtidor.username) no puede ser auxiliar"
        redirect action:'enProceso'
        return 
      }
      if(surtido.auxiliares.find{it.nombre==surtidor.username}){
        flash.error="Auxiliar ya asignado al pedido $pedido ($surtidor.username) "
        redirect action:'enProceso'
        return 
      }
      surtido.addToAuxiliares(nombre:surtidor.username)
      surtido.save(flush:true,failOnError:true)
      log.info "Surtido auxiliar $surtidor.username asignado al  pedido: $surtido.pedido   "
      flash.success="Surtido auxiliar $surtidor.username asignado al  pedido: $surtido.pedido   "
      redirect action:'enProceso'

    }


    
}


