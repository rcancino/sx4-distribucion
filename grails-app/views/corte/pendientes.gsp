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
				
				<div class="col-md-6 ">
					<div class="btn-group">
						<input type='text' class="form-control" id="filtro" placeholder="Filtrar"  autofocus="on">
					</div>
					
					<div class="btn-group toolbar-panel">
					                <button type="button" name="operaciones"
					                	class="btn btn-default dropdown-toggle" data-toggle="dropdown"
					                    role="menu">
					                    Operaciones <span class="caret"></span>
									</button>
					                <ul class="dropdown-menu">
										<li><a href="#importarDialog" data-toggle="modal"> 
												<i class="fa fa-upload"></i> Importar
											</a>
					                	</li>
									</ul>
					</div>
					<div class="btn-group">
					    <button type="button" name="reportes"
					            class="btn btn-default dropdown-toggle" data-toggle="dropdown"
					            role="menu">
					            Reportes <span class="caret"></span>
					    </button>

					    <ul class="dropdown-menu">
					        
					    </ul>
					</div>
				</div>
			
		</div><!-- end .row toolbar -->
		
		<div class="row">
			<div class="col-md-12">
				%{-- <g:render template="grid"/> --}%
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Pedido</th>
							<th>T</th>
							<th>Alta</th>
							<th>Producto</th>
							<th>Descripcion</th>
							<th>Cortes</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${corteInstanceList}" var="row">
							<tr>
								<td>
									<a href="" data-toggle="modal" class="btn btn-info btn-lg btn-block"
										data-target="#exampleModal" data-whatever="@PEDIDO">
										<g:formatNumber number="${row.pedido}" format="####"/>
									</a>
								</td>
								<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
								<td>${fieldValue(bean:row,field:"producto")}</td>
								<td>${fieldValue(bean:row,field:"descripcion")}</td>
								<td><g:formatDate date="${row.dateCreated}" format="hh:mm (dd-MM)"/></td>
								<td><g:formatNumber number="${row.cortes}" format="####"/></td>
								
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

		</div> <!-- end .row 2-->

		<g:render template="importarCortesDialog"/>

		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
		      </div>
		      <div class="modal-body">
		        <form>
		          <div class="form-group">
		            <label for="recipient-name" class="control-label">Recipient:</label>
		            <input type="text" class="form-control" id="recipient-name">
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="control-label">Message:</label>
		            <textarea class="form-control" id="message-text"></textarea>
		          </div>
		        </form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary">Send message</button>
		      </div>
		    </div>
		  </div>
		</div>

	</div><!-- end .container-->

	<script type="text/javascript">
		$(document).ready(function(){
			$('#exampleModal').on('show.bs.modal', function (event) {
			  var button = $(event.relatedTarget) // Button that triggered the modal
			  var recipient = button.data('whatever') // Extract info from data-* attributes
			  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			  var modal = $(this)
			  modal.find('.modal-title').text('New message to ' + recipient)
			  modal.find('.modal-body input').val(recipient)
			})

		}
	</script>

</body>

