<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/assets/imagens" var="urlAssetsImagens"/>
<c:url value="/autenticar" var="urlAutenticar"/>

<!DOCTYPE html>
<html>
<head>
<c:import url="templates/head.jsp" />
</head>
<body style="background-color: #762270">
	<h1 class="display-4 text-center" style="color: white;">
		Bem-vindo ao Keeper!
	</h1>
	
	<img alt="Logo da Keeper" src="${urlAssetsImagens }/logoKeeper.jpg" 
		class="rounded mx-auto d-block">
	
	<form:form modelAttribute="usuario" action="${urlAutenticar }" method="post" cssClass="container">
		<div class="form-group">
			<label for="email" style="color: white;">E-mail</label> 
			
			<form:input path="email" class="form-control" id="email"
				aria-describedby="emailHelp" placeholder="Insira o email"/> 
				
			<small id="emailHelp" class="form-text text-muted">
				<form:errors path="email" style="color: white;"/>
			</small>
		</div>
		
		<div class="form-group">
			<label for="password" style="color: white;">Senha</label> 
			
			<form:password path="senha" class="form-control" id="password"
				aria-describedby="passwordHelp" placeholder="Insira a senha"/> 
				
			<small id="passwordHelp" class="form-text text-muted">
				<form:errors path="senha" style="color: white;"/>
			</small>
		</div>
		
		<button type="submit" class="btn btn-secondary">Login</button>
	</form:form>
</body>
</html>