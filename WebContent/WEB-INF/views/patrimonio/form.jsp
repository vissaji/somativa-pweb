<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/patrimonio/deletar" var="urlDeletar" />
<c:url value="/app/adm/patrimonio/salvar" var="urlSalvar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<h1 class="display-5 text-center">Cadastro de patrimônio Keeper</h1>

	<form:form modelAttribute="patrimonio" action="${urlSalvar}"
		method="post" cssClass="container">
		<form:hidden path="id"/>
		
		<div class="input-group">
			<form:input path="nome" class="form-control"
				placeholder="Insira o nome do patrimônio" aria-label="nome"
				aria-describedby="basic-addon2" />

			<form:select path="categoria" items="${categoria}" />

			<div class="input-group-append">
				<button class="btn btn-outline-secondary" type="submit">Salvar</button>

				<c:if test="${not empty patrimonio.id}">
					<button class="btn btn-outline-secondary" type="reset">
						<a href="${urlDeletar}?id=${patrimonio.id}">Deletar</a>
					</button>
				</c:if>
			</div>
		</div>

		<small id="nome" class="form-text text-muted"> 
			<form:errors path="nome" />
		</small>
	</form:form>
</body>
</html>