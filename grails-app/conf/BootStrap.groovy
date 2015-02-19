import com.luxsoft.sx4.sec.*

class BootStrap {

    def init = { servletContext ->


    	def adminRole=Role.findOrSaveWhere(authority:'ADMIN')
		def usuarioRole=Role.findOrSaveWhere(authority:'USER')
		def surtidorRole=Role.findOrSaveWhere(authority:'SURTIDOR')
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
				,numeroDeEmpleado:'0000'
				,nip:'123456')
			.save(flush:true,failOnError:true)
			UsuarioRole.create(admin,usuarioRole,true)
			UsuarioRole.create(admin,adminRole,true)
		}

		environments {
			development {
				def surtidor=Usuario.findByUsername('surtidor')
				if(!surtidor){
					surtidor=new Usuario(username:'surtidor'
						,password:'surtidor'
						,apellidoPaterno:'surtidor'
						,apellidoMaterno:'surtidor'
						,nombres:'surtidor'
						,nombre:' surtidor surtidor'
						,numeroDeEmpleado:'0000'
						,nip:'424000').save(flush:true,failOnError:true)
					UsuarioRole.create(surtidor,usuarioRole,true)
					UsuarioRole.create(surtidor,surtidorRole,true)
				}
			}
		}


		
		

    }
    def destroy = {
    }
}
