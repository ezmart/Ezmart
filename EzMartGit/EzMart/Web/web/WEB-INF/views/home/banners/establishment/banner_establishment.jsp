<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<ul class="slides">
    <li>
        <img src="<c:url value = "/resources/img/banners/establishment/establishment_3.png"/>"> <!-- random image -->
        <div class="caption right-align">
            <h3 class="light text-lighten-1"><b>Bem-vindo ao EzMart</b></h3>
            <h5 class="light text-lighten-1">${userLogged.name}</h5>
            <br/>
            <br/>
            <img style="width: 100px; height: 32px" src="<c:url value = "/resources/img/logo_branco.png"/>">
        </div>
    </li>
    <li>
        <img src="<c:url value = "/resources/img/banners/establishment/establishment_2.png"/>"> <!-- random image -->
        <div class="caption left-align">
            <br/>
            <img style="width: 360px; height: 112px" src="<c:url value = "/resources/img/logo_preto.png"/>">
            <h3 class="light text-lighten-1" style="color: #ffd600"><b>Ajudando a fazer a escolha certa.</b></h3>
            <h5 class="light grey-text text-lighten-2"></h5>
        </div>
    </li>
    <li>
        <img style="opacity: 0.2" src="<c:url value = "/resources/img/banners/establishment/establishment_1.png"/>"> <!-- random image -->
        <div class="caption center-align">
            <h3 class="light text-lighten-1" style="color: #000000"><b>Cadastre seus produtos hoje.</b></h3>
            <h3 class="light text-lighten-1" style="color: #000000"><b>Monte suas promoções e se destaque.</b></h3>
            <h3 class="light text-lighten-1" style="color: #000000"><b>Sempre a melhor escolha.</b></h3>
            <h6 class="light grey-text text-lighten-3"></h6>
        </div>
    </li>
</ul>