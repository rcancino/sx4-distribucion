<div class="modal fade" id="autorizacionDialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="autorizacionLabel">New message</h4>
      </div>
      <g:form  name="autorizarForm" action="autorizacion" >

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
        
        <g:submitButton class="btn btn-primary" name="aceptar" value="Autorizar" />
      </div>
	</g:form>
				

    </div>
  </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
      $('#autorizacionDialog').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var recipient = button.data('whatever'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.

        var modal = $(this);
        modal.find('.modal-title').text('Asignacion de pedido: ' + recipient);
       // modal.find('.modal-body input').val(recipient);
       var surtido=button.data('surtido');
       modal.find('#surtidoField').val(surtido);
       
       // $('input:checked').each(function(){
       //    console.log('Seleccion: '+$(this).data('surtido'));
       // });

      });
      
      $('body').on('shown.bs.modal', '.modal', function () {
          $('[id$=recipient-name]').focus();
      });

      $("form[name='autorizarForm']").submit(function(e){
          
          //var data = $(this).serializeArray();
          //var url = $(this).attr("action");
          //console.log('Submit form.....'+url);
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