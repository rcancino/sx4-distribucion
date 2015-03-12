<div class="modal fade" id="asignacionManualDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">Asignacion manual</h4>
      </div>
      <g:form  name="asignacionManualForm" action="asignacionManual" >

      <div class="modal-body">
        	<g:hiddenField id="surtidoField" name="id"/>
          <div class="form-group">
            <label for="recipient-name" class="control-label">Supervisor:</label>
            <input name="nip" type="password" class="form-control" id="recipient-name" 
              placeholder="Digite su NIP"
              autocomplete="off">
          </div>
          <div class="form-group">
            <label for="surtidor">
              <g:select id="surtidor" name='surtidor' class="form-control"
                  noSelection="${['null':'Seleccione un surtidor']}"
                  from="${com.luxsoft.sx4.sec.UsuarioRole.executeQuery("select l.usuario from UsuarioRole l where l.role.authority='SURTIDOR'")}"
                  optionKey="id" optionValue="nombre"></g:select>
            </label>
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
      $('#asignacionManualDialog').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); 
        var pedido = button.data('pedido'); 
        var modal = $(this);
        modal.find('.modal-title').text('Asignacion manual de pedido: ' + pedido);
        var surtido=button.data('surtido');
        modal.find('#surtidoField').val(surtido);
       
      });
      
      $('body').on('shown.bs.modal', '.modal', function () {
          $('[id$=recipient-name]').focus();
      });

      $("form[name='asignacionManualForm']").submit(function(e){
          var form=this;
          $('input:checked').each(function(i){
              var surtido=$(this).data('surtido')
              console.log('Anexando surtido: '+surtido);
              $('<input />').attr('type', 'hidden')
                .attr('name', "surtidos")
              .attr('value', surtido)
              .appendTo(form);
           });
          //e.preventDefault(); //STOP default action
      });

    });
  </script>