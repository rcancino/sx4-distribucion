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
				  <h2>Surtido de pedidos 
				  	<a href="#cambioDeFecha" data-toggle='tooltip' title="Cambiar fecha">
				  		<small >${fechaDeSurtido.format('dd / MMM / yyyy')}</small>
				  	</a>
				  </h2>
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
							<th>Cliente</th>
							<th>Pedido</th>
							<th>Venta</th>
							<th>Fecha</th>
							<th>T</th>
							<th>Tipo</th>
							<th>Creado</th>
							<th>Iniciado</th>
							<th colspan="3" class="text-center">Corte</th>
							
							<th colspan="4" class="text-center">Surtido</th>
						</tr>
					</thead>
					<thead>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th>Corte Inicio</th>
						<th>Corte Fin</th>
						<th>Enregado</th>
						<th>Id</th>
						<th>Partidas</th>
						<th>Kilos</th>
						<th>Cortes</th>
					</thead>
					<tbody>
						<g:each in="${surtidoInstanceList}" var="row">
							<tr >
								<td>
									<a href="" data-toggle="tooltip" title="${row.nombre}">
										${fieldValue(bean:row,field:"cliente")}
									</a>
								</td>
								<td><g:formatNumber number="${row.pedido}" format="####"/></td>
								<td>${fieldValue(bean:row,field:"venta")}</td>
								<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
								<td>${fieldValue(bean:row,field:"tipoDeVenta")}</td>
								<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
								<td><g:formatDate date="${row.fecha}" format="HH:mm"/></td>
								<td><g:formatDate date="${row.iniciado}" format="HH:mm"/></td>
								<td><g:formatDate date="${row.corteInicio}" format="HH:mm"/></td>
								<td><g:formatDate date="${row.corteFin}" format="HH:mm"/></td>
								<td><g:formatDate date="${row.entregado}" format="HH:mm"/></td>

								<td><g:formatNumber number="${row.id}" format="####"/></td>
								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								<td><g:formatNumber number="${0}" format="####"/></td>
								<td><g:formatNumber number="${row.getCortes()}" format="####"/></td>

								
								%{-- <td>
									<abbr title="${row.nombre}">
									${org.apache.commons.lang.StringUtils.substring(row.nombre,0,20)}
									</abbr>
								</td>
								
								
								
								
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
								<td>PEND</td> --}%
								
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

