package com.luxsoft.sx4.distribucion

class CoreFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if(!session.fecha){
                    session.fecha=new Date()
                }
                
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    
    }
}
