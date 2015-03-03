<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Surtido de pedidos</title>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-12">
				<div class="page-header">
				  <h2>Surtido de pedidos <small>${fechaDeSurtido}</small></h2>
				</div>
			</div>

			<div class="col-md-2">
				%{-- <g:render template="sideBar"/> --}%
				<g:render template="sidebar"/>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-5">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Surtido</h3>
							</div>
							<div class="panel-body">
								<div id="surtidoGauge" ></div>
							</div>
						</div>
					</div>
					<div class="col-md-7">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Corte</h3>
							</div>
							<div class="panel-body">
								<div id=""></div>
								<div id="corteGauge" ></div>
							</div>
						</div>
					</div>
					
					
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Distribucion y desempeño</h3>
							</div>
							<div class="panel-body">
								<div id="piechart" style="width: 600px; height: 450px;"></div>
							</div>
						</div>
					</div>
				</div>
				
			</div>


		</div> 
		

	</div><!-- end .container-->

	<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart","gauge"]});
      google.setOnLoadCallback(drawChart);
      google.setOnLoadCallback(drawCorteChart);
      function drawChart() {

      	var url='<g:createLink controller="surtido" action="getResumenPorDiaAsJSON"/>'

        $.getJSON(url,{
        	chart:'pie'
        }).done(function(data){
        	var dataTable = new google.visualization.DataTable();
        	dataTable.addColumn('string', 'Etapa');
			dataTable.addColumn('number', 'Pedidos');
			$.each(data,function(index,value){
				//console.log('Agregando '+index+':'+value);
				dataTable.addRow([index,value]);
			});
			var chart = new google.visualization.PieChart(document.getElementById('piechart'));
			var options = {
          	  title: 'Surtido por etapas',
          	  is3D: true,
          	};
          	chart.draw(dataTable, options);
        });
      }
      google.setOnLoadCallback(function(){
      	var url='<g:createLink controller="surtido" action="getStatusPorDiaAsJSON"/>'
      	$.getJSON(url,{
      		fecha:"${session.fecha.format('dd/MM/yyyy')}"
      	}).done(function(data){
      		var dataTable = new google.visualization.DataTable();
      		dataTable.addColumn('string', 'Label');
			dataTable.addColumn('number', 'Value');
			$.each(data,function(index,value){
				//console.log('Agregando '+index+':'+value);
				dataTable.addRow([index,value]);
			});
			var options = {
				width: 400, height: 120,
			  	greenFrom:0,greenTo:5,
			  	yellowFrom:5, yellowTo: 10,
			  	redFrom: 10, redTo: 20,
			  	minorTicks: 1,max:20
			};
			var chart = new google.visualization.Gauge(document.getElementById('surtidoGauge'));
			chart.draw(dataTable, options);
      	});
      });

      function drawCorteChart(){
      	var url='<g:createLink controller="surtido" action="getStatusDeCorteAsJSON"/>'
      	$.getJSON(url,{
      		fecha:"${session.fecha.format('dd/MM/yyyy')}"
      	}).done(function(data){
      		var dataTable = new google.visualization.DataTable();
      		dataTable.addColumn('string', 'Label');
			dataTable.addColumn('number', 'Value');
			$.each(data,function(index,value){
				//console.log('Agregando '+index+':'+value);
				dataTable.addRow([index,value]);
			});
			var options = {
				width: 400, height: 120,
			  	greenFrom:0,greenTo:5,
			  	yellowFrom:5, yellowTo: 10,
			  	redFrom: 10, redTo: 20,
			  	minorTicks: 1,max:20
			};
			var chart = new google.visualization.Gauge(document.getElementById('corteGauge'));
			chart.draw(dataTable, options);
      	});
      };

	      
	      
	    </script>
	

</body>

