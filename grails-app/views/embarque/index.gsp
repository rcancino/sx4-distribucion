<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<title>Embarques</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-12">
				<div class="page-header">
				  <h2>Embarques <small>${title} (${periodo})</small></h2>
				</div>
			</div>

			<div class="col-md-2">
				<g:render template="sideBar"/>
			</div>
			
			<div class="col-md-10">
				
				
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
								<td>${fieldValue(bean:row,field:"documento")}</td>
								<td>${fieldValue(bean:row,field:"chofer")}</td>
								<td><g:formatDate date="${row.fecha}" format="dd/MM"/></td>
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
	    	$("#filterField").on('keyup',function(e){
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

