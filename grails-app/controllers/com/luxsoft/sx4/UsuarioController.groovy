package com.luxsoft.sx4


import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.sx4.sec.Usuario
import grails.converters.JSON
import groovy.sql.Sql
import org.springframework.jdbc.datasource.SingleConnectionDataSource
import org.apache.commons.lang.RandomStringUtils
import com.luxsoft.sx4.sec.*
import grails.validation.Validateable

@Transactional
@Secured(["hasAnyRole('ADMIN')"])
class UsuarioController {

    def index(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        params.sort=params.sort?:'nombre'
        params.order='asc'
        respond Usuario.list(params), model:[usuarioInstanceCount: Usuario.count()]
    }

    def roles(){

    }

    @Secured(['permitAll'])
    def getEmpleadosAsJSON() {
    	
    	

    	def term=params.term?:''
    	term=term.toLowerCase()
    	def sql=sql()
    	def rows=sql.rows("""
    		select u.clave as sucursal,pu.clave as puesto, e.id, e.apellido_paterno,e.apellido_materno,e.nombres
    		from empleado e
			join perfil_de_empleado p on e.id=p.empleado_id
			join ubicacion u on p.ubicacion_id=u.id
			join puesto pu on p.puesto_id=pu.id
    		where lower(e.apellido_paterno) like ?
    		  and u.clave in (?) 
    		LIMIT 50
    		""",[term.toLowerCase()+'%',findSucursal()])
		
		
		def list=rows.collect{ c->
			def nombre="$c.nombres $c.apellido_paterno $c.apellido_materno"
			[id:c.id,
			label:nombre+"( $c.sucursal / $c.puesto)",
			value:nombre
			]
		}
		def res=list as JSON
		render res
	}


	def importarEmpleado(Long id){
		log.info 'Importando empleado: '+id
		def sql=sql()
		sql.eachRow("""
	    		select u.clave as sucursal
	    		,pu.clave as puesto
	    		, e.id as empleadoId
	    		, e.apellido_paterno as apellidoPaterno
	    		,e.apellido_materno as apellidoMaterno
	    		,e.nombres as nombres
	    		,p.numero_de_trabajador as numeroDeEmpleado
	    		from empleado e
				join perfil_de_empleado p on e.id=p.empleado_id
				join ubicacion u on p.ubicacion_id=u.id
				join puesto pu on p.puesto_id=pu.id
	    		where e.id=?
			""",[id]){ row->

			def found=Usuario.findByNumeroDeEmpleado(row.numeroDeEmpleado)
			
			if(!found){
				def usuario=new Usuario(row.toRowResult())
				usuario.nombre="$usuario.nombres $usuario.apellidoPaterno?:'' $usuario.apellidoMaterno?:''"
				usuario.nip=RandomStringUtils.randomNumeric(4)
				usuario.username=RandomStringUtils.random(6, true, true)
				usuario.password='123'
				usuario.save flush:true,failOnError:true
			}
		}
		redirect action:'index'
			

	}



	private sql(){
    	def db=grailsApplication.config.luxor.empleadosDb
    	
    	SingleConnectionDataSource ds=new SingleConnectionDataSource(
            driverClassName:'com.mysql.jdbc.Driver',
            url:db.url,
            username:db.username,
            password:db.password)
         Sql sql=new Sql(ds)
         return sql
    }

    String findSucursal(){
    	grailsApplication.config.luxor.sx4.sucursal
    }

    def show(Usuario usuarioInstance) {
        [usuarioInstance:usuarioInstance]
    }

    def create() {
        // respond new UsuarioCommand(params)
       
        [usuarioInstance:new UsuarioCommand()]
    }

    def edit(Usuario usuarioInstance) {
        respond usuarioInstance
    }

    @Transactional
    def update(Usuario usuarioInstance ) {
        if (usuarioInstance == null) {
            notFound()
            return
        }

        if (usuarioInstance.hasErrors()) {
            respond usuarioInstance.errors, view:'edit'
            return
        }

        UsuarioRole.removeAll(usuarioInstance,false)

        def roles=params.roles
        roles.each{
            //println 'Evaluando rol: '+rol
            def rol=Role.get(it)
            if(!UsuarioRole.exists(usuarioInstance.id,it.toLong())){
                UsuarioRole.create(usuarioInstance,rol,false)
            }
            
        }
        usuarioInstance.save failOnError:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect usuarioInstance
            }
            '*'{ respond usuarioInstance, [status: OK] }
        }
        
    }

    @Transactional
    def delete(Usuario usuarioInstance) {
        if (usuarioInstance == null) {
            notFound()
            return
        }
        usuarioInstance.delete flush:true
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    def cambioDePassword(Usuario usuarioInstance,CambioDePassword command){
        if(request.method=='GET'){
            return [usuarioInstance:usuarioInstance,passwordCommand:new CambioDePassword()]
        }
        
        command.validate()
        if(command.hasErrors()){
            
            flash.message="Errores de validación"
            return [usuarioInstance:usuarioInstance,passwordCommand:command]
        }
        usuarioInstance.password=command.password
        usuarioInstance.save flush:true
        flash.message="Password actualizado"
        redirect action:'edit',params:[id:usuarioInstance.id]

    }

    @Transactional
    def save(UsuarioCommand command) {
        if (command == null) {
            notFound()
            return
        }
        
        if (command.hasErrors()) {
            respond command.errors, view:'create'
            return
        }

        def usuarioInstance=command.toUsuario()

        usuarioInstance.save failOnError:true
        log.info 'Usuario registrado: '+usuarioInstance.id?:'ERROR'
        command.roles.each{
            Role r=Role.get(it)
            UsuarioRole.create(usuarioInstance,r,false)
            //println 'Asignando Rol: '+r
        }


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect usuarioInstance
            }
            '*' { respond usuarioInstance, [status: CREATED] }
        }
    }
}

@Validateable
class UsuarioCommand{

    String apellidoPaterno
    String apellidoMaterno
    String nombres
    String email
    String username
    String password
    String confirmPassword
    boolean enabled = true
    Integer numeroDeEmpleado

    List roles=[]

    static constraints = {
        importFrom Usuario

        password blank: false, nullable: false
        confirmPassword nullable:false,validator:{ val,obj ->
            if(obj.password!=val){
                return 'noMatch'
            }
            else{
                return true;
            }
        }
        email nullable:true,email:true
        
    }

    Usuario toUsuario(){
        def u=new Usuario(properties)
        u.nip=RandomStringUtils.randomNumeric(6)
        u.capitalizarNombre()

        return u
    }
    
}

@Validateable
class CambioDePassword{
    //Usuario usuario
    String password
    String confirmarPassword

    static constraints={
        password nullable:false
        confirmarPassword nullable:false,validator:{ val,obj ->
            if(obj.password!=val){
                return 'noMatch'
            }
            else{
                return true;
            }
        }
    }
}

