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
			
				<div class="col-md-6 ">
					<div class="btn-group">
						<input type='text' class="form-control" id="filtro" placeholder="Filtrar"  autofocus="on">
					</div>
					
					
					<div class="btn-group toolbar-panel">
		                <button type="button" name="operaciones"
		                	class="btn btn-default dropdown-toggle" data-toggle="dropdown"
		                    role="menu">
		                    <i class="fa fa-upload"></i> Importar <span class="caret"></span>
						</button>
		                <ul class="dropdown-menu">
							<li><a href="#importarDialog" data-toggle="modal"> 
									Por Fecha
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
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Pedido</th>
							<th>T</th>
							<th>Cliente</th>
							<th>Venta</th>
							<th>Importado</th>
							<th>Partidas</th>
							<th>Cortes</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${surtidoInstanceList}" var="row">
							<tr>
								<td>
									<a href="" data-toggle="modal" class="btn btn-info btn-lg btn-block"
										data-target="#exampleModal" data-whatever="@PEDIDO">
										<g:formatNumber number="${row.pedido}" format="####"/>
									</a>
								</td>
								<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
								<td>${fieldValue(bean:row,field:"cliente")}</td>
								<td>${fieldValue(bean:row,field:"venta")}</td>
								<td><g:formatDate date="${row.dateCreated}" format="hh:mm (dd-MM)"/></td>
								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								<td><g:formatNumber number="${row.cortes }" format="####"/></td>
								
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<g:render template="importarDialog"/>
		</div>
