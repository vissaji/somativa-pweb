<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/" var="urlNovo" />
<c:url value="/app/adm/movimentacao/cancelar" var="urlCancelar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<h1 class="display-5 text-center">Lista de movimetações Keeper</h1>

	<button class="btn btn-outline-secondary" type="reset">
		<a class="nav-link" href="${urlNovo }">Requisitar nova movimentação</a>
	</button>

	<div class="row">
		<div class="col-sm-6" style="margin: 10dp">
			<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				<c:forEach items="${movimentacoes }" var="movimentacao">
					<div class="card-header">Movimentação: ${movimentacao.id}</div>	

					<div class="card-body">
						<h5 class="card-title">
							Item: ${movimentacao.item.id} 
						</h5>
						<h6>Patrimônio: ${movimentacao.item.patrimonio.nome}</h6>
						<h6>Ambiente de origem: ${movimentacao.origem.nome}</h6>
						<h6>Ambiente de destino: ${movimentacao.destino.nome}</h6>
						<h6>Data de movimentação: ${movimentacao.dataMovimentacao}</h6>
						<h6>Transportador: ${movimentacao.transportador.nome}</h6>
					</div>
					
					<c:if test="${usuarioAutenticado eq 'ADMINISTRADOR'}">
						<button class="card-title btn btn-light">
							<a href="${urlCancelar}?id=${movimentacao.id}">Deletar</a>
						</button>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>