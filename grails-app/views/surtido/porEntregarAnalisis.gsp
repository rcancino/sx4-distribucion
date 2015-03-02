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
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active">
						<g:link action="porEntregarAnalisis" >Por Entregar</g:link>
					</li>
					<li>
						<g:link action="entregados" >Entregados</g:link>
					</li>
				</ul>
			</div>
		</div>
		
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
				<table class="table table-striped table-bordered table-condensed ">
					<thead>
						<tr >
							<th class="text-center">Tipo</th>
							<th class="text-center">Cliente</th>
							<th class="text-center">Entrega</th>
							<th class="text-center">Pedido</th>
							<th class="text-center">Fecha</th>
							<th class="text-center">Venta</th>
							<th>T</th>
							<th>Partidas</th>
							<th>Corte</th>
							<th>Cortes</th>
							<th>Asignado</th>
							<th>T. Total</th>
							<th>Status</th>

							
						</tr>
					</thead>
					<tbody>
						<g:each in="${surtidoInstanceList}" var="row">
							<tr >
								<td>${fieldValue(bean:row,field:"surtido.tipoDeVenta")}</td>
								<td>
									<abbr title="${row.surtido.nombre}">
									${org.apache.commons.lang.StringUtils.substring(row.surtido.nombre,0,20)}
									</abbr>
								</td>
								<td>${fieldValue(bean:row,field:"surtido.formaDeEntrega")}</td>
								<td><g:formatNumber number="${row.surtido.pedido}" format="####"/></td>
								<td><g:formatDate date="${row.surtido.fecha}" format="dd/MM"/></td>
								<td>${fieldValue(bean:row,field:"surtido.venta")}</td>
								<td>${fieldValue(bean:row,field:"surtido.tipo")[0..0]}</td>
								<td><g:formatNumber number="${row.surtido.partidas.size()}" format="####"/></td>
								<td>
									<g:if test="${row.surtido.cortes}"><i class="fa fa-scissors"></i></g:if>
								</td>
								<td><g:formatNumber number="${row.surtido.cortes}" format="####"/></td>
								<td>
									${fieldValue(bean:row,field:"ultimoAsignado")}	
								</td>
								<td>${fieldValue(bean:row,field:"tiempoAcumulado")}</td>
								
								<td>${fieldValue(bean:row,field:"status")}</td>
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->
		

	</div><!-- end .container-->

	

</body>

