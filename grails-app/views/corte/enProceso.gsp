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
				<g:else>
					<div class="alert alert-info">
											
						<h4 class="text-center">Operador: ${cortador?.nombre}</h4>
					</div>
				</g:else>
				
				
				
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Pedido</th>
							<th>Producto</th>
							<th>Descripcion</th>
							<th>Fecha</th>
							<th>Cortadores</th>
							<th>Empacadores</th>
							<th>S</th>
							<th class="text-center">Empacado</th>
							<th class="text-center">Empacado G</th>
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
											<span class="label label-primary">${aux.nombre}</span><br>
										</g:if>
									</g:each>
								</td>
								%{-- <td >

									<a href="" data-toggle="modal" class="btn btn-warning btn-lg btn-block">
										${row.status}
									</a>
								</td> --}%
								<td>	
									<input class="seleccionMultiple" type="checkbox" name="cortes" value="item1"
											data-corte="${row.id}" data-toggle="tooltip" title="Corte: ${row.id}"> 
								</td>
								<td class="text-center">
									<g:if test="${row.fin && !row.empacadoFin}">
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
											${row.status}
										</a>
									</g:if>
									<g:else>
										<g:formatDate date="${row.empacadoFin}" format="hh:mm (dd-MM)"/>
										${row.statusEmpaque}
									</g:else>

								</td>
								<td class="text-center">
									<g:if test="${row.fin && !row.empacadoFin}">
										<a href="" data-toggle="modal" class="btn btn-success btn-lg btn-seleccion	"
											data-target="#empaqueGlobalModal"
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
											${row.status} G
										</a>
									</g:if>
									<g:else>
										<g:formatDate date="${row.empacadoFin}" format="hh:mm (dd-MM)"/>
										${row.statusEmpaque}
									</g:else>

								</td>

								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->

		<g:render template="agregarAuxiliarDeCorteDialog"/>

		<g:render template="empaqueDialog"/>
		<g:render template="empaqueGlobalDialog"/>

	</div><!-- end .container-->

	<script type="text/javascript">
		$(function(){
			//
			var count=0;

			setInterval(function(){

				var empaqueModal=$('#empaqueModal');
				var auxiliarModal=$('#agregarAuxiliarModal');
				var empaqueGlobalModal=$('#empaqueGlobalModal');

				if (!empaqueModal.is(':visible') && !auxiliarModal.is(':visible') && !empaqueGlobalModal.is(':visible')) {
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

