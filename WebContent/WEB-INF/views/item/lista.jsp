<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/item/novo" var="urlNovo" />
<c:url value="/app/item/editar" var="urlEditar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<h1 class="display-5 text-center">Bem-vindo ${usuarioAutenticado.nome }!</h1>

	<button class="btn btn-outline-secondary" type="reset">
		<a class="nav-link" href="${urlNovo }">Cadastrar novo item</a>
	</button>

	<div class="row">
		<div class="col-sm-6" style="margin: 10dp">
			<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				<c:forEach items="${itens }" var="item">
					<div class="card-header">
						<a href="${urlEditar}?id=${item.id}" style="color: white;">
							Id do item: ${item.id}
						</a>
					</div>

					<div class="card-body">
						<h6 class="card-title">
							Patrimônio: ${item.patrimonio.nome}
						</h6>
						
						<h6 class="card-title">
							Ambiente: ${item.ambiente.nome}
						</h6>
						
						<h6 class="card-title">
							Usuário: ${item.usuario.nome}
						</h6>
						
						<h6 class="card-title">
							Data: ${item.data}
						</h6>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>