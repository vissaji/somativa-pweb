<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/app/adm/usuario/deletar" var="urlDeletar" />
<c:url value="/app/adm/usuario/salvar" var="urlSalvar" />

<!DOCTYPE html>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<c:import url="../templates/header.jsp" />

	<h1 class="display-5 text-center">Cadastro de usuários Keeper</h1>

	<form:form modelAttribute="usuario" action="${urlSalvar}" method="post"
		cssClass="container">
		<form:hidden path="id" />

		<div class="form-group">
			<label for="nome">Nome</label>

			<form:input path="nome" class="form-control" id="nome"
				aria-describedby="nomeHelp" placeholder="Insira o nome" />

			<small id="nomeHelp" class="form-text text-muted"> <form:errors
					path="nome" />
			</small>
		</div>

		<div class="form-group">
			<label for="sobrenome">Sobrenome</label>

			<form:input path="sobrenome" class="form-control" id="sobrenome"
				aria-describedby="sobrenomeHelp" placeholder="Insira o sobrenome" />

			<small id="sobrenomeHelp" class="form-text text-muted"> <form:errors
					path="sobrenome" />
			</small>
		</div>

		<c:if test="${empty usuario.id}">
			<div class="form-group">
				<label for="email">E-mail</label>

				<form:input path="email" class="form-control" id="email"
					aria-describedby="emailHelp" placeholder="Insira o email" />

				<small id="emailHelp" class="form-text text-muted"> <form:errors
						path="email" />
				</small>
			</div>

			<div class="form-group">
				<label for="senha">Senha</label>

				<form:password path="senha" class="form-control" id="senha"
					aria-describedby="senhaHelp" placeholder="Insira a senha" />

				<small id="senhaHelp" class="form-text text-muted"> <form:errors
						path="senha" />
				</small>
			</div>

			<div class="form-group">
				<label for="confirmarSenha">Confirmar senha</label> 
				
				<input	type="password" id="inputConfirmaSenha" name="confirmarSenha"
					class="form-control" id="confirmarSenha"
					aria-describedby="confirmarSenhaHelp"
					placeholder="Confirme a senha">
			</div>
		</c:if>

		<div class="form-group form-check">
			<form:checkbox path="administrador" class="form-check-input"
				id="checkbox" />

			<label class="form-check-label" for="checkbox">Administrador</label>
		</div>

		<div class="input-group-append">
			<button class="btn btn-outline-secondary" type="submit">Salvar</button>

			<c:if test="${not empty usuario.id}">
				<button class="btn btn-outline-secondary" type="reset">
					<a href="${urlDeletar}?id=${usuario.id}">Deletar</a>
				</button>
			</c:if>
		</div>
	</form:form>
</body>
</html>