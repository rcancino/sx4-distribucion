
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

		home(action:'administracion'){}
		surtido(controller:'surtido',action:'porEntregarAnalisis'){
			pendientes(){}
			porEntregar(){}
			entregados(){}
			consultas(controller:'surtido'){
				resumen(action:'resumen',titleText:'General')
				porDia()
				porSurtidor()
				porSucursal()
			}
		}
		corte(controller:'corte',action:'index'){

		}
		embarque(controller:'embarque',action:'index'){
			consultas(controller:'embarque'){
				todos(controller:'embarque',action:'index')
				pendientes()
				transito()
				atendidos()
			}
		}
		tableros(controller:'tablero',action:'index'){
			global(){}
			surtido(){}
			corte(){}
			empaque(){}
			entregas(){}
			embarques(){}
		}
		
	}

	surtidor{
		pendientes(controller:'surtido'){}
		porEntregar(controller:'surtido'){}

		
	}
	cortador{
		pendientes(controller:'corte',index:'pendientes'){}
		
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