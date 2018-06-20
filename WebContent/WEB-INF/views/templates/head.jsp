<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/assets/imagens" var="urlAssetsImagens"/>
<c:url value="/assets/css" var="urlAssetsCss" />
<c:url value="/assets/js" var="urlAssetsJs" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Keeper Patrimônio</title>

<link rel="icon" href="${urlAssetsImagens}/favicon.ico" type="image/x-icon">

<link rel="stylesheet" href="${urlAssetsCss}/bootstrap.min.css"/>

<script src="${urlAssetsJs}/jquery-3.2.1.min.js"></script>
<script src="${urlAssetsJs}/bootstrap.min.js"></script>