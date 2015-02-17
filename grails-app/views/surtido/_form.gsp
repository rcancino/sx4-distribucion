<%@ page import="com.luxsoft.sx4.distribucion.Surtido" %>



<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="surtido.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tipo" from="${surtidoInstance.constraints.tipo.inList}" required="" value="${surtidoInstance?.tipo}" valueMessagePrefix="surtido.tipo"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'asignado', 'error')} ">
	<label for="asignado">
		<g:message code="surtido.asignado.label" default="Asignado" />
		
	</label>
	<g:textField name="asignado" maxlength="50" value="${surtidoInstance?.asignado}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'iniciado', 'error')} ">
	<label for="iniciado">
		<g:message code="surtido.iniciado.label" default="Iniciado" />
		
	</label>
	<g:datePicker name="iniciado" precision="day"  value="${surtidoInstance?.iniciado}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'finalizado', 'error')} ">
	<label for="finalizado">
		<g:message code="surtido.finalizado.label" default="Finalizado" />
		
	</label>
	<g:datePicker name="finalizado" precision="day"  value="${surtidoInstance?.finalizado}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'entregado', 'error')} ">
	<label for="entregado">
		<g:message code="surtido.entregado.label" default="Entregado" />
		
	</label>
	<g:datePicker name="entregado" precision="day"  value="${surtidoInstance?.entregado}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="surtido.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cliente" required="" value="${surtidoInstance?.cliente}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'corte', 'error')} ">
	<label for="corte">
		<g:message code="surtido.corte.label" default="Corte" />
		
	</label>
	<g:checkBox name="corte" value="${surtidoInstance?.corte}" />

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="surtido.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${surtidoInstance?.fecha}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'pedido', 'error')} required">
	<label for="pedido">
		<g:message code="surtido.pedido.label" default="Pedido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="pedido" required="" value="${surtidoInstance?.pedido}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surtidoInstance, field: 'sucursal', 'error')} required">
	<label for="sucursal">
		<g:message code="surtido.sucursal.label" default="Sucursal" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sucursal" required="" value="${surtidoInstance?.sucursal}"/>

</div>

