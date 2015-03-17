<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
	<title>Surtido de traslados</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-12">
				<div class="page-header">
				  <h2>Surtido de traslados 
				  	<a href="#selectorDeFechaDialog" data-toggle='modal' title="Cambiar fecha">
				  		<small >${fechaDeSurtido.format('dd / MMM / yyyy')}</small>
				  	</a>
				  </h2>
				</div>
			</div>

			<div class="col-md-2">
				<g:render template="sidebar"/>
			</div>

			<div class="col-md-10">
				<table class="table table-striped table-bordered table-condensed ">
					<thead class="text-center">
						<tr >
							<th>Pedido</th>
							<th>T</th>
							<th>Cliente</th>
							<th>Org</th>
							<th>Entrega</th>
							<th>Hora</th>
							<th>Partidas</th>
							<th>Cortes</th>
							<th>Comentario</th>
							<th>Cancelar</th>
						</tr>
					</thead>
					<tbody >
						<g:each in="${surtidoInstanceList}" var="row">
							<tr class="text-center ${row.cancelado?'danger':''}">
								<td><g:formatNumber number="${row.pedido}" format="####"/></td>
								<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
								<td>${fieldValue(bean:row,field:"cliente")}</td>
								<td>${fieldValue(bean:row,field:"forma")}</td>
								<td>${fieldValue(bean:row,field:"formaDeEntrega")}</td>
								<td><g:formatDate date="${row.pedidoCreado}" format="HH:mm (dd/MM)"/></td>
								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>
								<td>${fieldValue(bean:row,field:"comentario")}</td>
								<td>
									<g:if test="${!row.cancelado}">
										<g:link action="cancelarTraslado"
											id="${row.id}" onclick="return confirm('Cancelar el surtido de este traslado?');">
											<i class="fa fa-trash"></i>
										</g:link>
									</g:if>
									<g:else>
										<i class="fa fa-times"></i>
									</g:else>
									
								</td>

							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${surtidoInstanceCount ?: 0}" />
				</div>

			</div>

		</div> <!-- end .row 2-->
		<g:render template="/_common/selectorDeFecha" model="['destino':'porDia']"/>

	</div><!-- end .container-->

	<script type="text/javascript">
		$(function(){

			
		});
	</script>

</body>

