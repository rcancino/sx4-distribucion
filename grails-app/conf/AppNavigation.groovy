
import grails.plugin.springsecurity.SpringSecurityUtils


def loggedIn = { -> 
    //springSecurityService.principal instanceof String
    springSecurityService.isLoggedIn()
}
def loggedOut = { -> 
    !springSecurityService.isLoggedIn()
}
def isAdmin = { -> 
    SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')
}


//springSecurityService

navigation={
	user{
		home(action:'index',titleText:'Inicio'){}
		
		surtido(){
			
		}
		corte(){

		}
		embarque(){

		}
		reportes(controller:'report',action:'index'){
			
		}
		

		
	}

	admin{
		salir(visible:loggedIn)
		cambiarUsuario(visible:loggedIn)
		usuarios(enabled:false)
		sesiones()
		configuracion(){
				catalogos(controller:'home'){
				sucursales(controller:'tipoDeSocio',action:'index',enabled:SpringSecurityUtils.ifAllGranted('ADMINISTRACION'))
				tipoDeCorpo(controller:'tipoDeCorporativo',action:'index',titleText:'Corporativos')
			}
		}
		importacion()
	}
	
}