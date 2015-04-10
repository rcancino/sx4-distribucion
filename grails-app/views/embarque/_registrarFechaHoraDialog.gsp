<asset:stylesheet src="datepicker/jquery.datetimepicker.css"/>
<asset:javascript src="datepicker/jquery.datetimepicker.js"/> 
<div class="modal fade" id="registrarFechaHoraModal" tabindex="-1" role="dialog" aria-labelledby="registrarFechaHoraModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="registrarFechaHoraModalLabel">New message</h4>
      </div>
      <g:form  name="asignarForm" action="asignarFechaHora" controller="entrega">

      <div class="modal-body">
        	<g:hiddenField id="entregaField" name="id"/>
          <g:hiddenField id="tipoField" name="tipo"/>
          <div class="form-group">
            <label for="fechaHoraField" class="control-label">Fecha Hora:</label>
            <input name="fechaHora" 
              type="text" 
              class="form-control" 
              id="fechaHoraField">
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
      $('#registrarFechaHoraModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var tipo = button.data('tipo'); // Extract info from data-* attributes
        var modal = $(this);
        
        
       if(tipo==='arribo'){
          modal.find('.modal-title').text('Registrar ARRIBO de entrega');
       }else{
          modal.find('.modal-title').text('Registrar RECEPCION de entrega');
       }
        var entrega=button.data('entrega');
        modal.find('#entregaField').val(entrega);
        modal.find('#tipoField').val(tipo);
      });

      $('#fechaHoraField').datetimepicker(
        {
          format:'d/m/Y H:i',
          //formatDate:'d/m/Y',
          minDate:'${(new Date()-2).format('yyyy/MM/dd')}',
          //minDate:'-1970/01/02',
          //minDate:'2015/04/09',
          maxDate:0,
          lang:'es',
          closeOnDateSelect:true,
          step:5
        }
      );

    });
  </script>