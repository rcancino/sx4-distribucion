<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Análisis de surtido ${surtidoInstance.pedido}</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<br>
			<div class="alert alert-info">
			  	<h2>Cliente: ${surtidoInstance.nombre} Pedido: ${surtidoInstance.pedido} </h2>
			</div>
			<g:if test="${flash.message}">
				<div class="alert alert-info">
			  		<h4>${flash.message}</h4>
				</div>
			</g:if>
		</div> <!-- end .row -->

		<div class="row">
			<div class="col-sm-6">
				<form class="form-horizontal">
					<f:with bean="${surtidoInstance}">
		    			<f:display property="sucursal" />
		    			<f:display property="fecha"/>
		    			<f:display property="pedido" />
		    			<f:display property="venta" />
		    			<f:display property="tipoDeVenta" label="Tipo"/>
					</f:with>
				</form>
			</div>

			<div class="col-sm-6">
				<form class="form-horizontal">
					<f:with bean="${surtidoInstance}">
		    			<f:display property="asignado" />
		    			<f:display property="iniciado" />
		    			<f:display property="entrego" />
		    			<f:display property="entregado"/>
					</f:with>
				</form>
			</div>
		</div>

		<div class="row grid-panel">
			<div class="col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						Partidas
					</div>
					<table class="table table-striped table-bordered table-condensed ">
						<thead>
							<tr>
								<th>Producto</th>
								<th>Descripción</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${surtidoInstance.partidas}" var="row">
								<tr>
									<td>${fieldValue(bean:row,field:"producto")}</td>
									<td>${fieldValue(bean:row,field:"descripcion")}</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<g:if test="${cortes}">
			<div class="row grid-panel">
				<div class="col-sm-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							Cortes
						</div>
						<table class="table table-striped table-bordered table-condensed ">
							<thead>
								<tr>
									<th>Producto</th>
									<th>Descripción</th>
									<th>Instrucción</th>
									<th>Cant</th>
									<th>Entregado</th>
									<th>Pendiente</th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${cortes}" var="row">
									<tr>
										<td>${fieldValue(bean:row,field:"producto")}</td>
										<td>${fieldValue(bean:row,field:"descripcion")}</td>
										<td>${fieldValue(bean:row,field:"instruccion")}</td>
										<td>${formatNumber(number:row.cantidad,format:'#,####.###')}</td>
										<td>${formatNumber(number:row.entregado,format:'#,####.###')}</td>
										<td>${formatNumber(number:row.pendiente,format:'#,####.###')}</td>
									</tr>
								</g:each>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</g:if>
	</div><!-- end .container-->
	

</body>

