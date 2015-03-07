<div class="modal fade" id="agregarAuxiliarModal" 
  tabindex="-1" role="dialog" 
  aria-labelledby="agregarAuxiliarModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="agregarAuxiliarModalLabel">Auxiliar</h4>
      </div>
      <g:form  action="agregarAuxiliar" class="form-horizontal">

      <div class="modal-body">
        	<g:hiddenField id="corteField" name="id"/>
          <g:hiddenField id="tipoField" name="tipo" vale="${tipo}"/>
          
          <div class="form-group">
            <label for="tipoField" class="col-sm-2  control-label">Tipo</label>
            <div class="col-sm-10">
                <input class="form-control" id="tipoName" 
                  autocomplete="off" readonly="true">
            </div>
          </div>

          <div class="form-group">
            <label for="recipient-name" class=" col-sm-2 control-label">Operador:</label>
            <div class="col-sm-10">
                <input name="nip" type="password" class="form-control" id="recipient-name" 
                  placeholder="Digite su NIP"
                  autocomplete="off">
            </div>
            
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
      $('#agregarAuxiliarModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var recipient = button.data('pedido'); // Extract info from data-* attributes
        var tipo=button.data('tipo'); 
        var corte=button.data('corte'); 
        var modal = $(this);
        if(tipo==='CORTADOR'){
          modal.find('.modal-title').text('Cortador auxiliar pedido: ' + recipient);
        }
        //modal.find('.modal-title').text('Auxiliar en scorte de pedido: ' + recipient);
       modal.find('#corteField').val(corte);
       modal.find('#tipoField').val(tipo);
       modal.find('#tipoName').val(tipo);
       

      });
      
      $('body').on('shown.bs.modal', '.modal', function () {
          $('[id$=recipient-name]').focus();
      });

    });
  </script>