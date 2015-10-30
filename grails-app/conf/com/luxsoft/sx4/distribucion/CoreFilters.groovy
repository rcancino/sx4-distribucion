package com.luxsoft.sx4.distribucion

import com.luxsoft.sx4.Periodo

class CoreFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if(!session.fecha){
                    session.fecha=new Date()
                }
                if(!session.periodoEmbarques){
                    session.periodoEmbarques=new Periodo(new Date()-5,new Date())
                }
                if(!session.periodoDeAnalisis){
                    session.periodoDeAnalisis=new Periodo(new Date()-5,new Date())
                }
                
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    
    }
}
