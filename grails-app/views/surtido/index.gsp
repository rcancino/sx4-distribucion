
<%@ page import="com.luxsoft.sx4.distribucion.Surtido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'surtido.label', default: 'Surtido')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-surtido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-surtido" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="tipo" title="${message(code: 'surtido.tipo.label', default: 'Tipo')}" />
					
						<g:sortableColumn property="asignado" title="${message(code: 'surtido.asignado.label', default: 'Asignado')}" />
					
						<g:sortableColumn property="iniciado" title="${message(code: 'surtido.iniciado.label', default: 'Iniciado')}" />
					
						<g:sortableColumn property="finalizado" title="${message(code: 'surtido.finalizado.label', default: 'Finalizado')}" />
					
						<g:sortableColumn property="entregado" title="${message(code: 'surtido.entregado.label', default: 'Entregado')}" />
					
						<g:sortableColumn property="cliente" title="${message(code: 'surtido.cliente.label', default: 'Cliente')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${surtidoInstanceList}" status="i" var="surtidoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${surtidoInstance.id}">${fieldValue(bean: surtidoInstance, field: "tipo")}</g:link></td>
					
						<td>${fieldValue(bean: surtidoInstance, field: "asignado")}</td>
					
						<td><g:formatDate date="${surtidoInstance.iniciado}" /></td>
					
						<td><g:formatDate date="${surtidoInstance.finalizado}" /></td>
					
						<td><g:formatDate date="${surtidoInstance.entregado}" /></td>
					
						<td>${fieldValue(bean: surtidoInstance, field: "cliente")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${surtidoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
