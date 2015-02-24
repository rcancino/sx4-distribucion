
import grails.plugin.springsecurity.SpringSecurityUtils


def loggedIn = { -> 
    //springSecurityService.principal instanceof String
    springSecurityService.isLoggedIn()
}
def loggedOut = { -> 
    !springSecurityService.isLoggedIn()
}
def isAdmin = { -> 
    SpringSecurityUtils.ifAllGranted('ADMIN')
}


//springSecurityService

navigation={
	user{
		home(action:'index',titleText:'Inicio'){}
		
		surtido(controller:'surtido',action:'pendientes'){
			
		}
		corte(controller:'corte',action:'pendientes'){

		}
		embarque(){

		}
		tableros(controller:'tablero',action:'index'){}
		
	}

	/*admin{
		
		salir(visible:loggedIn,controller:'logout',action:'index')
		usuarios(visible:isAdmin)
		sesiones(visible:isAdmin)
		configuracion(visible:isAdmin){
				catalogos(controller:'home'){
				sucursales(controller:'tipoDeSocio',action:'index',enabled:SpringSecurityUtils.ifAllGranted('ADMINISTRACION'))
				tipoDeCorpo(controller:'tipoDeCorporativo',action:'index',titleText:'Corporativos')
			}
		}
		importacion(visible:isAdmin)
	}*/
	
}