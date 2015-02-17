package com.luxsoft.sx4.distribucion

import org.springframework.security.access.annotation.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class HomeController {

    def index() { }
}
