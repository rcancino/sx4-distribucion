<%@page expressionCodec="none"%>
<div class="modal fade" id="importarDialog" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Importar surtidos pendientes</h4>
				
			</div>

			<g:form class="form-horizontal" action="importar" >
				
				<div class="modal-body">

					<div class="form-group">
						<label for="fechaFinal" class="col-sm-3 control-label">Folio</label>
						<div class="col-sm-9">
							<g:field  type="text" name="folio" class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="todos" class="col-sm-3 control-label">Todos</label>
						<div class="col-sm-9">
							<g:checkBox name="todos" value="${true}" checked="true" class="fomr-control"/>
						</div>
					</div>
					
				</div>	
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<g:submitButton class="btn btn-primary" name="aceptar" value="Importar" />
				</div>
			</g:form>

		</div>
		<!-- moda-content -->
	</div>
	<!-- modal-di -->

</div>

