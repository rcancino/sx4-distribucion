<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
	<title>Surtido de pedidos</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-12">
				<div class="page-header">
				  <h2>Surtido de pedidos 
				  	<a href="#selectorDeFechaDialog" data-toggle='modal' title="Cambiar fecha">
				  		<small >${fechaDeSurtido.format('dd / MMM / yyyy')}</small>
				  	</a>
				  </h2>
				</div>
			</div>

			<div class="col-md-2">
				%{-- <g:render template="sideBar"/> --}%
				<g:render template="sidebar"/>
			</div>

			<div class="col-md-10">
				<ul class="nav nav-tabs" role="tablist">
					<g:each in="${surtidosMap?.keySet()}" var="surtidor" status="i">
						<li class="${i==0}?'active':''">
							<a href="#${surtidor}" aria-controls="home" role="tab" data-toggle="tab">${surtidor}</a>
						</li>
					</g:each>
				</ul>
				
				<div class="tab-content">
				    
				    <g:each in="${surtidosMap}" var="entry" status="i">
				    	<div class="tab-pane ${i==0}?'active':''" id="${entry.key}"> 
				    		<g:render template="porDiaGrid" model="[surtidoInstanceList:entry.value]"/>
				    	</div>
				    </g:each>
				</div>
				
				
				
				
			</div>

		</div> <!-- end .row 2-->
		<g:render template="/_common/selectorDeFecha" model="['destino':'porDia']"/>

	</div><!-- end .container-->

	<script type="text/javascript">
		$(function(){

	    	// $('#grid').dataTable( {
	     //    	"paging":   false,
	     //    	"ordering": false,
	     //    	"info":     false
	     //    	,"dom": '<"toolbar col-md-4">rt<"bottom"lp>'
	    	// } );
	    	// var table = $('#grid').DataTable();
	    	// $("#filtro").on('keyup',function(e){
	    	// 	$('#grid').DataTable().search($(this).val()).draw();
	    	// });
	    	
			
			
		});
	</script>

</body>

