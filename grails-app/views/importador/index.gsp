<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Surtido de pedidos</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="col-md-6">
				<ul class="nav nav-tabs">
					<li role="presentation" class="active"><a href="#">Pendientes</a></li>
					<li role="presentation"><a href="#">Parciales</a></li>
					<li role="presentation"><a href="#">Atendidos</a></li>
				</ul>
			</div>
		</div>

		<div class="row">
				
				<div class="col-md-6 ">
					<div class="btn-group">
						<input type='text' class="form-control" id="filtro" placeholder="Filtrar"  autofocus="on">
					</div>
					
					<div class="btn-group toolbar-panel">
					                <button type="button" name="operaciones"
					                	class="btn btn-default dropdown-toggle" data-toggle="dropdown"
					                    role="menu">
					                    Operaciones <span class="caret"></span>
									</button>
					                <ul class="dropdown-menu">
										<li><a href="#importarDialog" data-toggle> 
												<i class="fa fa-upload"></i> Importar
											</a>
					                	</li>
									</ul>
					</div>
					<div class="btn-group">
					    <button type="button" name="reportes"
					            class="btn btn-default dropdown-toggle" data-toggle="dropdown"
					            role="menu">
					            Reportes <span class="caret"></span>
					    </button>

					    <ul class="dropdown-menu">
					        
					    </ul>
					</div>
				</div>
			
		</div><!-- end .row toolbar -->
		
		<div class="row">
			<div class="col-md-12">
				%{-- <g:render template="grid"/> --}%
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Pedido</th>
							<th>Tipo</th>
							<th>Cliente</th>
							<th>Teimpo</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${surtidoInstanceList}" var="row">
							<tr>
								<td>
									<g:link action="show" id="${row.id}">
										${fieldValue(bean:row,field:"pedido")}
									</g:link>
								</td>
								<td>${fieldValue(bean:row,field:"tipo")}</td>
								<td>${fieldValue(bean:row,field:"cliente")}</td>
								<td>PENDIENTE</td>
								<!-- <td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td> -->
								
	
						</g:each>
					</tb
		</div>
