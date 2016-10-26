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

  @Secured(["hasAnyRole('GERENTE')"])
   def traslados(Integer max){
      params.max = Math.min(max ?: 20, 100)
      params.sort='pedidoCreado'
      params.order='asc'
      def query=Surtido.where{asignado==null && forma=='SOL'}
      [surtidoInstanceList:query.list(params),surtidoInstanceCount:query.count()]        
   }
    

    @Secured(['permitAll'])
    def pendientes() {
        //params.max = 100
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{ (asignado==null && cancelado==null)}
       
        respond query.list(params), model:[surtidoInstanceCount:query.count()]
        //respond Surtido.list(params), model:[surtidoInstanceCount:Surtido.count()]
    }

    

    @Secured(['permitAll'])
    def porEntregar() {
        params.sort='pedidoCreado'
        params.order='asc'
        def list=Surtido.where{asignado!=null && formaDeEntrega=='LOCAL' && depurado==null && cancelado==null}.list(params)
        def res=list.findAll{it.getStatus()=='POR ENTREGAR' }
        respond res, model:[surtidoInstanceCount:res.size()]
        //respond Surtido.list(params), model:[surtidoInstanceCount:Surtido.count()]
    }

    @Secured(['permitAll'])
    def porEntregarEnvio() {
        params.sort='pedidoCreado'
        params.order='asc'
        def list=Surtido.where{asignado!=null && (formaDeEntrega=='ENVIO' || formaDeEntrega=='ENVIO_FORANEO') && depurado==null  && cancelado==null}.list(params)
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
      def user=Usuario.findByNipAndEnabled(nip,true)
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
      
      if(params.surtidos){
        def adicionales=params.surtidos.findAll({it.toLong()!=surtido.id})
        adicionales.each{
          def s2=Surtido.get(it.toLong())
          s2.asignado=user.username
          s2.iniciado=new Date()
          s2.save(flush:true,failOnError:true)

        }
        //print 'Surtidos adicionales: '+adicionales
      }
      log.info "Surtido de pedido: $surtido.pedido asignado a  $user.nombre "
      flash.success="Surtido de pedido: $surtido.pedido asignado a  $user.nombre "
      redirect action:'pendientes'

    }

    @Secured(['permitAll']) 
    @Transactional
    def asignacionManual(Surtido surtido){
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
      if(!user.getAuthorities().find{it.authority=='SUPERVISOR_SURTIDO'}){
        flash.error="No tiene el ROL de SUPERVISOR_SURTIDO  "
        redirect action:'pendientes'
        return 
      }

      def surtidor=Usuario.get(params.surtidor)
      
      surtido.asignado=surtidor.username
      surtido.iniciado=new Date()
      surtido.save(flush:true,failOnError:true)
      
      if(params.surtidos){
        def adicionales=params.surtidos.findAll({it.toLong()!=surtido.id})
        adicionales.each{
          def s2=Surtido.get(it.toLong())
          s2.asignado=surtidor.username
          s2.iniciado=new Date()
          s2.save(flush:true,failOnError:true)

        }
        //print 'Surtidos adicionales: '+adicionales
      }
      log.info "Surtido de pedido: $surtido.pedido asignado a  $surtidor.nombre "
      flash.success="Surtido de pedido: $surtido.pedido asignado a  $surtidor.nombre "
      redirect action:'pendientes'

    }

    
    @Secured(['permitAll'])
    @Transactional
    def entregarSurtido(Surtido surtido){
      assert surtido.status=='POR ENTREGAR','El surtido no esta listo para entregar Status: '+surtido.getStatus()
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operacin"
         if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
            redirect action:'porEntregar'    
          }
        return
      }
      def surtidor=Usuario.findByNipAndEnabled(nip,true)
      if(!surtidor){
        flash.error="Operador no encontrado verifique su NIP "
         if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
            redirect action:'porEntregar'    
          }
        return 
      }
      if(!surtidor.getAuthorities().find{it.authority=='SURTIDOR'}){
        flash.error="No tiene el ROL de SURTIDOR verifique su NIP "
        if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
            redirect action:'porEntregar'    
          }
        return 
      }
      surtido.entrego=surtidor.username
      surtido.entregado=new Date()
      if (surtido.formaDeEntrega=='ENVIO') {
          surtido.cierreSurtido=surtido.entregado
        }

      surtido.save(flush:true)
      event('surtidoEntregado',surtido.id)
      log.info "Surtido de pedido: $surtido.pedido entregado por  $surtidor.nombre "
      flash.success="Surtido de pedido: $surtido.pedido entregado por  $surtidor.nombre "

      if(params.surtidos){
        println 'Estoy dentro de la Seleccion Multiple'
            def adicionales=params.surtidos.findAll({it.toLong()!=surtido.id})
              adicionales.each{

              def ca=Surtido.get(it)
              println 'Seleccion Multiple'+it
                  surtido.entrego=surtidor.username
                  surtido.entregado=new Date()
                  ca.save(flush:true)
                
            }
      }


       if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
            redirect action:'porEntregar'    
          }

    }

    @Secured(['permitAll'])
    @Transactional
    def cerrarSurtido(Surtido surtido){
      assert surtido.status=='POR ENTREGAR','El surtido no esta listo para Cerrar Status: '+surtido.getStatus()
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para proceder con operacin"
         if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
            redirect action:'porEntregar'    
          }
        return
      }
      def surtidor=Usuario.findByNipAndEnabled(nip,true)
      if(!surtidor){
        flash.error="Operador no encontrado verifique su NIP "
         if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
            redirect action:'porEntregar'    
          }
        return 
      }
      if(!surtidor.getAuthorities().find{it.authority=='SURTIDOR'}){
        flash.error="No tiene el ROL de SURTIDOR verifique su NIP "
        if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
            redirect action:'porEntregar'    
          }
        return 
      }
      //surtido.entrego=surtidor.username
      //surtido.entregado=new Date()
      surtido.cierreSurtido=new Date()
      surtido.save(flush:true)
      //event('surtidoEntregado',surtido.id)
      log.info "Surtido de pedido: $surtido.pedido entregado por  $surtidor.nombre "
      flash.success="Surtido de pedido: $surtido.pedido entregado por  $surtidor.nombre "

      if(params.surtidos){
        println 'Estoy dentro de la Seleccion Multiple'
            def adicionales=params.surtidos.findAll({it.toLong()!=surtido.id})
              adicionales.each{

              def ca=Surtido.get(it)
              println 'Seleccion Multiple'+it
                //  surtido.entrego=surtidor.username
                  surtido.cierreSurtido=new Date()
                  ca.save(flush:true)
                
            }
      }


      if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
            redirect action:'porEntregar'    
          }

    }



    @Secured(['permitAll'])
    @Transactional
    def revizarSurtido(Surtido surtido){
      
          
          assert surtido.status=='POR ENTREGAR','El surtido no esta listo para entregar Status: '+surtido.getStatus()
          assert surtido.revision==null,'Surtido ya revizado para su entrega'
          String nip=params.nip
          if(!nip){
            flash.error="Digite su NIP para proceder con operacin"
             if(surtido.formaDeEntrega=='ENVIO'){
                  redirect action:'porEntregarEnvio'    
              }else{
                redirect action:'porEntregar'    
              }
            return
          }
          def supervisor=Usuario.findByNip(nip)
          if(!supervisor){
            flash.error="Supervisor no encontrado verifique su NIP "
             if(surtido.formaDeEntrega=='ENVIO'){
                  redirect action:'porEntregarEnvio'    
              }else{
                redirect action:'porEntregar'    
              }
            return 
          }
          if(!supervisor.getAuthorities().find{it.authority=='SUPERVISOR_ENTREGA'}){
            flash.error="No tiene el ROL de SUPERVISOR_ENTREGA verifique su NIP "
             if(surtido.formaDeEntrega=='ENVIO'){
                  redirect action:'porEntregarEnvio'    
              }else{
                redirect action:'porEntregar'    
              }
            return 
          }
          surtido.revisionUsuario=supervisor.username
          surtido.revision=new Date()
          if(surtido.cierreSurtido==null){
              surtido.cierreSurtido=new Date()
          }
          surtido.save(flush:true)
          event('surtidoRevizado',surtido.id)

          if(params.surtidos){
            def adicionales=params.surtidos.findAll({it.toLong()!=surtido.id})
            adicionales.each{
              def s2=Surtido.get(it.toLong())
              if(s2.formaDeEntrega=='ENVIO'){
                  s2.revisionUsuario=supervisor.username
                  s2.revision=new Date()
                  s2.save(flush:true)
                  event('surtidoRevizado',surtido.id)
              }
            }
          }

          log.info "Surtido de pedido: $surtido.pedido revizado por  $supervisor.nombre "
          flash.success="Surtido de pedido: $surtido.pedido entregado por  $supervisor.nombre "
          if(surtido.formaDeEntrega=='ENVIO'){
              redirect action:'porEntregarEnvio'    
          }else{
              redirect action:'porEntregar'    
          }
          
    }




    @Secured(["hasAnyRole('GERENTE')"])
    def pendientesAnalisis() {
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{iniciado==null && cancelado == null}
        def res=query.list(params).collect({new SurtidoAnalisis(surtido:it)})
        [surtidoInstanceList:res]
    }
   
    @Secured(["hasAnyRole('GERENTE')"])
    def revisadosAnalisis() {
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{iniciado!=null && cancelado == null &&
            (( revision!=null && entregado==null && formaDeEntrega=='LOCAL') || (entregado!=null && revision==null && formaDeEntrega=='ENVIO'))

        } 


        def res=query.list(params).collect({new SurtidoAnalisis(surtido:it)})
        
        //def envio=Surtido.where{iniciado!=null && cancelado == null && entregado!=null && revision==null && formaDeEntrega=='ENVIO'} 
        //def res2=envio.list().collect({new SurtidoAnalisis(surtido:it)})
        
        //res<<res2
        [surtidoInstanceList:res]
    }


    @Secured(["hasAnyRole('GERENTE')"])
    def porEntregarAnalisis() {
        params.sort='pedidoCreado'
        params.order='asc'
        def query=Surtido.where{ cancelado==null && depurado == null  &&((formaDeEntrega=='ENVIO' && revision==null) || (formaDeEntrega=='LOCAL' && entregado==null))}
        def res=query.list(params).collect({new SurtidoAnalisis(surtido:it)})
        [surtidoInstanceList:res]
    }
        
    @Secured(['permitAll'])
    def entregados(Integer max) {
        //params.max = Math.min(max ?: 20, 100)
        params.sort='pedidoCreado'
        params.order='asc'
         def list=Surtido.findAll("from Surtido s where date(s.entregado)=? and s.depurado is null",[new Date()],params)
        // def list=Surtido.findAll("from Surtido s where date(s.entregado) between '2015/08/20' and '2015/08/21'")

        respond list,model:[surtidoInstanceCount:list.size()]
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
      def query=Surtido.where{cancelado == null && asignado!=null && depurado==null && ((formaDeEntrega=='LOCAL' && entregado==null) || (formaDeEntrega=='ENVIO' && revision==null) )}
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


    @Secured(["hasAnyRole('SUPERVISOR_SURTIDO')"])
    @Transactional
    def cancelarTraslado(Surtido surtido) {
      assert surtido.forma=='SOL','No es un surtido de traslado'
      if(!surtido.cancelado){
        surtido.cancelado=new Date()
        surtido.save flush:true
      }
      redirect action:'traslados'
    }

     @Secured(['permitAll']) 
    @Transactional
    def cancelarAsignacion(Surtido surtido) {
      assert surtido.asignado,'El surtido no ha sido asignado por lo que no se puede canclear la asignacion'
      assert !surtido.cancelado,'El surtido ha sido cancleado no se puede eliminar la asignacion'
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

      if(!surtidor.getAuthorities().find{it.authority=='SUPERVISOR_SURTIDO'}){
        flash.error="No tiene el ROL de SUPERVISOR_SURTIDO verifique su NIP "
        redirect action:'pendientes'
        return 
      }

      /* if(surtidor.username!=surtido.asignado){
        flash.error="La asignacion del surtido para el pedido: $surtido.pedido  solo puede ser cancelada por:$surtido.asignado"
        redirect action:'enProceso'
        return 
      }*/
      surtido.asignado=null
      surtido.iniciado=null
      surtido.save(flush:true,failOnError:true)
      redirect action:'enProceso'
    }

    @Secured(['permitAll']) 
    @Transactional
    def depurar(Surtido surtido){
      String nip=params.nip
      if(!nip){
        flash.error="Digite su NIP para depurar surtido $surtido.id"
        redirect(uri: request.getHeader('referer') )
        return
      }
      def user=Usuario.findByNip(nip)
      if(!user){
        flash.error="Operador no encontrado verifique su NIP "
        redirect(uri: request.getHeader('referer') )
        return 
      }
      if(!user.getAuthorities().find{it.authority=='SUPERVISOR_SURTIDO'}){
        flash.error="No tiene tiene autorizacin para esta operacin "
        redirect(uri: request.getHeader('referer') )
        return 
      }
      surtido.depurado=new Date()
      surtido.depuradoUser=user.username
      surtido.save(flush:true,failOnError:true)
      log.info "Surtido de pedido: $surtido.pedido depurado $surtido.depurado   $surtido.depuradoUser "
      flash.success="Surtido de pedido: $surtido.pedido depurado por   $user.nombre "
      redirect(uri: request.getHeader('referer') )
    }

    
}


