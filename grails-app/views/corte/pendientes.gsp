<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Cortes pendientes</title>
</head>
<body>
	<div class="container">
		<div class="row">
			
			
			<div class="col-md-12">
				<div class="alert alert-info">
						<h4 class="text-center">Cortes (Productos en corte)</h4>
				</div>
				<g:if test="${flash.error}">
					<div class="alert alert-danger">
						<h4 class="text-center">${flash.error}</h4>
					</div>
				</g:if>
				<g:if test="${flash.success}">
					<div class="alert alert-success">
						<h4 class="text-center">${flash.success}</h4>
					</div>
				</g:if>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Pedido</th>
							<th>Producto</th>
							<th>Descripcion</th>
							<th>Fecha</th>
							<th>Cortes</th>
							<th class="text-center">Estatus</th>
							<th class="text-center">Empacado</th>
							%{-- <th>Liberacion</th> --}%
						</tr>
					</thead>
					<tbody>
						<g:each in="${corteInstanceList}" var="row">
							<tr>
								<td>
									<a href="" data-toggle="modal" class="btn btn-default btn-lg btn-block">
										<g:formatNumber number="${row.pedido}" format="####"/>
									</a>
								</td>
								<td>${fieldValue(bean:row,field:"producto")}</td>
								<td>${fieldValue(bean:row,field:"descripcion")}</td>
								<td><g:formatDate date="${row.dateCreated}" format="hh:mm (dd-MM)"/></td>
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>
								<td>
									<a href="" data-toggle="modal" 
										class="btn btn-warning btn-lg btn-block"
										data-target="#corteModal"
										data-pedido="${row.pedido}" 
										data-descripcion="${row.descripcion}"
										data-corte="${row.id}"
										data-producto="${row.producto}" 
										data-cantidad="${row.cantidad}"
										data-cortes="${row.cortes}" 
										data-surtidor="${row.surtidor}"
										data-instruccion="${row.instruccion}"
										data-cortador="${row.asignado}"
										data-empacador="${row.empacador}"
										data-status="${row.statusCorte}">
										${row.statusCorte}
									</a>
									
								</td>
								<td class="text-center">
									<g:if test="${row.asignado}">
										<a href="" data-toggle="modal" class="btn btn-warning btn-lg btn-block"
											data-target="#empaqueModal"
											data-pedido="${row.pedido}" 
											data-descripcion="${row.descripcion}"
											data-corte="${row.id}"
											data-producto="${row.producto}" 
											data-cantidad="${row.cantidad}"
											data-cortes="${row.cortes}" 
											data-surtidor="${row.surtidor}"
											data-instruccion="${row.instruccion}"
											data-cortador="${row.asignado}"
											data-empacador="${row.empacador}"
											data-statusEmpaque="${row.status}"
											data-empacadoInicio="${row.empacadoInicio}"
											data-empacadoFin="${row.empacadoFin}"
											data-status="${row.statusEmpaque}">
											${row.statusEmpaque}
										</a>
									</g:if>
									
								</td>
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->

		<g:render template="corteDialog"/>
		<g:render template="empaqueDialog"/>

		

	</div><!-- end .container-->

	

</body>

