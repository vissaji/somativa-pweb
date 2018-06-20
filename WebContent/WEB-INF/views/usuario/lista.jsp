<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/usuario/novo" var="urlNovo" />
<c:url value="/app/adm/usuario/editar" var="urlEditar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<h1 class="display-5 text-center">Lista de usuários Keeper</h1>

	<button class="btn btn-outline-secondary" type="reset">
		<a class="nav-link" href="${urlNovo }">Cadastrar novo usuário</a>
	</button>

	<div class="row">
		<div class="col-sm-6" style="margin: 10dp">
			<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				<c:forEach items="${usuarios }" var="usuario">
					<div class="card-header">
						<a href="${urlEditar}?id=${usuario.id}" style="color: white;">
							ID do usuário: ${usuario.id}
						</a>
					</div>

					<div class="card-body">
						<h5 class="card-title">
							Usuário: ${usuario.nome} ${usuario.sobrenome}  
						</h5>

						<h6>E-mail: ${usuario.email}</h6>		
						<h6>Administrador: ${usuario.tipo}</h6>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>