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
		<sec:ifLoggedIn>
			<div class="collapse navbar-collapse" id="mainMenu">
				<nav:primary class="nav navbar-nav" scope="user"/>
				<ul class="nav navbar-nav">
					<li><a href="#searchSurtidoDialog" data-toggle="modal" data-target="#searchSurtidoDialog"> <i class="fa fa-search"></i> Buscar</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<g:render template="/_menu/user"/> 
				</ul>
			</div>
		</sec:ifLoggedIn>
	</div>
	
</nav>

<g:render template="/surtidoAnalisis/searchSurtidoDialog" />