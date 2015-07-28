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
							<th>T</th>
							<th>Producto</th>
							<th>Descripcion</th>
							
							<th>Cortes</th>
							<th>Instrucción</th>
							<th>Asignado</th>
							<th>S</th>
							<th class="text-center">Surtido</th>
							<th class="text-center">Corte</th>
							%{-- <th class="text-center">Empacado</th> --}%
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
								<td>${fieldValue(bean:row,field:"surtidoDet.surtido.forma")}</td>
								<td>${fieldValue(bean:row,field:"producto")}</td>
								<td>${fieldValue(bean:row,field:"descripcion")}</td>
								
								
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>
								<td>${row.instruccion}</td>
								<td>
									<g:if test="${row.asignado}">
										${fieldValue(bean:row,field:"asignado")}
									</g:if>
									<g:else>
										S: ${fieldValue(bean:row,field:"surtidoDet.surtido.asignado")}
									</g:else>
								</td>
								<td>
									<input class="seleccionMultiple" type="checkbox" name="pedidos" value="item1"
											data-corte="${row.id}" data-toggle="tooltip" title="Corte: ${row.id}"> 
								</td>
								<td class="text-center ${row.fin?'success':''}">
									<g:if test="${!row.asignado}">

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
									</g:if>
								</td>
								<td class="text-center">
									<g:if test="${row.asignado}">
										<%--
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
											${row.statusCorte=='PENDIENTE'?'INICIAR':row.statusCorte}
										</a>
										--%>
										<g:if test="${row.statusCorte=='PENDIENTE'}">
											<g:link action="iniciarCorte2" id="${row.id}" class="btn btn-warning btn-lg btn-block">
												INICIAR<%-- ${row.statusCorte=='PENDIENTE'?'INICIAR':row.statusCorte} --%>
											</g:link>
										</g:if>
										<g:elseif test="${row.statusCorte=='EN CORTE'}">
											<g:if test="${sec.loggedInUserInfo(field:"username")==row.asignado}">
												<g:link action="terminarCorte2" id="${row.id}" class="btn btn-warning btn-lg btn-block">
													${row.statusCorte}<%-- ${row.statusCorte=='PENDIENTE'?'INICIAR':row.statusCorte} --%>
												</g:link>
											</g:if>
											<g:else>
												${sec.loggedInUserInfo(field:"username")}
											</g:else>
										</g:elseif>
									</g:if>
								</td>
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->

		<g:render template="corteDialog"/>
		%{-- <g:render template="empaqueDialog"/> --}%

		

	</div><!-- end .container-->

	<script type="text/javascript">
		$(function(){
			//
			var count=0;

			setInterval(function(){

				if (!$('#corteModal').is(':visible')) {
					//var loc=window.location
    				console.log('Actualizar consulta...'+count);
    				//console.log('Location: '+loc);
    				window.location.reload();
				}
				count++;

			},20000);
		});
	</script>	

</body>

