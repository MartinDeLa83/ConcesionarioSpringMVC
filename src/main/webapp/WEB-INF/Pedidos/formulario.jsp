<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Gestion Pedidos</title>

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

<style>
body {
	margin-top: 30px;
}
    	
.error {
	color: red;
}
table {
  border-collapse: separate;
  border-spacing:  5px;
}
</style>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class=".col-md-6 .col-md-offset-3">
				<h3>Registrar pedido</h3>
				<form:form method="POST" action="${not empty tipoOperacion ? '../modificacion' : 'alta'}" modelAttribute="pedidoForm">
					<table>
					<form:hidden path="idPedido"/>
						<tr>								
								<td><form:label path="idCliente">Cliente</form:label></td>
								<td><form:select path="idCliente">
								    <form:options items="${listaClientes}"  itemValue="id" itemLabel="nombre"/></form:select><td>
 							
						</tr>
						<tr>						
								<td><form:label path="idComercial">Comercial</form:label></td>
								<td><form:select path="idComercial">
								    <form:options items="${listaComerciales}"  itemValue="id" itemLabel="nombre"/></form:select><td>
				
						</tr>
						<tr>	
								<td><form:label path="idVehiculo">Modelo</form:label></td>
								<td><form:select path="idVehiculo">
								    <form:options items="${listaVehiculos}"  itemValue="id" itemLabel="modelo"/></form:select><td>
				
 							
						</tr>
						<tr>
							<td><input type="submit" class="btn btn-primary" value="Registrar" /></td>
						</tr>

					</table>
				</form:form>

			</div>
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