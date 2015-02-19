<nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse" role="navigation">
	<div class="container">
		%{-- <p>The current active path is "${nav.activePath().encodeAsHTML()}"</p> --}%
		
		<p class="navbar-text navbar-left">DB:
			<a href="#" class="navbar-link" data-toggle="tooltip" 
				title="${grailsApplication.config.dataSource.url?.replaceFirst('jdbc:mysql://','')}">
				<g:if env="development">PRUEBAS</g:if>
				<g:else>PRODUCCION</g:else>
			</a>
		</p>
		
		<g:if test="${flash.brodcast}">
			<p class="navbar-text navbar-center"> 
				<a href="#" class="navbar-link">${flash.brodcast}</a>
			</p>
		</g:if>

		<p class="navbar-text navbar-right">Sucursal: 
			<a href="#" class="navbar-link">${grailsApplication.config.luxor.sx4.sucursal}</a>
		</p>
	</div>
</nav>