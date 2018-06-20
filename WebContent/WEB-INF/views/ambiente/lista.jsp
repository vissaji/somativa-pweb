<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/ambiente/novo" var="urlNovo" />
<c:url value="/app/adm/ambiente/editar" var="urlEditar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<h1 class="display-5 text-center">Lista dos ambientes Keeper</h1>

	<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
		<button class="btn btn-outline-secondary" type="reset">
			<a class="nav-link" href="${urlNovo }">Cadastrar novo ambiente</a>
		</button>
	</c:if>

	<div class="row">
		<div class="col-sm-6">
			<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				<c:forEach items="${ambientes }" var="ambiente">
					<div class="card-header">
						<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
							<a href="${urlEditar}?id=${ambiente.id}" style="color: white;">
								ID do ambiente: ${ambiente.id}
							</a>
						</c:if>
					</div>

					<div class="card-body">
						<h5 class="card-title">
							Ambiente: ${ambiente.nome}
						</h5>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>