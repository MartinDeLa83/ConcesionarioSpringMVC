<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Lista de pedidos</title>

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
</style>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="span12">
			<h3>Lista de Pedidos</h3>
				<form method="get" action="buscar" class="form-inline">
					<input name="id" type="text" placeholder="id"> 
					<button type="submit" class="btn btn-primary">Buscar</button>
					<a href="lista" class="btn btn-primary" role="button">Todos los Pedidos</a>
					<a href="formulario" class="btn btn-primary" role="button">Lanzar Pedido</a>
					<a href="../inicio.jsp" class="btn btn-primary" role="button">Menu Principal</a>
				</form>				
			</div>
		</div>

		<div class="row">
			<table
				class="table table-bordered table-striped table-hover table-condensed table-responsive">
				<thead>
					<tr>
						<th>id</th>
						<th>Modelo</th>
						<th>Comercial</th>
						<th>Cliente</th>
						<th>Estado</th>
						<th>Fecha Lanzamiento</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaPedidos}" var="pedido">
						<tr>
							<td>${pedido.id}</td>
							<td>${pedido.vehiculo.modelo}</td>
							<td>${pedido.comercial.nombre}</td>
							<td>${pedido.cliente.nombre}</td>
							<td>${pedido.estado}</td>
							<td>${pedido.fecha}</td>
							<td>
					 			<c:choose>
						            <c:when test="${pedido.estado.equals('en proceso')}">
						            	<a href="generarFactura/${pedido.id}" class="btn btn-primary" role="button">Generar Factura</a>	
						            </c:when>
						            <c:otherwise>
						            	<font color="tral">Factura anteriormente generada</font>
						            </c:otherwise>
					        	</c:choose>
					
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
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