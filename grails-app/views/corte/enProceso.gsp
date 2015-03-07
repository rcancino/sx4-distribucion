<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="corte"/>
	<title>Cortes pendientes</title>
</head>
<body>
	<div class="container">
		<div class="row">
			
			
			<div class="col-md-12">
				
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
							<th>Cortadores</th>
							<th>Empacadores</th>
							<th class="text-center">Estatus</th>
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
								<td>
									<a href="#agregarAuxiliarModal" 
										data-toggle="modal" 
										class=""
										title="Agregar cortador auxiliar"
										data-corte="${row.id}"
										data-tipo="CORTADOR"
										data-pedido="${row.pedido}">
										${row.asignado}
									</a>
									<br>
									<g:each in="${row.auxiliares}" var="aux">
										<g:if test="${aux.tipo=='CORTADOR'}">
											<span class="label label-primary">${aux.nombre}</span>
										</g:if>
									</g:each>
									
									
								</td>
								<td>
									<a href="#agregarAuxiliarModal" 
										data-toggle="modal" 
										class=""
										title="Agregar empacador auxiliar"
										data-corte="${row.id}"
										data-tipo="EMPACADOR"
										data-pedido="${row.pedido}">
										${row.empacador}
									</a>
									<br>
									<g:each in="${row.auxiliares}" var="aux">
										<g:if test="${aux.tipo=='EMPACADOR'}">
											<span class="label label-primary">${aux.nombre}</span>
										</g:if>
									</g:each>
								</td>
								<td >
									<a href="" data-toggle="modal" class="btn btn-warning btn-lg btn-block">
										${row.status}
									</a>
								</td>
								
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->

		<g:render template="agregarAuxiliarDeCorteDialog"/>
		

		

	</div><!-- end .container-->

	

</body>

