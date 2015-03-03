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
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Surtido</h3>
							</div>
							<div class="panel-body">
								<div id="chart_div" class=""></div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Corte</h3>
							</div>
							<div class="panel-body">
								<div id="g_corte"></div>
							</div>
						</div>
					</div>
					
					
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Surtido</h3>
							</div>
							<div class="panel-body">
								<div id="piechart" style="width: 900px; height: 500px;"></div>
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
	      function drawChart() {

	        var data = google.visualization.arrayToDataTable([
	          ['Pedidos', '# per Day'],
	          ['Pendientes',     11],
	          ['En Corte',      2],
	          ['Corte terminado',  2],
	          ['Por entregar', 2],
	          ['Pendientes',    7]
	        ]);

	        var options = {
	          title: 'Resumen sucursal TACUBA'
	        };

	        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

	        chart.draw(data, options);
	      }

	      //google.load("visualization", "1", {packages:["gauge"]});
	      google.setOnLoadCallback(drawChart2);
	            function drawChart2() {

	              var data = google.visualization.arrayToDataTable([
	                ['Label', 'Value'],
	                ['Pendientes', 80],
	                ['Por Entregar', 55],
	                ['Network', 68]
	              ]);

	              var options = {
	                width: 400, height: 120,
	                redFrom: 90, redTo: 100,
	                yellowFrom:75, yellowTo: 90,
	                minorTicks: 5
	              };

	              var chart = new google.visualization.Gauge(document.getElementById('chart_div'));

	              chart.draw(data, options);

	              setInterval(function() {
	                data.setValue(0, 1, 40 + Math.round(60 * Math.random()));
	                chart.draw(data, options);
	              }, 13000);
	              setInterval(function() {
	                data.setValue(1, 1, 40 + Math.round(60 * Math.random()));
	                chart.draw(data, options);
	              }, 5000);
	              setInterval(function() {
	                data.setValue(2, 1, 60 + Math.round(20 * Math.random()));
	                chart.draw(data, options);
	              }, 26000);
	            };
	      google.setOnLoadCallback(function(){
	      	var data = google.visualization.arrayToDataTable([
	      	  ['Label', 'Value'],
	      	  ['Pendientes', 80],
	      	  ['En Corte', 55],
	      	  ['Entregados',20]
	      	]);

	      	var options = {
	      	  width: 400, height: 120,
	      	  redFrom: 90, redTo: 100,
	      	  yellowFrom:75, yellowTo: 90,
	      	  minorTicks: 5
	      	};

	      	var chart = new google.visualization.Gauge(document.getElementById('g_corte'));

	      	chart.draw(data, options);

	      	setInterval(function() {
	      	  data.setValue(0, 1, 40 + Math.round(60 * Math.random()));
	      	  chart.draw(data, options);
	      	}, 13000);
	      	setInterval(function() {
	      	  data.setValue(1, 1, 40 + Math.round(60 * Math.random()));
	      	  chart.draw(data, options);
	      	}, 5000);
	      	
	      });
	    </script>
	

</body>

