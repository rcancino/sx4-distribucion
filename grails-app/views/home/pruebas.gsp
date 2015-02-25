<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="layout" content="surtido"/>
		<title>Pruebas</title>
		
		<asset:stylesheet src="jquery-ui.css"/>
		<asset:javascript src="jquery-ui/autocomplete.js"/>
	</head>
	
	<body>
		<div class="container">


			
			<div class="row">
				<div class="col-md-12">
					<div class="well text-center">
						AREA DE PRUEBAS
					</div>
				</div>
			</div><!-- end .row 1 -->
			
			<div class="row">
				<label for="fechaField">Fecha</label>
				<input type="text" class="fechaField" id="fechaField">
			</div><!-- end .row 2 button panel -->
			
			<div class="row">
				<br>
				<label for="empleadoField">Empleado</label>
				<input type="text" class="" id="empleadoField">
			</div>
			
			
			
		</div>
	
	<script type="text/javascript">
		$(function(){
			$("#fechaField").datepicker({});
			$("#empleadoField").autocomplete({
			    source:'<g:createLink controller="usuario" action="getEmpleadosAsJSON"/>',
			    //source:'/usuario/getEmpleadosAsJSON',
			    minLength:3,
			    select:function(e,ui){
			      console.log('Valor seleccionado: '+ui.item.id);
			      $("#empleadoId").val(ui.item.id);
			    }
			});

			
			
		});

	</script>
		
	</body>
	
</html>
