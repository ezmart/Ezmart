<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
        <body>
        <c:if test="${not empty userLogged}">
            <c:if test="${userLogged.userType eq 'admin'}">
                <c:import url="/WEB-INF/views/templates/header_admin.jsp"></c:import>
            </c:if>
            <c:if test="${userLogged.userType eq 'consumer'}">
                <c:import url="/WEB-INF/views/templates/header_consumer.jsp"></c:import>
            </c:if>
            <c:if test="${userLogged.userType eq 'emporium'}">
                <c:import url="/WEB-INF/views/templates/header_emporium.jsp"></c:import>
            </c:if>
        </c:if>
        <c:if test="${empty userLogged}">
            <c:import url="/WEB-INF/views/templates/header_logout.jsp"></c:import>
        </c:if>     
        <div class="slider">
            <ul class="slides" >
                <li>
                    <img src="<c:url value = "/resources/img/banners/img_fundo_mercado.png"/>"> <!-- random image -->
                    <div class="caption center-align">
                        <h3 class="light text-lighten-1">Bem vindo ao seu perfil!</h3>
                        <h5 class="light text-lighten-1">${userLogged.name}</h5>
                        <br/>
                        <br/>
                        <img style="width: 100px; height: 32px" src="<c:url value = "/resources/img/logo_branco.png"/>">
                    </div>
                </li>
                <li>
                    <img src="<c:url value = "/resources/img/banners/img_fundo_mercado_1.png"/>"> <!-- random image -->
                    <div class="caption right-align">
                        <img style="width: 100px; height: 32px" src="<c:url value = "/resources/img/logo_branco.png"/>">
                        <h5 class="light grey-text text-lighten-2"></h5>
                    </div>
                </li>
                <li>
                    <img style="opacity: 0.5" src="<c:url value = "/resources/img/banners/img_fundo_mercado_2.png"/>"> <!-- random image -->
                    <div class="caption right-align">
                        <img style="width: 100px; height: 32px" src="<c:url value = "/resources/img/logo_preto.png"/>">
                        <h6 class="light grey-text text-lighten-3"></h6>
                    </div>
                </li>
            </ul>
        </div>
        <!--<div class="container">-->
        <div class="row">
            <div class="col s12 m12">
                <div class="card horizontal">
                    <div class="card-stacked">
                        <div class="card-content">   
                            <h4 class="center-align">Propagandas</h4>
                            <!-- Listar uma lista de produtos e serviços aqui -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--</div>-->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
