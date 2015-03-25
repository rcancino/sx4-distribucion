<div class="modal fade" id="asignacionDialog" tabindex="-1" role="dialog" 
			aria-labelledby="asignacionDialogLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="entregaModalLabel">Entrega parcial</h4>
		      </div>
		      <g:form  controller="entregaDet" action="update" class="form-horizontal">
		      <div class="modal-body">
		       	  <g:hiddenField id="entregaDetId" name="id"/>
		          <fieldset disabled>
		          <div class="form-group">
		            <label for="producto" class="col-md-2 control-label">Producto</label>
		            <div class="col-md-10">
		            	<input name="producto" type="text" class="form-control" >
		            </div>
		          </div>

		          <div class="form-group">
		            <label for="descripcion" class="col-md-2 control-label">Descripción</label>
		            <div class="col-md-10">
		            	<input name="descripcion" type="text" class="form-control" >
		            </div>
		          </div>

		           <div class="form-group">
		            <label for="total" class="col-md-2 control-label">Total</label>
		            <div class="col-md-4">
		            	<input name="total" type="text" class="form-control" >
		            </div>
		          </div>
		          
		          <div class="form-group">
		            <label for="asignado" class="col-md-2 control-label">Asignado</label>
		            <div class="col-md-4">
		            	<input name="asignado" type="text" class="form-control" >
		            </div>
		            <label for="pendiente" class="col-md-2 control-label">Pendiente</label>
		            <div class="col-md-4">
		            	<input name="pendiente" type="text" class="form-control" >
		            </div>
		          </div>
		          
		          </fieldset>

		          <div class="form-group">
		            <label for="cantidad" class="col-md-2 control-label">Por asignar</label>
		            <div class="col-md-10">
		            	<input name="cantidad" type="text" class="form-control" id="recipient-name">
		            </div>
		          </div>
		          
		        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		         <g:submitButton class="btn btn-primary" name="aceptar" value="Actualizar" />
		      </div>
		    </div>
		    </g:form>

		  </div>
		</div>

<script type="text/javascript">
	$(document).ready(function(){
		$('#asignacionDialog').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget); // Button that triggered the modal

		  var producto = button.data('producto');
		  var pendiente=button.data('pendiente');
		 
		  var modal = $(this);
		  modal.find("[name='descripcion']").val(button.data('descripcion'));
		  modal.find("[name='asignado']").val(button.data('asignado'));
		  modal.find("[name='pendiente']").val(button.data('pendiente'));
		  modal.find("[name='cantidad']").val(pendiente);
		  modal.find("[name='producto']").val(producto);
		  modal.find("[name='producto']").val(producto);
		  modal.find("[name='total']").val(button.data('total'));
		  
		  modal.find("#entregaDetId").val(button.data('entrega'))
		  
		});

	});
	$('body').on('shown.bs.modal', '.modal', function () {
			$('[id$=recipient-name]').focus();
	});

	
</script>		