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
        "500"(view:'/error')
	}

	// static mappings = {
 //        "/$controller/$action?/$id?(.$format)?"{
 //            constraints {
                
 //            }
 //        }

 //        "/"(view:"/index")
 //        "500"(view:'/error')
	// }
}
