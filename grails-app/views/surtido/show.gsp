
<%@ page import="com.luxsoft.sx4.distribucion.Surtido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'surtido.label', default: 'Surtido')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-surtido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-surtido" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list surtido">
			
				<g:if test="${surtidoInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="surtido.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${surtidoInstance}" field="tipo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.asignado}">
				<li class="fieldcontain">
					<span id="asignado-label" class="property-label"><g:message code="surtido.asignado.label" default="Asignado" /></span>
					
						<span class="property-value" aria-labelledby="asignado-label"><g:fieldValue bean="${surtidoInstance}" field="asignado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.iniciado}">
				<li class="fieldcontain">
					<span id="iniciado-label" class="property-label"><g:message code="surtido.iniciado.label" default="Iniciado" /></span>
					
						<span class="property-value" aria-labelledby="iniciado-label"><g:formatDate date="${surtidoInstance?.iniciado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.finalizado}">
				<li class="fieldcontain">
					<span id="finalizado-label" class="property-label"><g:message code="surtido.finalizado.label" default="Finalizado" /></span>
					
						<span class="property-value" aria-labelledby="finalizado-label"><g:formatDate date="${surtidoInstance?.finalizado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.entregado}">
				<li class="fieldcontain">
					<span id="entregado-label" class="property-label"><g:message code="surtido.entregado.label" default="Entregado" /></span>
					
						<span class="property-value" aria-labelledby="entregado-label"><g:formatDate date="${surtidoInstance?.entregado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="surtido.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:fieldValue bean="${surtidoInstance}" field="cliente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.corte}">
				<li class="fieldcontain">
					<span id="corte-label" class="property-label"><g:message code="surtido.corte.label" default="Corte" /></span>
					
						<span class="property-value" aria-labelledby="corte-label"><g:formatBoolean boolean="${surtidoInstance?.corte}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="surtido.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${surtidoInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.pedido}">
				<li class="fieldcontain">
					<span id="pedido-label" class="property-label"><g:message code="surtido.pedido.label" default="Pedido" /></span>
					
						<span class="property-value" aria-labelledby="pedido-label"><g:fieldValue bean="${surtidoInstance}" field="pedido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surtidoInstance?.sucursal}">
				<li class="fieldcontain">
					<span id="sucursal-label" class="property-label"><g:message code="surtido.sucursal.label" default="Sucursal" /></span>
					
						<span class="property-value" aria-labelledby="sucursal-label"><g:fieldValue bean="${surtidoInstance}" field="sucursal"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:surtidoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${surtidoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
