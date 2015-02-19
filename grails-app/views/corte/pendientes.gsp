<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Cortes pendientes</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Pedido</th>
							<th>Producto</th>
							<th>Descripcion</th>
							<th>Fecha</th>
							<th>Cortes</th>
							<th>Entregado</th>
							<th>Liberacion</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${corteInstanceList}" var="row">
							<tr>
								<td>
									<a href="" data-toggle="modal" class="btn btn-default btn-lg btn-block">
										<g:formatNumber number="${row.pedido}" format="####"/>
									</a>
								</td>
								<td>${fieldValue(bean:row,field:"producto")}</td>
								<td>${fieldValue(bean:row,field:"descripcion")}</td>
								<td><g:formatDate date="${row.dateCreated}" format="hh:mm (dd-MM)"/></td>
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>
								<td>
									<g:if test="${row.inicio}">
										<g:formatDate date="${row.inicio}" format="hh:mm (dd-MM)"/>
									</g:if>
									<g:else>
										<a href="" data-toggle="modal" class="btn btn-warning btn-lg btn-block"
										data-target="#entregarModal"
										 data-pedido="${row.pedido}" data-descripcion="${row.descripcion}"
										 data-corte="${row.id}"
										data-producto="${row.producto}" data-cantidad="${row.cantidad}"
										data-cortes="${row.cortes}"
										 data-instruccion="${row.instruccion}">
										Entregar
									</a>
									</g:else>
									
								</td>
								<td>
									<g:if test="${row.asignado}">
										<a href="" data-toggle="modal" class="btn btn-success btn-lg btn-block"
										data-target="#exampleModal" data-whatever="${row.pedido}"> Cortado
										</a>
									</g:if>
									
								</td>
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->

		<g:render template="importarCortesDialog"/>

		<div class="modal fade" id="entregarModal" tabindex="-1" role="dialog" 
			aria-labelledby="entregarModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="entregarModalLabel">New message</h4>
		      </div>
		      <g:form  action="asignar" >
		      <div class="modal-body">
		        	<g:hiddenField id="corteField" name="id"/>
		          <fieldset disabled>
		          <div class="form-group">
		            <label for="pedido" class="control-label">Pedido</label>
		            <input name="pedido" type="text" class="form-control" >
		          </div>

		          <div class="form-group">
		            <label for="descripcion" class="control-label">Descripcion</label>
		            <input name="descripcion" type="text" class="form-control" >
		          </div>
		          <div class="form-group">
		            <label for="cantidad" class="control-label">Cantidad</label>
		            <input name="cantidad" type="text" class="form-control" >
		          </div>
		          <div class="form-group">
		            <label for="cortes" class="control-label">Cortes</label>
		            <input name="cortes" type="text" class="form-control" >
		          </div>
		          <div class="form-group">
		            <label for="instruccion" class="control-label">Instrucción</label>
		            <input name="instruccion" type="text" class="form-control" >
		          </div>
		          </fieldset>
		          <div class="form-group">
		            <label for="nip" class="control-label">Nip</label>
		            <input name="nip" type="password" class="form-control" id="recipient-name" >
		          </div>
		        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		         <g:submitButton class="btn btn-primary" name="aceptar" value="Entregar" />
		      </div>
		    </div>
		    </g:form>

		  </div>
		</div>

	</div><!-- end .container-->

	<script type="text/javascript">
		$(document).ready(function(){
			$('#entregarModal').on('show.bs.modal', function (event) {
			  var button = $(event.relatedTarget); // Button that triggered the modal
			  var producto = button.data('producto');
			  var cantidad=button.data('cantidad');
			  var pedido=button.data('pedido');
			  var modal = $(this);
			  modal.find('.modal-title').text('Engregando : ' + producto);
			  modal.find("[name='descripcion']").val(button.data('descripcion'));
			  modal.find("[name='cantidad']").val(cantidad);
			  modal.find("[name='pedido']").val(pedido);
			  modal.find("[name='instruccion']").val(button.data('instruccion'));
			  modal.find("[name='cortes']").val(button.data('cortes'));

			  modal.find('#corteField').val(button.data('corte'));
			});

		});
		$('body').on('shown.bs.modal', '.modal', function () {
  				$('[id$=recipient-name]').focus();
			});

	</script>

</body>

