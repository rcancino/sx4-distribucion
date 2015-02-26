
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
		
		
		surtido(controller:'surtido',action:'index'){
			pendientes(){}
			porEntregar(){}
			entregados(){}
		}
		corte(controller:'corte',action:'index'){

		}
		embarque(){

		}
		tableros(controller:'tablero',action:'index'){}
		
	}

	surtidor{
		pendientes(controller:'surtido'){}
		porEntregar(controller:'surtido'){}
		entregados(controller:'surtido'){}
		todos(controller:'surtido'){}
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