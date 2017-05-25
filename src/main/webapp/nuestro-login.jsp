<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Pagina de Login</title>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">


<style>
body {
	margin-top: 30px;
}
    	
.error {
	color: red;
}
table {
  border-collapse: separate;
  border-spacing:  10px;
}
</style>
</head>

<body>

	<div class="jumbotron">
		<div class="container">
		<h2>Concesionario ATSISTEMAS</h3>
		<h3>Entrar con Usuario y Contraseña</h3>

		<c:if test="${param.login_error ne null}">
			<font color="orange"> 
				El intento de conectar no tuvo éxito,
				intentelo de nuevo.<br /> Razón: 
				${SPRING_SECURITY_LAST_EXCEPTION.message}
			</font>
		</c:if>
		
		<form name="f" action="<c:url value='/nuestro-login.jsp'/>"	method="POST">
			<security:csrfInput/>
			<table>
				<tr>
					<td>Usuario:</td>
					<td>
						<input type="text" name="username" />
					</td>
				</tr>
				<tr>
					<td>Contraseña:</td>
					<td>
						<input type="password" name="password" />
					</td>
				</tr>
				<tr>
					<td colspan='2'>
						<input class="btn btn-primary btn-lg" name="submit" type="submit" value="Entrar" />
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	
		<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</body>
</html>



