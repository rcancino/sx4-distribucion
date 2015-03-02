<div class="panel panel-info">
  
	<div class="panel-heading">
		<h3 class="panel-title">Consultas</h3>
	</div>
  
  	<nav:menu id="nav" scope="user/embarque/consultas" class="nav nav-tabs nav-stacked" custom="true">
  		<g:if test="${!active}">
  			<li>
  				<p:callTag tag="g:link" attrs="${linkArgs}">
                    <span>
                        <nav:title item="${item}"/>
                    </span>
  				</p:callTag>
  			</li>
  		</g:if>
  	</nav:menu>
  
</div>


<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">Operaciones</h3>
	</div>
</div>


<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">Reportes</h3>
	</div>
</div>