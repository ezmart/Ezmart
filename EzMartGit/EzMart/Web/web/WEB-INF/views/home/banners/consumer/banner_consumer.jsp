<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<ul class="slides">
    <li>
        <img src="<c:url value = "/resources/img/banners/consumer/consumer_1.png"/>"> <!-- random image -->
        <div class="caption right-align">
            <h3 class="light text-lighten-1"><b>Bem vindo ao EzMart</b></h3>
            <h5 class="light text-lighten-1">${userLogged.name}</h5>
            <br/>
            <br/>
            <img style="width: 100px; height: 32px" src="<c:url value = "/resources/img/logo_branco.png"/>">
        </div>
    </li>
    <li>
        <img src="<c:url value = "/resources/img/banners/consumer/consumer_2.png"/>"> <!-- random image -->
        <div class="caption right-align">
            <br/>
            <img style="width: 360px; height: 112px" src="<c:url value = "/resources/img/logo_preto.png"/>">
            <h3 class="light text-lighten-1" style="color: #000000"><b>Faça já a sua lista.</b></h3>
            <h5 class="light grey-text text-lighten-2"></h5>
        </div>
    </li>
    <li>
        <img style="opacity: 0.2" src="<c:url value = "/resources/img/banners/consumer/consumer_3.png"/>"> <!-- random image -->
        <div class="caption center-align">
            <h3 class="light text-lighten-1" style="color: #000000"><b>Sempre com os melhores produtos.</b></h3>
            <h3 class="light text-lighten-1" style="color: #000000"><b>Sempre com o menor preço.</b></h3>
            <h3 class="light text-lighten-1" style="color: #000000"><b>Sempre a melhor escolha.</b></h3>
            <h6 class="light grey-text text-lighten-3"></h6>
        </div>
    </li>
</ul>