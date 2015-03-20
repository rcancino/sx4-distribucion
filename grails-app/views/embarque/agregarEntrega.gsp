<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/>
	<title>Embarque ${embarqueInstance.documento}</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="well">
			  	<h2>Embarque ${embarqueInstance.documento}  (${embarqueInstance.chofer}) </h2>
			  	<h3>Facturas pendietes</h3>
			</div>

		</div> <!-- end .row -->

		<div class="row">
			<div class="col-sm-12">
				<div class="toolbar-panel">
					<div class="btn-group">
						<input type='text' id="filtro" placeholder="Buscar" class="form-control">
					</div>
					<div class="btn-group">
						<a id="agregarFacturas"class="btn btn-primary"> Agregar documentos</a>
						<a id ="limpiar"  class="btn btn-default" > Limpiar</a>
					</div>
				</div>
				<table id="grid" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Documento</th>
							<th>Tipo</th>
							<th>Fecha</th>
							<th>Cliente</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${pendientes}" var="row">
							<tr id="${row.id}">
								<td>${formatNumber(number:row.venta.id,format:'###')}</td>
								<td>${row.venta.tipoVenta}</td>
								<td>${formatDate(number:row.venta.fecha,format:'dd/MM/yyyy')}</td>
								<td>${row.venta.nombre}</td>
								<td>${formatNumber(number:row.venta.id,type:'currency')}</td>
							</tr>
						</g:each>
					</tbody>
				</table>

				<div class="pagination">
					<g:paginate total="${pendientesCount ?: 0}" />
				</div>
				
			</div>
		</div>

		<div class="modal fade" id="agregarDocumentosDialog" tabindex="-1" 
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="exampleModalLabel">Agregar documentos</h4>
		      </div>
		      <g:form  name="agregarForm" action="agregarEntregas" >
		      <div class="modal-body">
		        	<g:hiddenField id="${embarqueInstance.id}" name="id"/>
		        	<div class="form-group">
		        	  <label for="seleccion" class="control-label">Documentos seleccionados:</label>
		        	  <input name="seleccion" type="text" class="form-control" 
		        	  	id="seleccionField" readonly="true" 
		        	    autocomplete="off">
		        	</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		        <g:submitButton class="btn btn-primary" name="aceptar" value="Agregar" />
		      </div>
			</g:form>

		    </div>
		  </div>
		</div><!-- end modal -->
		
		
	</div><!-- end .container-->

	<script type="text/javascript">
		$(function(){

			$('#grid').dataTable( {
			    "paging":   false,
			    "ordering": false,
			    "info":     false
			    ,"dom": '<"toolbar col-md-4">rt<"bottom"lp>'
			});

			var table = $('#grid').DataTable();

			$("#filtro").on('keyup',function(e){
				$('#grid').DataTable().search($(this).val()).draw();
			});

			$("#grid tbody tr").hover(function(){
				$(this).toggleClass("info");
				
			});
			$("#grid tbody tr").click(function(){
				$(this).toggleClass("success selected");
				
			});
			
			var selectRows=function(){
				var res=[];
				var data=$("tr.selected").each(function(){
				var tr=$(this);
					res.push(tr.attr("id"));
				});
				return res;
			}

			

			$('#limpiar').click(function(){
				$("#grid tbody tr").removeClass("success selected");
			});

			/*

			$('#agregarFacturas').click(function(){
			 	var selected=selectRows();

			 	if(selected.length<=0){
			 		alert('Debe seleccionar al menos una factura');
			 	}else{
			 		String doctos=
			 		$('#seleccionField').val(selected.length);
			 		$('#agregarDocumentosDialog').modal('show');
			 	}
			});
			*/

			$("#agregarFacturas").click(function(){
				var res=selectRows();
				if(res==""){
					alert("Debe seleccionar al menos una factura");
					return
				}
				$.ajax({
					url:"${createLink(action:'registrarEntregas')}",
					dataType:"json",
					data:{
						id:${embarqueInstance.id},partidas:JSON.stringify(res)
					},
					success:function(data,textStatus,jqXHR){
						console.log('Rres: '+data.documento);
						window.location.href='${createLink(action:'show',params:[id:embarqueInstance.id])}';

					},
					error:function(request,status,error){
						console.log(error);
						alert("Error asignando facturas: "+error);
					},
					complete:function(){
						console.log('OK ');
					}
				});
			});
			
		});
	</script>
	

</body>

