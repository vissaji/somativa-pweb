<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/item/deletar" var="urlDeletar" />
<c:url value="/app/item/salvar" var="urlSalvar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<c:if test="${empty item.id }">
		<h1 class="display-5 text-center">Cadastro de itens Keeper</h1>
	</c:if>
	
	<c:if test="${not empty item.id }">
		<h1 class="display-5 text-center">Movimentação de itens Keeper</h1>
	</c:if>
	
	<form:form modelAttribute="item" action="${urlSalvar}" method="post" cssClass="container">
		<form:hidden path="id" />

		<c:if test="${empty item.id}">
			<div class="input-group">
				<input class="form-control" disabled="disabled" placeholder="Insira o patrimônio desejado para o item">
	
				<form:select path="patrimonio.id" items="${patrimonios}" itemLabel="nome" itemValue="id"/>
	
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="reset">Limpar</button>
				</div>
			</div>

			<div class="input-group">
				<input class="form-control" disabled="disabled"	placeholder="Insira o ambiente desejado">
	
				<form:select path="ambiente.id" items="${ambientes}" itemLabel="nome" itemValue="id"/>
	
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="submit">Salvar</button>
				</div>
			</div>
		</c:if>

		<c:if test="${not empty item.id}">
			<div class="input-group">
				<input class="form-control" disabled="disabled" placeholder="Ambiente de origem:">
				<form:select path="ambiente.id" items="${ambientes}" itemLabel="nome" itemValue="id" disabled="true" />
	
				<input class="form-control" disabled="disabled"	placeholder="Ambiente de destino:">
				<form:select path="ambiente.id" items="${ambientes}" itemLabel="nome" itemValue="id"/>
	
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="submit">Salvar</button>
	
					<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">				
						<button class="btn btn-outline-secondary" type="reset">
							<a href="${urlDeletar}?id=${itens.id}">Deletar</a>
						</button>
					</c:if>
				</div>
			</div>
		</c:if>
		
	</form:form>
</body>
</html>