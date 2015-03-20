<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/> 
	<title>Embarque Nuevo</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="page-header">
			  	<h2>Alta de Embarques </h2>
			</div>

			<div class="col-sm-12">
				<g:form class="form form-horizontal" action="save" name="embarqueForm">
					<f:with bean="${embarqueInstance}">
						<f:field property="sucursal" input-class="form-control" cols="col-sm-8" colsLabel="col-sm-2" input-disabled="disabled"/>
						<f:field property="fecha" >
							<div class="form-group">
								<div class="col-sm-8">
									<input id="fecha" name="fecha" type="text" value="${embarqueInstance.fecha.format('dd/MM/yyyy')}" 
										class="form-control fecha" autocomplete="off">
								</div>
							</div>
						</f:field>
						<f:field property="chofer" input-class="form-control" cols="col-sm-8" colsLabel="col-sm-2" />
						<f:field property="comentario" input-class="form-control" cols="col-sm-8" colsLabel="col-sm-2"/>
					</f:with>
					<g:hasErrors bean="${embarqueInstance}">
						<div class="alert alert-danger col-md-offset-2  col-md-10">
							<g:renderErrors bean="${embarqueInstance}" as="list" />
						</div>
					</g:hasErrors>
					<div class="form-group">
						<div class="buttons  col-md-offset-2  col-md-2">
							<g:submitButton name="Generar" class="btn btn-primary  btn-block" value="Aceptar"/>
						</div>
					</div>
				</g:form>
			</div>
		</div>
		
		
	</div><!-- end .container-->

	<script type="text/javascript">
		$(document).ready(function(){
			$(".fecha").datepicker({});
			$('#chofer').focus();
		});	
	</script>
	

</body>

