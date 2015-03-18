<%@page expressionCodec="none"%>
<div class="modal fade" id="selectorDePeriodoDialog" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Selección de periodo </h4>
			</div>

			<g:form class="form-horizontal" action="${operacion}" >
				
				<div class="modal-body">

					<div class="form-group">
						<label for="fechaInicial" class="col-sm-3 control-label ">Fecha inicial</label>
						<div class="col-sm-9">
							<input id="fechaInicial" 
								value="${periodo?.fechaInicial?.format('dd/MM/yyyy')}"
								name="fechaInicial" type="text"  
								class="form-control fecha " autocomplete="off">
						</div>
					</div>

					<div class="form-group">
						<label for="fechaFinal" class="col-sm-3 control-label ">Fecha final</label>
						<div class="col-sm-9">
							<input id="fechaFinal" name="fecha" type="text"  
								value="${periodo?.fechaFinal?.format('dd/MM/yyyy')}"
								class="form-control fecha " autocomplete="off">
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
	});	
</script>

