<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Sistema de distribución</title>
</head>
<body>
	<div class="container">
		
		<div class="row">
			<div class="col-md-6">
				<div class="jumbotron">
				  <h2> <i class="fa fa-cubes"></i>Surtido</h2>
				  <p>
				  	<g:link class="btn btn-primary btn-lg" controller="surtido" action="pendientes">
				  		Acceso
				  	</g:link>
				  </p>
				</div>
			</div>
			<div class="col-md-6">
				<div class="jumbotron">
				  <h2> <i class="fa fa-scissors"></i> Corte y Empaque</h2>
				  <p>
				  	<g:link class="btn btn-primary btn-lg" controller="corte" action="pendientes">
				  		Acceso
				  	</g:link>	
				  </p>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="jumbotron">
				  <h2> <i class="fa fa-truck"></i> Embarques</h2>
				  <p><g:link class="btn btn-primary btn-lg" controller="embarque" action="index">
				  		Acceso
				  	</g:link>	</p>
				</div>
			</div>
			<div class="col-md-6">
				<div class="jumbotron">
				  <h2> <i class="fa fa-desktop"></i> Control</h2>
				  <p>
				  	<g:link class="btn btn-primary btn-lg" action="administracion">
				  		Acceso
				  	</g:link>	
				  	
				</div>
			</div>
		</div>

	</div>
</body>
</html>