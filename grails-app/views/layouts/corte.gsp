<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="SX4-Entregas"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="luxor.css"/>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
    </head>
    
    <body>
        
        <nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container-fluid">

                <div class="navbar-header">
                    <p class="navbar-text ">Ordenes de corte: <a href="#" class="navbar-link">${new Date().format('dd/MM/yyyy')}</a></p>
                </div>
               <div class="collapse navbar-collapse" id="mainMenu">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Cortadores <span class="caret"></span> </a>
                                 <ul class="dropdown-menu">
                                                        <li>
                                                            <g:each in="${com.luxsoft.sx4.sec.UsuarioRole.executeQuery("select l.usuario from UsuarioRole l where l.role.authority='CORTADOR'")}" var="row">
                                                        
                                                            <g:link action="enProceso" id="${row.id}">
                                                                ${row.username}
                                                            </g:link>
                                                        </g:each>
                                                        </li>
                                                        
                                                    </ul>
                                </li>
                    </ul>
                    
                </div> 
            </div>
        </nav>
        
        <g:layoutBody/>
        
        
        
        <nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#mainMenu">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    %{-- <g:link action="index" controller="home" class="navbar-brand"><i class="fa fa-home fa-lg"></i> SX4 </g:link> --}%
                    <a href="" class="navbar-brand">
                        SX4 Sistema de Empaque
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="mainMenu">
                    %{-- <nav:menu class="nav navbar-nav" scope="cortador"/> --}%
                    <ul class="nav navbar-nav navbar-right">
                        <p class="navbar-text navbar-left">DB:
                            <a href="#" class="navbar-link navbar-right" data-toggle="tooltip" 
                                title="${grailsApplication.config.dataSource.url?.replaceFirst('jdbc:mysql://','')}">
                                <g:if env="development">PRUEBAS</g:if>
                                <g:else>PRODUCCION</g:else>
                            </a>
                        </p>
                        <p class="navbar-text navbar-right">Sucursal: 
                            <a href="#" class="navbar-link">${grailsApplication.config.luxor.sx4.sucursal}</a>
                        </p>
                    </ul>
                </div>
                
            </div>
        </nav>
        
    </body>
</html>
