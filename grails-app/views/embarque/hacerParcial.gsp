<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/>
	<title>Entrega parcial ${entregaInstance.id}</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="well">
			  	<h2>Entrega parcial Documento: ${entregaInstance.documento}  (${entregaInstance.nombre}) </h2>
			  	<h3>Total docto: ${formatNumber(number:entregaInstance.totalDocumento,type:'currency')}</h3>

			</div>

		</div> <!-- end .row -->

		<div class="row">
			<div class="page-header">
				<h4>Partidas del documento</h4>
				<g:form action="agregarEntregaParcial" id="${entregaInstance.id}">
				<table id="grid" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<td>Folio</td>
							<td>Clave</td>
							<td>Descripcion</td>
							<td>Cantidad</td>
							<td>Asignado</td>
							<td>Pendiente</td>
							<td>Por Asignar</td>
							<td>Kilos</td>
							<td>Importe</td>
						</tr>
					</thead>
					<tbody>
						<g:each in="${documentos}" var="row">
							<td>${formatNumber(number:row.id,format:'####')}</td>
							<td>${row.producto}</td>
							<td>${row.descripcion}</td>
							<td>${formatNumber(number:row.cantidad,format:'####.###')}</td>
							<td>${formatNumber(number:row.asignado,format:'####.###')}</td>
							<td>${formatNumber(number:row.pendiente,format:'####.###')}</td>
							<td name="data">
								<g:hiddenField name="partidas.ventaDet" value="${row.ventaDet.id}"/>
								<g:field type="text" name="partidas.cantidad" 
									required="" value="${row.pendiente}"/>
							</td>
							<td>${formatNumber(number:row.ventaDet.kilos,format:'####.###')}</td>
							<td>${formatNumber(number:row.valor,type:'currency')}</td>
						</g:each>
					</tbody>
				</table>
				<div class="form-group">
					<div class="buttons   col-md-5">
						<g:submitButton name="aplicar" class="btn btn-primary  " value="Aceptar"/>
						<g:link action="show" id="${entregaInstance.embarque.id}" class="btn btn-default"> Cancelar</g:link>
					</div>

				</div>
				</g:form>
			</div>
		</div>
		
	</div><!-- end .container-->

	<script type="text/javascript">
		$(function(){
			
		});
	</script>
	

</body>

