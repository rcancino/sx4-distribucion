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
				<div class="well well-sm">
						<h4 class="text-center">Ordenes de corte</h4>
				</div>
				
				<g:if test="${flash.message}">
					<div class="alert alert-danger">
						<h4 class="text-center">${flash.message}</h4>
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
								<td>${fieldValue(bean:row,field:"statusCorte")}</td>
								<td>${fieldValue(bean:row,field:"statusEmpaque")}</td>
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->
		

	</div><!-- end .container-->

	

</body>

