<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/ambiente/deletar" var="urlDeletar" />
<c:url value="/app/adm/ambiente/salvar" var="urlSalvar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<h1 class="display-5 text-center">Cadastro de ambiente Keeper</h1>

	<form:form modelAttribute="ambiente" action="${urlSalvar}"
		method="post" cssClass="container">
		<form:hidden path="id"/>
		
		<div class="input-group">
			<form:input path="nome" class="form-control"
				placeholder="Insira o nome do ambiente" aria-label="nome"
				aria-describedby="basic-addon2"/>
			
			<div class="input-group-append">
				<button class="btn btn-outline-secondary" type="submit">Salvar</button>
				
				<c:if test="${not empty ambiente.id}">
					<button class="btn btn-outline-secondary" type="reset">
						<a href="${urlDeletar}?id=${ambiente.id}">Deletar</a>
					</button>
				</c:if>
			</div>
		</div>
		
		<small id="nome" class="form-text text-muted">
			<form:errors path="nome"/>
		</small>
	</form:form>
</body>
</html>