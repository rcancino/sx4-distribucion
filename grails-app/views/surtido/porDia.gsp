<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
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

				
				<table id="grid" class="table table-striped table-bordered table-condensed ">
					<thead>
						<tr >
							<th colspan="7"></th>
							<th colspan="3" class="text-center">Corte</th>
							<th colspan="4" class="text-center">Surtido</th>
						</tr>
						<tr>
							<th>Cliente</th>
							<th>Pedido</th>
							<th>Venta</th>
							<th>Fecha</th>
							<th>T</th>
							<th>Tipo</th>
							<th>Creado</th>
							<th>Inicio</th>
							<th>Fin</th>
							<th>Entregado</th>
							<th>Id</th>
							<th>Partidas</th>
							<th>Kilos</th>
							<th>Cortes</th>
						</tr>
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

								
								<td><g:formatDate date="${row.iniciado}" format="HH:mm"/></td>
								<td><g:formatDate date="${row.corteInicio}" format="HH:mm"/></td>
								<td><g:formatDate date="${row.corteFin}" format="HH:mm"/></td>
								<td><g:formatDate date="${row.entregado}" format="HH:mm"/></td>

								<td><g:formatNumber number="${row.id}" format="####"/></td>
								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								<td>0</td>
								<td><g:formatNumber number="${row.getCortes()}" format="####"/></td>

								
								
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

	<script type="text/javascript">
		$(function(){

	    	$('#grid').dataTable( {
	        	"paging":   false,
	        	"ordering": false,
	        	"info":     false
	        	,"dom": '<"toolbar col-md-4">rt<"bottom"lp>'
	    	} );
	    	var table = $('#grid').DataTable();
	    	$("#filtro").on('keyup',function(e){
	    		$('#grid').DataTable().search($(this).val()).draw();
	    	});
	    	/*
			$("#nombreField").autocomplete({
				source:'<g:createLink controller="socio" action="getSociosJSON"/>',
				minLength:3,
				select:function(e,ui){
					console.log('Valor seleccionado: '+ui.item.id);
					$("#nombreField").val(ui.item.id);
					$("#id").val(ui.item.id);
					var button=$("#buscarBtn");
	    			button.removeAttr('disabled');
				}
			});
			*/
			
			
		});
	</script>

</body>

