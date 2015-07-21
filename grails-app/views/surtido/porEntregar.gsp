<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="surtido"/>
	<title>Surtido de pedidos</title>
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
				%{-- <g:if test="${flash.success}">
					<div class="alert alert-success">
						<h4 class="text-center">${flash.success}</h4>
					</div>
				</g:if> --}%
				
				<table class="table table-striped table-bordered table-condensed ">
					<thead>
						<tr >
							<th class="text-center">Pedido</th>
							<th>T</th>
							<th>Cliente</th>
							<th>Entrega</th>
							<th>Hora</th>
							<th>Partidas</th>
							<th>Cortes</th>
							<th>Surtidor</th>
							<th>
								Acción
							</th>
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${surtidoInstanceList}" var="row">
							<tr class="text-center">
								<td class="">
									<a href="" class="btn btn-warning btn-lg btn-block">
										<g:formatNumber number="${row.pedido}" format="####"/>
									</a>
								</td>
								<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
								<td>${fieldValue(bean:row,field:"cliente")}</td>
								<td>${fieldValue(bean:row,field:"formaDeEntrega")}</td>
								<td><g:formatDate date="${row.dateCreated}" format="hh:mm (dd/MM)"/></td>
								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>
								<td>${fieldValue(bean:row,field:"asignado")}</td>
								<td>
									<g:if test="${row.status=='POR ENTREGAR' && (row.revision==null)  }">
										<a href="" data-toggle="modal" class="btn btn-primary btn-lg btn-block"
											data-target="#revisionDeSurtidoModal" 
											data-whatever="${row.pedido}" 
											data-surtido="${row.id}"
											data-status="${row.status}">
											REVISION
										</a> 
									</g:if>
									<g:elseif test="${row.status=='POR ENTREGAR' && (row.revision!=null)  }">
										<a href="" data-toggle="modal" class="btn btn-success btn-lg btn-block"
											data-target="#entregaDeSurtidoModal" 
											data-whatever="${row.pedido}" 
											data-surtido="${row.id}"
											data-status="${row.status}">
											ENTREGAR
										</a> 
									</g:elseif>
								</td>
								<td>
									<g:if test="${row.status=='POR ENTREGAR'}">
										
									</g:if>
								</td>

							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->
		<g:render template="entregarSurtidoDialog"/>
		<g:render template="revizarSurtidoDialog"/>
		

	</div><!-- end .container-->

		<script type="text/javascript">
			$(function(){
				//
				var count=0;

				setInterval(function(){

					var modal1=$('#entregaDeSurtidoModal');
					var modal2=$('#revisionDeSurtidoModal')

					if (!modal.is(':visible') && !modal2.is(':visible')) {
	    				console.log('Actualizar consulta...'+count);
	    				window.location.reload();
					}
					count++;

				},20000);
			});
		</script>
	

</body>

