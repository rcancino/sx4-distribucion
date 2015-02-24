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
        <!-- Main navigation
        ===============================================-->
        <nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#mainMenu">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <g:link controller="home" action="index" class="navbar-brand">
                        <i class="fa fa-home fa-lg"></i> SX4  
                    </g:link>
                </div>
                <div class="collapse navbar-collapse" id="mainMenu">
                    <nav:menu class="nav navbar-nav" scope="surtidor"/>
                </div>
            </div>
        </nav>
        
        <g:layoutBody/>
        
        <g:render template="/_menu/footer"/>
        
    </body>
</html>
