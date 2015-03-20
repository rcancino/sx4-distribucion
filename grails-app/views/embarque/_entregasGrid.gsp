	%{-- 
	String documento
	String tipoDeDocumento
	Date fechaDeDocumento
	String documentoOrigen

	Long folio
	
	BigDecimal totalDocumento=0.0
	
	Boolean parcial=false
	String cliente
	String nombre
	
	int paquetes = 0
	BigDecimal kilos = 0
	BigDecimal cantidad = 0

	Date arribo
	Date recepcion
	String recibio

	String comentario

	BigDecimal valor=0.0
	BigDecimal porCobrar=0.0
	BigDecimal comision=1.1
	BigDecimal comisionPorTonelada=0.0
	BigDecimal importeComision=0.0
	Date fechaComision
	String comentarioDeComision --}%

<table id="documentosGrid" class="table table-striped table-bordered table-condensed ">
	<thead>
		<tr class="text-center">
			<td>Documento</td>
			<td>Tipo</td>
			<td>Fecha D</td>
			
			<td>P</td>
			<td>Cliente</td>
			<td>Arribo</td>
			<td>Recepción</td>
			<td>Kilos</td>
			<td>Valor</td>
			
			%{-- <td>Salida</td>
			<td>Regreso</td>
			<td>Comentario</td> --}%
		</tr>
	</thead>
	<tbody>
		<g:each in="${entregas}" var="row">
			<tr id="${row.id}">
				<td>
					${formatNumber(number:row.documento,format:'####')}
				</td>
				<td>${fieldValue(bean:row,field:"tipoDeDocumento")}</td>
				<td><g:formatDate date="${row.fechaDeDocumento}" format="dd/MM/yyyy"/></td>
				
				<td>
					<g:if test="${row.parcial}"><i class="fa fa-star-half-o"></i></g:if>
					<g:else><i class="fa fa-star"></i></g:else>
					
				</td>
				<td>${fieldValue(bean:row,field:"nombre")}</td>
				<td>
					<abbr title="${fieldValue(bean:row,field:'nombre')}">
						${org.apache.commons.lang.StringUtils.substring(row.nombre,0,20)}
					</abbr>
				</td>
				<td><g:formatDate date="${row.arribo}" format="HH:mm (MM/dd)"/></td>
				<td><g:formatDate date="${row.recepcion}" format="HH:mm (MM/dd)"/></td>
				<td>${formatNumber(number:row.kilos,format:'###.###')}</td>
				<td>${formatNumber(number:row.valor,type:'currency')}</td>

				%{-- 
				<td><g:formatDate date="${row.salida}" format="HH:mm (MM/dd)"/></td>
				<td><g:formatDate date="${row.regreso}" format="HH:mm (MM/dd)"/></td>
				<td>${fieldValue(bean:row,field:"comentario")}</td> --}%
			</tr>
		</g:each>
	</tbody>
</table>

