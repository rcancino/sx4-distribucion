<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
      </div>
      <g:form  action="asignar" >

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
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        
        <g:submitButton class="btn btn-primary" name="aceptar" value="Asignar" />
      </div>
	</g:form>
				

    </div>
  </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
      $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var recipient = button.data('whatever'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.

        var modal = $(this);
        modal.find('.modal-title').text('Asignacion de pedido: ' + recipient);
       // modal.find('.modal-body input').val(recipient);
       var surtido=button.data('surtido');
       modal.find('#surtidoField').val(surtido);
       

      });
      
      $('body').on('shown.bs.modal', '.modal', function () {
          $('[id$=recipient-name]').focus();
      });

    });
  </script>