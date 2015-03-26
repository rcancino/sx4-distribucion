<div class="modal fade" id="cancelarAsignacionModal" 
  tabindex="-1" role="dialog" 
  aria-labelledby="cancelarAsignacionModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="cancelarAsignacionModalLabel">Cancelar asignación</h4>
      </div>
      <g:form  action="cancelarAsignacion" >

      <div class="modal-body">
        	<g:hiddenField id="surtidoField" name="id"/>
          <div class="form-group">
            <label for="recipient-name" class="control-label">Operador:</label>
            <input name="nip" type="password" class="form-control" id="recipient-name" 
              placeholder="Digite su NIP"
              autocomplete="off">
          </div>
         
        
      </div>
      <div class="modal-footer">
        <g:submitButton class="btn btn-danger" name="aceptar" value="Cancelar" />
      </div>
	</g:form>
				

    </div>
  </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
      $('#cancelarAsignacionModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); 
        var modal = $(this);
        var surtido=button.data('surtido');
        modal.find('#surtidoField').val(surtido);
      });
      
      $('body').on('shown.bs.modal', '.modal', function () {
          $('[id$=recipient-name]').focus();
      });

    });
  </script>