<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="surtido"/>
	<title>Surtido de pedidos</title>
</head>
<body>
	<div class="container-fluid">

		
		
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
					<thead class="text-center">
						<tr >
							<th class="text-center">Pedido</th>
							<th>S</th>
							<th>T</th>
							
							<th>Cliente</th>
							<th>Org</th>
							<th>Entrega</th>
							<th>Hora</th>
							<th>Partidas</th>
							<th>Cortes</th>
							%{-- <th  class="text-center">Status</th> --}%
						</tr>
					</thead>
					<tbody >
						<g:each in="${surtidoInstanceList}" var="row">
							<tr class="text-center">
								<td>
									<g:if test="${row.status=='PENDIENTE'}">
										<a href="" data-toggle="modal" class="btn btn-info btn-lg btn-block"
											data-target="#exampleModal" 
											data-whatever="${row.pedido}" 
											data-surtido="${row.id}">
											<g:formatNumber number="${row.pedido}" format="####"/>
										</a>
									</g:if>
									<g:else>
										<a href="" data-toggle="modal" class="btn btn-success btn-lg btn-block">
											<g:formatNumber number="${row.pedido}" format="####"/>
										</a>
									</g:else>
									%{-- <g:link action="show" id="${row.id}" class="btn btn-info btn-lg btn-block">
									</g:link> --}%
								</td>
								<td>
									<g:if test="${row.status=='PENDIENTE'}">
										<input class="seleccionMultiple" type="checkbox" name="pedidos" value="item1"
											data-surtido="${row.id}" data-toggle="tooltip" title="Surtido: ${row.id}"> 
									</g:if>
								</td>
								<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
								<td>
									<g:if test="${row.status=='PENDIENTE'}">
										<a href="" 
											class="btn btn-warning	 btn-lg btn-block"
											data-toggle="modal" 
											data-target="#asignacionManualDialog" 
											data-pedido="${row.pedido}" 
											data-surtido="${row.id}">
											${fieldValue(bean:row,field:"cliente")}
										</a>
									</g:if>
								</td>
								<td>${fieldValue(bean:row,field:"forma")}</td>
								<td>${fieldValue(bean:row,field:"formaDeEntrega")}</td>
								<td><g:formatDate date="${row.pedidoCreado}" format="HH:mm (dd/MM)"/></td>
								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>
								%{-- <td class="${row.status=='EN SURTIDO'?'success':'warning'}">
									${fieldValue(bean:row,field:"status")}
								</td> --}%
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->
		<g:render template="atenderSurtidoDialog"/>
		<g:render template="asignacionManualDialog"/>
		

	</div><!-- end .container-->

		<script type="text/javascript">
			$(function(){
				//
				var count=0;

				setInterval(function(){

					var modal=$('#exampleModal');
					var modal2=$('#asignacionManualDialog');

					if (!modal.is(':visible') && !modal2.is(':visible')) {
	    				console.log('Actualizar consulta...'+count);
	    				window.location.reload();
					}
					count++;

				},20000);
			});
		</script>	
	

</body>

