<div class="modal fade" id="entregaDeSurtidoModal" tabindex="-1" role="dialog" 
  aria-labelledby="entregaDeSurtidoModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="entregaDeSurtidoModal">Entrega de pedido</h4>
      </div>
      <g:form  action="entregarSurtido" >

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
      $('#entregaDeSurtidoModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); 
        var recipient = button.data('whatever'); 
        var modal = $(this);
        modal.find('.modal-title').text('Asignacion de pedido: ' + recipient);
       
        var surtido=button.data('surtido');
        modal.find('#surtidoField').val(surtido);
      });
      
      $('body').on('shown.bs.modal', '.modal', function () {
          $('[id$=recipient-name]').focus();
      });

    });
  </script>