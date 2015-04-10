<table id="documentosGrid" class="table table-striped table-bordered table-condensed ">
	<thead>
		<tr class="text-center">
			<td>Documento</td>
			<td>Tipo</td>
			<td>Fecha D</td>
			<td>Cliente</td>
			<td>Arribo</td>
			<td>Recepción</td>
			<td>Kilos</td>
			%{-- <td>Valor</td> --}%
			<td></td>
		</tr>
	</thead>
	<tbody>
		<g:each in="${entregas}" var="row">
			<tr id="${row.id}" class="${row.parcial}?'warning':''">
				<td>
					<g:link controller="entrega" class="btn btn-primary btn-block"
						action="show" id="${row.id}" title="Detalle de entrega" data-toggle="tooltip" >
						${formatNumber(number:row.documento,format:'####')}
					</g:link>
				</td>
				<td>${fieldValue(bean:row,field:"tipoDeDocumento")}</td>
				<td><g:formatDate date="${row.fechaDeDocumento}" format="dd/MM/yyyy"/></td>
				<td>
					<abbr title="${fieldValue(bean:row,field:'nombre')}">
						${org.apache.commons.lang.StringUtils.substring(row.nombre,0,20)}
					</abbr>
				</td>
				<td>
					<g:if test="${row.embarque.salida!=null  }">
						<g:if test="${!row.recepcion}">
							<a  href="#registrarFechaHoraModal" class="btn btn-primary btn-block" 
								data-toggle="modal"
								 data-tipo="arribo"
								 data-entrega="${row.id}"> 
								<g:if test="${!row.arribo}">Registrar</g:if>
								<g:else><g:formatDate date="${row.arribo}" format="HH:mm (MM/dd)"/></g:else>
							</a>
						</g:if>
						<g:else><g:formatDate date="${row.arribo}" format="HH:mm (MM/dd)"/></g:else>
						
					</g:if>
				</td>
				<td>
					<g:if test="${row.embarque.regreso==null}">
						<g:if test="${row.embarque.salida!=null && row.arribo!=null  }">
							<a href="#registrarFechaHoraModal" class="btn btn-primary btn-block"
								data-toggle="modal" 
								data-tipo="recepcion"
								data-entrega="${row.id}"> 
								<g:if test="${!row.recepcion}">Registrar</g:if>
								<g:else><g:formatDate date="${row.recepcion}" format="HH:mm (MM/dd)"/></g:else>
							</a>
						</g:if>
					</g:if>
					<g:else><g:formatDate date="${row.recepcion}" format="HH:mm (MM/dd)"/></g:else>
					
				</td>
				<td>${formatNumber(number:row.kilos,format:'###.###')}</td>
				%{-- <td>${formatNumber(number:row.valor,type:'currency')}</td> --}%
				<td>
					<g:if test="${row.recepcion==null }">
						<g:link action="eliminarEntrega" id="${row.id}" onclick="return confirm('Eliminar la entrega del documento: '+${row.documento});">
							<i class="fa fa-trash"></i>
						</g:link>
					</g:if>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>


