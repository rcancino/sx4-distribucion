<table id="grid" class="table table-striped table-bordered table-condensed ">
	<thead>
		<tr >
			<th colspan="7"></th>
			<th colspan="3" class="text-center">Corte</th>
			<th colspan="4" class="text-center">Surtido</th>
		</tr>
		<tr>
			<th>Cliente</th>
			<th>Pedido</th>
			<th>Venta</th>
			<th>Fecha</th>
			<th>T</th>
			<th>Tipo</th>
			<th>Creado</th>
			<th>Inicio</th>
			<th>Fin</th>
			<th>Entregado</th>
			<th>Minutos</th>
			<th>Partidas</th>
			<th>Kilos</th>
			<th>Cortes</th>
		</tr>
	</thead>
	
	<tbody>
		<g:each in="${surtidoInstanceList}" var="row">
			<tr >
				<td>
					<a href="" data-toggle="tooltip" title="${row.nombre}">
						${fieldValue(bean:row,field:"cliente")}
					</a>
				</td>
				<td><g:formatNumber number="${row.pedido}" format="####"/></td>
				<td>${fieldValue(bean:row,field:"venta")}</td>
				<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
				<td>${fieldValue(bean:row,field:"tipoDeVenta")}</td>
				<td>${fieldValue(bean:row,field:"tipo")[0..0]}</td>
				<td><g:formatDate date="${row.iniciado}" format="HH:mm"/></td>
				<td><g:formatDate date="${row.corteInicio}" format="HH:mm"/></td>
				<td><g:formatDate date="${row.corteFin}" format="HH:mm"/></td>
				<td><g:formatDate date="${row.entregado}" format="HH:mm"/></td>
				<td><g:formatNumber number="${row.getAnalisis().getMinutosAcumulados()}" format="####"/></td>
				<td><g:formatNumber number="${row.partidas.size()}" format="####"/></td>
				<td><g:formatNumber number="${row.getAnalisis().getKilos()}" format="####.###"/></td>
				<td><g:formatNumber number="${row.getCortes()}" format="####"/></td>
			</tr>
		</g:each>
	</tbody>
	<tfoot>
		<th colspan="9"></th>
		<th> Total</th>
		<th>${surtidoInstanceList.sum(0.0) {it.getAnalisis().getMinutosAcumulados()}}</th>
		%{-- <th>${surtidoInstanceList.sum( 0.0 ),{it.partidas.size()}}</th>
		<th>${surtidoInstanceList.sum (0.0 ),{it.getAnalisis().getKilos()}}</th>
		<th>${surtidoInstanceList.sum (0.0) ,{it.partidas.getCortes()}}</th> --}%
	</tfoot>
</table>