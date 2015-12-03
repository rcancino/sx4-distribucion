<asset:stylesheet src="jquery-ui.css"/>
<asset:javascript src="jquery-ui/autocomplete.js"/> 
<%@page expressionCodec="none"%>
<div class="modal fade" id="searchSurtidoDialog" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content ui-front">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Buscador de surtido </h4>
			</div>

			<g:form class="form-horizontal" controller="surtidoAnalisis" action="filtrar" >
				
				<div class="modal-body">

					<div class="form-group">
						<label for="pedido" class="control-label col-sm-3">Pedido</label>
						<div class="col-sm-9">
							<input name="pedido" class="form-control" value="">
						</div>
					</div>

					%{-- <div class="form-group">
						<label for="pedido" class="control-label col-sm-3">Pedido</label>
						<div class="col-sm-9">
							<g:hiddenField id="pedido" name="pedido"  />
							<input 
								id="pedidoField" 
								type="text" 
								name="pedidoDesc"  
								class="form-control dedidoField"  
								placeholder="Seleccione el pedido">
							</input>
						</div>
					</div>
 --}%
					<div class="form-group">
						<label for="cliente" class="control-label col-sm-3">Cliente</label>
						<div class="col-sm-9">
							<g:hiddenField id="clienteId" name="cliente.id"  />
							<input 
								id="clienteField" 
								type="text" 
								name="clienteNombre"  
								class="form-control clienteField"  
								placeholder="Seleccione al cliente">
							</input>
						</div>
					</div>

					<div class="form-group">
						<label for="cliente" class="control-label col-sm-3">Surtidor</label>
						<div class="col-sm-9">
							<input  
								type="text" 
								name="surtidor"  
								class="form-control">
							</input>
						</div>
					</div>

					<div class="form-group">
						<label for="fechaInicial" class="col-sm-3 control-label ">Fecha inicial</label>
						<div class="col-sm-9">
							<input id="fechaInicial" 
								value="${session.periodoDeAnalisis?.fechaFinal?.format('dd/MM/yyyy')}"
								name="fechaInicial" type="text"  
								class="form-control fecha " autocomplete="off">
						</div>
					</div>

					<div class="form-group">
						<label for="fechaFinal" class="col-sm-3 control-label ">Fecha final</label>
						<div class="col-sm-9">
							<input id="fechaFinal" name="fechaFinal" type="text"  
								value="${session.periodoDeAnalisis?.fechaFinal?.format('dd/MM/yyyy')}"
								class="form-control fecha " >
						</div>
					</div>

					
				</div>	
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<g:submitButton class="btn btn-primary" name="aceptar" value="Aceptar" />
				</div>
			</g:form>

		</div>
		<!-- moda-content -->
	</div>
	<!-- modal-di -->

</div>

<script type="text/javascript">
	$(document).ready(function(){
		$(".fecha").datepicker({
		         
		 });
		$(".clienteField").autocomplete({
			source:'<g:createLink controller="surtidoAnalisis" action="buscarClientes"/>',
			minLength:3,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#clienteId").val(ui.item.id);
			}
		});

		$("#pedidoField").autocomplete({
			source:'<g:createLink controller="surtidoAnalisis" action="buscarPedido"/>',
			minLength:1,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#pedido").val(ui.item.id);
			}
		});
	});	
</script>

