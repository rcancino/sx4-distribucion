<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Embarque ${embarqueInstance.documento}</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="page-header">
			  	<h2>Embarque ${embarqueInstance.documento}  (${embarqueInstance.chofer}) </h2>
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
						<li><g:link action="edit" id="${embarqueInstance.id}"> 
								<span class="glyphicon glyphicon-pencil"></span> Editar
							</g:link>
						</li>
						<li><g:link action="create">
								<span class="glyphicon glyphicon-floppy-saved"></span> Nuevo
							</g:link>
						</li>
						<li><g:link action="agregarEntrega" id="${embarqueInstance.id}">
								<i class="fa fa-plus"></i> Agregar
							</g:link>
						</li>
					</ul>
				  
				</div>
			</div>

			<div class="col-sm-10 ">
				<form class="form form-horizontal" >
					<fieldset disabled>
						<div class="form-group">
							<label for="fecha" class="control-label col-sm-2">Fecha</label>
							<div class="col-sm-3">
								<input type="text" 
								 class="form-control"
								 value="${embarqueInstance.fecha.format('dd/MM/yyyy')}" >
							</div>
							<label for="sucursal" class="control-label col-sm-2">Sucursal</label>
							<div class="col-sm-3">
								<input type="text" value="${embarqueInstance.sucursal}" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Salida</label>
							<div class="col-sm-3">
								<input type="text" 
								 class="form-control"
								 value="${embarqueInstance?.salida?.format('dd/MM/yyyy HH:mm')}" >
							</div>
							<label class="control-label col-sm-2">Regreso</label>
							<div class="col-sm-3">
								<input type="text" 
								 class="form-control"
								 value="${embarqueInstance?.regreso?.format('dd/MM/yyyy HH:mm')}" >
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Valor</label>
							<div class="col-sm-3">
								<input type="text" class="form-control"
									value="${formatNumber(number:embarqueInstance.valor,type:'currency')}" >
							</div>
							
							
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Comentario</label>
							<div class="col-sm-8">
								<input type="text" value="${embarqueInstance.comentario}" class="form-control">
							</div>
							
						</div>
					</fieldset>
				</form>
			</div>
		</div> <!-- end .row -->

		<div class="row">
			<div class="col-sm-12">
				<div class="page-header">
					<h3>Documentos</h3>
				</div>
				<g:render template="entregasGrid" model="[entregas:embarqueInstance.partidas]"/>
			</div>
		</div>
		
		
	</div><!-- end .container-->

	
	

</body>

