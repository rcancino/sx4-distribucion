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
        <g:render template="/_menu/navbar"/>
        
        <g:layoutBody/>
        
        <g:render template="/_menu/footer"/>

        
        
    <script type="text/javascript">
    /*
        $(document).ready(function(){
            $(document).keydown(function(e){
                  //CTRL + S keydown combo
                  if(e.altKey && e.keyCode == 83){
                        var location='<g:createLink controller="consulta" action="socios"/>';
                        console.log("I've been pressed!: Ctrl+s "+location);
                        window.location=location;
                        //$('<g:createLink controller="socio" action="index"/>').click();
                  }

                  if(e.altKey && e.keyCode == 80){
                        var location='<g:createLink controller="consulta" action="productos"/>';
                        console.log("I've been pressed!: Alt+p "+location);
                        window.location=location;
                        //$('<g:createLink controller="socio" action="index"/>').click();
                  }
            });
            
        });
    */
    </script>        
        
    </body>
</html>
