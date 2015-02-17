import com.luxsoft.sx4.sec.*

class BootStrap {

    def init = { servletContext ->


    	def adminRole=Role.findOrSaveWhere(authority:'ADMIN')
		def usuarioRole=Role.findOrSaveWhere(authority:'USER')
		def empacadorRole=Role.findOrSaveWhere(authority:'EMPACADOR')
		def cortadorRole=Role.findOrSaveWhere(authority:'CORTADOR')
		def embarqueRole=Role.findOrSaveWhere(authority:'EMBARQUE')
		def gerenteRole=Role.findOrSaveWhere(authority:'GERENTE')

		def admin=Usuario.findByUsername('admin')
		if(!admin){
			admin=new Usuario(username:'admin'
				,password:'admin'
				,apellidoPaterno:'admin'
				,apellidoMaterno:'admin'
				,nombres:'admin'
				,nombre:' ADMIN ADMIN'
				,numeroDeEmpleado:'0000').save(flush:true,failOnError:true)
			UsuarioRole.create(admin,usuarioRole,true)
			UsuarioRole.create(admin,adminRole,true)
		}

    }
    def destroy = {
    }
}
