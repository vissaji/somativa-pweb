<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/assets/imagens" var="urlAssetsImagens" />
<c:url value="/app/" var="urlItem" />
<c:url value="/app/movimentacao/lista" var="urlMovimentacao" />
<c:url value="/app/ambiente/lista" var="urlAmbiente" />
<c:url value="/app/patrimonio/lista" var="urlPatrimonio" />
<c:url value="/app/adm/usuario/lista" var="urlUsuario" />
<c:url value="/sair" var="urlSair" />

<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #762270 !important">
		<a class="navbar-brand" href="${urlItem}" style="color: white;"> <img
			src="${urlAssetsImagens }/logoKeeper.jpg" width="30" height="30"
			class="d-inline-block align-top" alt="Logo da Keeper"> Keeper
		</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo01"
			aria-controls="navbarTogglerDemo01" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item"><a class="nav-link" href="${urlItem }" style="color: white;">Itens</a></li>
				<li class="nav-item"><a class="nav-link" href="${urlMovimentacao }" style="color: white;">Movimentações</a></li>
				<li class="nav-item"><a class="nav-link" href="${urlAmbiente }" style="color: white;">Ambientes</a></li>
				<li class="nav-item"><a class="nav-link" href="${urlPatrimonio }" style="color: white;">Patrimônios</a></li>

				<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR'}">
					<li class="nav-item"><a class="nav-link" href="${urlUsuario }" style="color: white;">Usuários</a></li>
				</c:if>
			
				<li class="nav-item"><a class="nav-link" href="${urlSair }" style="color: white;">Sair</a></li>
			</ul>
		</div>
	</nav>
</header>