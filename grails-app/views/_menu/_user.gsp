<li class="dropdown">
	<sec:ifLoggedIn>
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
    		<i class="glyphicon glyphicon-user"></i>
    		<sec:loggedInUserInfo field="username"/><b class="caret"></b>
		</a>
		%{-- <ul class="dropdown-menu" role="menu">
			<li>
				<g:form controller="logout" class="navbar-form navbar-left" role="search">
  
  					<button type="submit" class="btn btn-default"> <i class="fa fa-power-off"></i> Cerrar sesión</button>
				</g:form>
				
			</li>
			<sec:ifAllGranted roles="ADMIN">
				<li>
					<g:link controller="usuario" ><i class="fa fa-users"></i> Usuarios</g:link>
				</li>
				<li>
					<g:link controller="consulta" action="sesiones"><i class="fa fa-cogs"></i> Sessiones</g:link>
				</li>
				<li>
					<g:link controller="configuracion" action="index"><i class="fa fa-building-o"></i> Configuración</g:link>
				</li>
			</sec:ifAllGranted>
		</ul> --}%
		<nav:menu class="dropdown-menu" scope="admin" custom="true">
			<li>
				<p:callTag tag="g:link" attrs="${linkArgs + [class:active ? 'active' : '']}">
		           <span>
		               <nav:title item="${item}"/>
		           </span>
				</p:callTag>
			</li>
		</nav:menu>
	</sec:ifLoggedIn>
</li>
