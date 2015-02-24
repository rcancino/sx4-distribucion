<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Surtido de pedidos</title>
</head>
<body>
	<div class="container">

		
		
		<div class="row">
			<div class="col-md-12">

				<g:if test="${flash.error}">
					<div class="alert alert-danger">
						<h4 class="text-center">${flash.error}</h4>
					</div>
				</g:if>
				<g:if test="${flash.success}">
					<div class="alert alert-success">
						<h4 class="text-center">${flash.success}</h4>
					</div>
				</g:if>
				<table class="table table-striped table-bordered table-condensed ">
					<thead>
						<tr >
							<th class="text-center">Pedido</th>
							<th>T</th>
							<th>Cliente</th>
							<th>Alta</th>
							<th>Partidas</th>
							<th>Cortes</th>
							<th  class="text-center">Status</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${surtidoInstanceList}" var="row">
							<tr class="text-center">
								<td>
									<g:if test="${row.status=='PENDIENTE'}">
										<a href="" data-toggle="modal" class="btn btn-info btn-lg btn-block"
											data-target="#exampleModal" data-whatever="${row.pedido}" data-surtido="${row.id}">
											<g:formatNumber number="${row.pedido}" format="####"/>
										</a>
									</g:if>
									<g:else>
										<a href="" data-toggle="modal" class="btn btn-success btn-lg btn-block">
											<g:formatNumber number="${row.pedido}" format="####"/>
										</a>
									</g:else>
									%{-- <g:link action="show" id="${row.id}" class="btn btn-info btn-lg btn-block">
									</g:link> --}%
								</td>
								<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
								<td>${fieldValue(bean:row,field:"cliente")}</td>
								<td><g:formatDate date="${row.pedidoCreado}" format="hh:mm (dd-MM)"/></td>
								<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>
								<td class="${row.status=='EN SURTIDO'?'success':'warning'}">
									${fieldValue(bean:row,field:"status")}
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->

		

		<g:render template="/importador/importarDialog"/>

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
		            <input name="nip" type="password" class="form-control" id="recipient-name" placeholder="Digite su NIP">
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

	</div><!-- end .container-->

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

</body>

