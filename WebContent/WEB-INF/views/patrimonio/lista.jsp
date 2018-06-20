<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/patrimonio/novo" var="urlNovo" />
<c:url value="/app/adm/patrimonio/editar" var="urlEditar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<h1 class="display-5 text-center">Lista dos patrimônios Keeper</h1>

	<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
		<button class="btn btn-outline-secondary" type="reset">
			<a class="nav-link" href="${urlNovo }">Cadastrar novo patrimonio</a>
		</button>
	</c:if>

	<div class="row">
		<div class="col-sm-6" style="margin: 10dp">
			<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				<c:forEach items="${patrimonios }" var="patrimonio">
					<div class="card-header">
						<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
							<a href="${urlEditar}?id=${patrimonio.id}" style="color: white;">
								ID do patrimônio: ${patrimonio.id}
							</a>
						</c:if>
						
						<c:if test="${usuarioAutenticado.tipo eq 'COMUM'}">
							ID do patrimônio: ${patrimonio.id}							
						</c:if>
					</div>

					<div class="card-body">									
						<h5 class="card-title">
							Patrimônio ${patrimonio.nome}
						</h5>
						
						<h6>Categoria: ${patrimonio.categoria}</h6>
						<h6>Usuário: ${patrimonio.usuario.nome}</h6>
						<h6>Data de cadastro: ${patrimonio.dataCadastro}</h6>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>