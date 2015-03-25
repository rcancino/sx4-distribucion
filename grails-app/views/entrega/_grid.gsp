<table id="grid" class="table table-striped table-bordered table-condensed ">
	<thead>
		<tr class="text-center">
			<td>Producto</td>
			<td>Descripción</td>
			%{-- <td>Asignado</td> --}%
			<td>Pendiente</td>
			<td>Cantidad</td>
			<td>Valor</td>
			<td></td>
		</tr>
	</thead>
	<tbody>
		<g:each in="${partidas}" var="row">
			<tr id="${row.id}">
				<td>
					<a href="#asignacionDialog" class="btn btn-primary btn-block"
						data-toggle="${row.entrega.embarque.salida==null?'modal':''}"
						data-producto="${row.producto.clave}"
						data-descripcion="${row.producto.descripcion}"
						data-asignado="${row.asignado}"
						data-pendiente="${row.pendiente}"
						data-cantidad="${row.cantidad.abs()}"
						data-entrega="${row.id}"
						data-total="${row.ventaDet.cantidad.abs()}"
						id="${row.id}" >
						${row.producto.clave}
					</a>
				</td>
				<td>${fieldValue(bean:row,field:"producto.descripcion")}</td>
				%{-- <td>${formatNumber(number:row.asignado.abs(),format:'###.###')}</td> --}%
				<td>${formatNumber(number:row.pendiente.abs(),format:'###.###')}</td>
				<td>${formatNumber(number:row.cantidad.abs(),format:'###.###')}</td>
				<td>${formatNumber(number:row.valor,type:'currency')}</td>
				<td>
					<g:if test="${row.entrega.embarque.salida==null}">
						<g:link action="eliminarEntrega" id="${row.id}" onclick="return confirm('Eliminar la entrega parcial del 	producto: '+${row.producto});">
							<i class="fa fa-trash"></i>
						</g:link>
					</g:if>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>

<g:render template="entregaDetDialog"/>