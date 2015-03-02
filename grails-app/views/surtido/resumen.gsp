<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Surtido de pedidos</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-12">
				<div class="page-header">
				  <h2>Surtido de pedidos <small>${fechaDeSurtido}</small></h2>
				</div>
			</div>

			<div class="col-md-2">
				%{-- <g:render template="sideBar"/> --}%
				<g:render template="sidebar"/>
			</div>

			<div class="col-md-10">

				
				<table class="table table-striped table-bordered table-condensed ">
					<thead>
						<tr >
							<th class="text-center">Tipo</th>
							<th class="text-center">Cliente</th>
							<th class="text-center">Pedido</th>
							<th class="text-center">Fecha</th>
							<th class="text-center">Venta</th>
							<th>T</th>
							<th>Partidas</th>
							<th>Corte</th>
							<th>T Surtido</th>
							<th>Surtio</th>

							<th>T Corte</th>
							<th>Cortador</th>
							<th>Cortes</th>

							<th>Entregado</th>
							<th>Entregó</th>
							<th>T. Total</th>

							
						</tr>
					</thead>
					<tbody>
						<g:each in="${surtidoInstanceList}" var="row">
							<tr >
								<td>${fieldValue(bean:row,field:"tipoDeVenta")}</td>
								<td>
									<abbr title="${row.nombre}">
									${org.apache.commons.lang.StringUtils.substring(row.nombre,0,20)}
									</abbr>
								</td>
								<td><g:formatNumber number="${row.pedido}" format="####"/></td>
								<td><g:formatDate date="${row.fecha}" format="dd/MM"/></td>
								<td>${fieldValue(bean:row,field:"venta")}</td>
								<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								<td>
									<g:if test="${row.cortes}"><i class="fa fa-scissors"></i></g:if>
								</td>
								<td>
									<g:formatDate date="${row.iniciado}" format="HH:MM"/>
								</td>
								<td>${fieldValue(bean:row,field:"asignado")}</td>

								<td>
									<g:if test="${row.cortes}">
										<g:formatDate date="${row.corteInicio}" format="HH:MM"/>
									</g:if>
									<g:else>
										0
									</g:else>
								</td>
								<td>PEND</td>
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>

								<td>${fieldValue(bean:row,field:"entrego")}</td>
								<td><g:formatDate date="${row.entregado}" format="hh:MM (dd/MM)"/></td>
								<td>PEND</td>
								
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${surtidoInstanceCount ?: 0}"/>
				</div>
			</div>

		</div> <!-- end .row 2-->
		

	</div><!-- end .container-->

	

</body>

