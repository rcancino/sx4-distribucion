<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Surtido de pedidos</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/> 
</head>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-12">
				<div class="well">
					<h3 class="text-center">Surtidos registrados (${searchCommand.toPeriodo()})
						<g:if test="${searchCommand.cliente}">
							<p class="text-center"> ${searchCommand.cliente.nombre}</p>
						</g:if>
					</h3>
					
				</div>
			</div>
		</div>

		<div class="row">
			
			<div class="col-md-4">
			  <input type='text' id="filtro" 
			  	placeholder="Filtrar" class="form-control" autofocus="on">
			</div>

			<div class="col-md-8">
				<div class="btn-group">
					<button type="button" name="reportes"
							class="btn btn-default dropdown-toggle" data-toggle="dropdown"
							role="menu">
							Reportes <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><g:link controller="reporte" action="usuariosDelSistema"> Pendiente</g:link></li>
					    
					</ul>
				</div>
				<div class="btn-group">
					<a class="btn btn-primary"
						data-toggle="modal" 
						data-target="#searchSurtidoDialog"> 
						<i class="fa fa-search"></i> Buscar
					</a>
				</div>
			</div>
		</div>
	
		

		<div class="row">
			<div class="col-md-12 grid-panel">
				<table id="grid" class="table table-striped table-bordered table-condensed ">
					<thead>
						<tr >
							<th class="text-center">Tipo</th>
							<th>Entrega</th>
							<th class="text-center">Cliente</th>
							<th class="text-center">Pedido</th>
							<th class="text-center">Venta</th>
							<th class="text-center">Fecha</th>
							<th>Part</th>
							<th>Surtio</th>
							<th>Surtido</th>
							
							<th>C</th>
							<th>Cortador</th>
							<th>Cortes</th>
							<th>C Inicio</th>
							<th>C Fin</th>

							<th>Entregó</th>
							<th>Entregado</th>

							<th>Revisó</th>
							<th>Revisión</th>

							
						</tr>
					</thead>
					<tbody>
						<g:each in="${surtidoInstanceList}" var="row">
							<tr >
								<td>${fieldValue(bean:row,field:"tipoDeVenta")}</td>

								<td>${fieldValue(bean:row,field:"formaDeEntrega")}</td>

								<td>
									<g:link action="analisis" id="${row.id}">
										<abbr title="${row.nombre}">
										${org.apache.commons.lang.StringUtils.substring(row.nombre,0,20)}
										</abbr>
									</g:link>
									
								</td>
								<td><g:formatNumber number="${row.pedido}" format="####"/></td>
								
								<td>${fieldValue(bean:row,field:"venta")}</td>

								<td><g:formatDate date="${row.fecha}" format="dd/MM"/></td>

								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								
								<td>${fieldValue(bean:row,field:"asignado")}</td>

								<td><g:formatDate date="${row.iniciado}" format="HH:MM"/></td>

								<td>
									<g:if test="${row.cortes}"><i class="fa fa-scissors"></i></g:if>
								</td>
								<td>
									<g:if test="${row.cortes}">"${row.partidas.get(0).corte?row.partidas.get(0).corte.asignado:''}"</g:if>
								</td>
								
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>

								<td>
									<g:if test="${row.cortes}">
										<g:formatDate date="${row.corteInicio}" format="HH:MM (dd/MM)"/>
									</g:if>
									<g:else>
										0
									</g:else>
								</td>

								<td>
									<g:if test="${row.cortes}">
										<g:formatDate date="${row.corteFin}" format="HH:MM (dd/MM)"/>
									</g:if>
									<g:else>
										0
									</g:else>
								</td>
								
								

								<td>${fieldValue(bean:row,field:"entrego")}</td>
								<td><g:formatDate date="${row.entregado}" format="hh:MM (dd/MM)"/></td>
								
								<td>${fieldValue(bean:row,field:"revisionUsuario")}</td>
								<td><g:formatDate date="${row.revision}" format="hh:MM (dd/MM)"/></td>
								
							</tr>
						</g:each>
					</tbody>
				</table>
				%{-- <div class="pagination">
					<g:paginate total="${surtidoInstanceCount ?: 0}" />
				</div> --}%
			</div>

		</div> <!-- end .row 2-->
	
	<script type="text/javascript">
		$(function(){

			$("table tbody").on('hover','tr',function(){
				$(this).toggleClass("info");
			});

			$(".table tbody tr").hover(function(){
				$(this).toggleClass("info");
			});
			
			$('#grid2').dataTable({
			    responsive: false,
			    "dom": '<"toolbar col-md-4">rt<"bottom"lp>',
			    "paging":   false,
			    "order": []
			      });

				$('#grid').dataTable( {
					"responsive": false,
			    	"paging":   false,
			    	"ordering": false,
			    	"info":     false,
			    	"dom": '<"toolbar col-md-4">rt<"bottom"lp>'
				} );

			

			$("#filtro").on('keyup',function(e){
			    var term=$(this).val();
			    $('#grid').DataTable().search(
			    $(this).val()
			    ).draw();
			});
		});
		
		  
	</script>	

	</div><!-- end .container-->

	

</body>

