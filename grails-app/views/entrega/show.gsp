<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Entrega ${entregaInstance.documento}</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="page-header">
			<g:link controller="embarque" action="show" id="${entregaInstance.embarque.id}" class="btn btn-default">
				<h4>Embarque ${entregaInstance.documento}  (${entregaInstance.nombre}) </h4>
			</g:link>
			  	
			</div>
			<g:if test="${flash.message}">
				<div class="alert alert-info">
			  		<h4>${flash.message}</h4>
				</div>
			</g:if>

			<div class="col-sm-2">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Operaciones</h3>
					</div>
					<ul class="nav nav-tabs nav-stacked">
						<li>
							<g:if test="${!entregaInstance.embarque.salida}">
								<g:if test="${!entregaInstance.parcial }">
									<g:link action="registrarParcial" id="${entregaInstance.id}" 
										onclick="return confirm('Generar entrega parcial de documento ${entregaInstance.documento}');"> 
										<i class="fa fa-cubes"></i> Registrar parcial
									</g:link>
								</g:if>
								<g:elseif test="${!entregaInstance.embarque.salida}">
									<g:link action="cancelarParcial" id="${entregaInstance.id}" 
										onclick="return confirm('Cancelar entrega parcial de documento ${entregaInstance.documento}');"> 
										<i class="fa fa-cube"></i> Cancelar parcial
									</g:link>
								</g:elseif>
							</g:if>
							
							
						</li>
					</ul>
				  
				</div>
			</div>

			<div class="col-sm-10 ">
				<form class="form form-horizontal" >
					<fieldset disabled>
						<div class="form-group">
							<label for="fecha" class="control-label col-sm-2">Fecha Dcto</label>
							<div class="col-sm-3">
								<input type="text" 
								 class="form-control"
								 value="${entregaInstance.fechaDeDocumento.format('dd/MM/yyyy')}" >
							</div>
							<label for="fecha" class="control-label col-sm-2">Tipo</label>
							<div class="col-sm-3">
								<input type="text" 
								 class="form-control"
								 value="${entregaInstance.tipoDeDocumento}" >
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Arribo</label>
							<div class="col-sm-3">
								<input type="text" 
								 class="form-control"
								 value="${entregaInstance?.arribo?.format('dd/MM/yyyy HH:mm')}" >
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Recepción</label>
							<div class="col-sm-3">
								<input type="text" 
								 class="form-control"
								 value="${entregaInstance?.recepcion?.format('dd/MM/yyyy HH:mm')}" >
							</div>
							<label class="control-label col-sm-2">Recibió</label>
							<div class="col-sm-3">
								<input type="text" 
								 class="form-control"
								 value="${entregaInstance?.recibio}" >
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Valor</label>
							<div class="col-sm-3">
								<input type="text" class="form-control"
									value="${formatNumber(number:entregaInstance.valor,type:'currency')}" >
							</div>
							
							
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Comentario</label>
							<div class="col-sm-8">
								<input type="text" value="${entregaInstance.comentario}" class="form-control">
							</div>
							
						</div>
					</fieldset>
				</form>
			</div>
		</div> <!-- end .row -->

		<div class="row">
			<div class="col-sm-12">
				<div class="page-header">
					<h3>Productos</h3>
				</div>
				<g:render template="grid" model="[partidas:entregaInstance.partidas]"/>
					
			</div>
		</div>
		
		
	</div><!-- end .container-->

	
	

</body>

