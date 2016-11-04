import com.luxsoft.sx4.sec.*

class BootStrap {

    def init = { servletContext ->

    	Date.metaClass.inicioDeMes{ ->
    		def d1=delegate.clone()
    		d1.putAt(Calendar.DATE,1)
    		return d1.clearTime()
    	
    	}
    	
    	Date.metaClass.finDeMes{ ->
    		Calendar c2=delegate.clone().toCalendar()
    		c2.putAt(Calendar.DATE,c2.getActualMaximum(Calendar.DATE))
    		return c2.getTime().clearTime()
    	}
    	
    	Date.metaClass.text{ ->
    		return delegate.format('dd/MM/yyyy')
    	}
    	
    	Date.metaClass.toMonth{ ->
    		return delegate.getAt(Calendar.MONTH)+1
    	}
    	Date.metaClass.toYear{
    		return delegate.getAt(Calendar.YEAR)
    	}
    	Date.metaClass.asPeriodoText{
    		return delegate.format('MMMM - yyyy')
    	}

    	def adminRole=Role.findOrSaveWhere(authority:'ADMIN')
    	def usuariAdminRole=Role.findOrSaveWhere(authority:'USUARIOS_ADMIN')
		def usuarioRole=Role.findOrSaveWhere(authority:'USER')
		def surtidorRole=Role.findOrSaveWhere(authority:'SURTIDOR')
		def empacadorRole=Role.findOrSaveWhere(authority:'EMPACADOR')
		def cortadorRole=Role.findOrSaveWhere(authority:'CORTADOR')
		def embarqueRole=Role.findOrSaveWhere(authority:'EMBARQUE')
		def gerenteRole=Role.findOrSaveWhere(authority:'GERENTE')
		def supervisorSurtidoRole=Role.findOrSaveWhere(authority:'SUPERVISOR_SURTIDO')
		def supervisorEmbarqueRole=Role.findOrSaveWhere(authority:'SUPERVISOR_EMBARQUE')
		def supervisorEntregaRole=Role.findOrSaveWhere(authority:'SUPERVISOR_ENTREGA')




		def admin=Usuario.findByUsername('admin')
		if(!admin){
			admin=new Usuario(username:'admin'
				,password:'admin'
				,apellidoPaterno:'admin'
				,apellidoMaterno:'admin'
				,nombres:'admin'
				,nombre:' ADMIN ADMIN'
				,numeroDeEmpleado:'0000'
				,nip:'1234')
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
						,nip:'4240').save(flush:true,failOnError:true)
					UsuarioRole.create(surtidor,usuarioRole,true)
					UsuarioRole.create(surtidor,surtidorRole,true)
				}

				def cortador1=Usuario.findByUsername('cortador1')
				if(!cortador1){
					cortador1=new Usuario(username:'cortador1'
						,password:'cortador1'
						,apellidoPaterno:'cortador1'
						,apellidoMaterno:'cortador1'
						,nombres:'cortador1'
						,nombre:' cortador1 cortador1'
						,numeroDeEmpleado:'0000'
						,nip:'4330').save(flush:true,failOnError:true)
					UsuarioRole.create(cortador1,usuarioRole,true)
					UsuarioRole.create(cortador1,cortadorRole,true)
				}

				def empacador1=Usuario.findByUsername('empacador1')
				if(!empacador1){
					empacador1=new Usuario(username:'empacador1'
						,password:'empacador1'
						,apellidoPaterno:'empacador1'
						,apellidoMaterno:'empacador1'
						,nombres:'empacador1'
						,nombre:' empacador1 empacador1'
						,numeroDeEmpleado:'0000'
						,nip:'5330').save(flush:true,failOnError:true)
					UsuarioRole.create(empacador1,usuarioRole,true)
					UsuarioRole.create(empacador1,empacadorRole,true)
				}
			}
		}


		
		

    }
    def destroy = {
    }
}
