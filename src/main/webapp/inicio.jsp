<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>::Concesionario_ATSISTEMAS::</title>

<!-- Bootstrap -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<div class="jumbotron">
		<div class="container">
			<h1>Bienvenido <%= request.getUserPrincipal().getName() %></h1>
			<p>Gestión del concesionario ATSISTEMAS</p>
			<p>
			<sec:authorize access="hasRole('ROLE_COMERCIAL')">
	      	 	<a class="btn btn-primary btn-lg" href="<c:url value='Cliente/lista'/>" role="button">Clientes</a>
	        	<a class="btn btn-primary btn-lg" href="<c:url value='Pedido/lista'/>" role="button">Pedidos</a>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_GERENTE')">
	      	 	<a class="btn btn-primary btn-lg" href="<c:url value='Comercial/lista'/>" role="button">Comerciales</a>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_ADMINISTRATIVO')">
	      	 	<a class="btn btn-primary btn-lg" href="<c:url value='Vehiculo/lista'/>" role="button">Vehículos</a>
	        	<a class="btn btn-primary btn-lg" href="<c:url value='Factura/lista'/>" role="button">Facturas</a>
			</sec:authorize>
		
<!-- 				<a class="btn btn-primary btn-lg" href="Cliente/lista" role="button">Clientes</a> -->
<!-- 				<a class="btn btn-primary btn-lg" href="Vehiculo/lista" role="button">Vehículos</a> -->
<!-- 				<a class="btn btn-primary btn-lg" href="Comercial/lista" role="button">Comerciales</a> -->
<!-- 				<a class="btn btn-primary btn-lg" href="Pedido/lista" role="button">Pedidos</a> -->
<!-- 				<a class="btn btn-primary btn-lg" href="Factura/lista" role="button">Facturas</a> -->
			</p>
			<a class="btn btn-primary btn-lg"  href="<c:url value='/logout'/>" role="button">Cerrar Sesión</a>
		</div>
		
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>