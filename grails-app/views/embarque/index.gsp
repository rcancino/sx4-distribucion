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
	<title>Embarques</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="page-header">
			  	<h2>Embarques 
			  		<small>
			  			<a href="#selectorDePeriodoDialog" data-toggle="modal">Periodo (${session.periodoEmbarques})</a>
			  		</small>
			  	</h2>
			  	
			</div>
			<div class="toolbar-panel">
				<div class="btn-group">
					<input type='text' id="filtro" placeholder="Buscar" class="form-control">
				</div>
				
				
				<div class="btn-group">
					<g:link action="index" class="btn btn-default">
						<span class="glyphicon glyphicon-repeat"></span> Refrescar
					</g:link>
					
					
					
				</div>
				<div class="btn-group">
					<button type="button" name="operaciones"
							class="btn btn-default dropdown-toggle" data-toggle="dropdown"
							role="menu">
							Operaciones <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li>
							<g:link action="create">
								<span class="glyphicon glyphicon-floppy-saved"></span> Nuevo
							</g:link>
						</li>
					</ul>
				</div>
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
			</div>

			<div>
				<table id="grid" class="table table-striped table-bordered table-condensed ">
					<thead>
						<tr class="text-center">
							<td>Folio</td>
							<td>Chofer</td>
							<td>Fecha</td>
							%{-- <td>Cierre</td> --}%
							<td>Salida</td>
							<td>Regreso</td>
							<td>Comentario</td>
						</tr>
					</thead>
					<tbody>
						<g:each in="${embarqueInstanceList}" var="row">
							<tr >
								<td>
									<g:link action="show" id="${row.id}" class="btn btn-primary btn-block">
										${formatNumber(number:row.documento,format:'####')}
									</g:link>
								</td>
								<td>${fieldValue(bean:row,field:"chofer")}</td>
								<td><g:formatDate date="${row.fecha}" format="dd-MMM-yyyy"/></td>
								%{-- <td><g:formatDate date="${row.cerrado}" format="HH:mm (MM/dd)"/></td> --}%
								<td><g:formatDate date="${row.salida}" format="HH:mm (MM/dd)"/></td>
								<td><g:formatDate date="${row.regreso}" format="HH:mm (MM/dd)"/></td>
								<td>${fieldValue(bean:row,field:"comentario")}</td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${embarqueInstanceCount ?: 0}"/>
				</div>
			</div>

		</div> <!-- end .row 2-->
		
		<g:render template="/_common/selectorDePeriodo" 
			model="[operacion:'cambiarPeriodo',periodo:session.periodoEmbarques]"/>
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

