<%@page expressionCodec="none"%>
<div class="modal fade" id="selectorDeFechaDialog" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Selección de fecha</h4>
			</div>

			<g:form class="form-horizontal" action="${destino}" >
				
				<div class="modal-body">

					<div class="form-group">
						<label for="fecha" class="col-sm-3 control-label ">Fecha</label>
						<div class="col-sm-9">
							%{-- <g:field  type="date" name="fecha" class="form-control" /> --}%

							<input id="fecha" name="fecha" type="text"  
								class="form-control " autocomplete="off">
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
		$("#fecha").datepicker({
		     
		 });
	});	
</script>

