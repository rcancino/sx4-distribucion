<%@page expressionCodec="none" %>
<asset:stylesheet src="jquery-ui.css"/>
<asset:javascript src="jquery-ui/autocomplete.js"/>
<g:hiddenField id="choferId" name="${property}.id" value="${value?.id}" />
<input type="text" id="${property}" name="${property}Nombre"  class="form-control clienteField" value="${value}"></input>

<script type="text/javascript">
$(function(){
	$(".clienteField").autocomplete({
			source:'<g:createLink controller="chofer" action="getChoferesAsJSON"/>',
			minLength:3,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#choferId").val(ui.item.id);
			}
	});
});
</script>



