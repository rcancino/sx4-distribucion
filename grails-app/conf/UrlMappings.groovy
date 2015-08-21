class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view:"/index")
        "/"(controller:"home")
         //"/reportes"(controller:'report')
		"/info"(view:"/index")
        //"api/surtidos"(resources:"surtidoRest")

        "500"(view:'/error')
	}

	
}
