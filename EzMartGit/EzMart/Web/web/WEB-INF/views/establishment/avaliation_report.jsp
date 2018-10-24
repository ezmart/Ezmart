<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
        <body>  
        <c:if test="${empty userLogged}">
            <c:import url="/WEB-INF/views/templates/header_logout.jsp"></c:import>
                <div class="container" >
                    <div class="row">
                        <h1 style="color: #2196f3">Ops!</h1>                
                        <br/>
                        <div class="col s5">
                            <p style="color: #898989; font-size: x-large">
                                Você não está logado no site!
                            </p>
                        </div>
                        <div class="col s4 offset-s2">
                            <img class="responsive-img" src="<c:url value = "/resources/img/register/carrinho_registro.png"/>">
                    </div>
                </div>
            </div> 
        </c:if>  
        <c:if test="${not empty userLogged}">
            <c:import url="/WEB-INF/views/templates/header_emporium.jsp"></c:import>
                <div>
                    <nav>
                        <div class="nav-wrapper">
                            <div style="margin-left: 40px" class="col s12">
                                <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                            <a href="<c:url value=""/>" class="breadcrumb">Dashboard Avaliação</a>
                        </div>
                    </div>
                </nav>
            </div>

            <br><br>

            <input type="number" style="display: none" id = "satisfaction" value="${avaliation.satisfactionConvert}">
            <input type="number" style="display: none" id = "priceProduct" value="${avaliation.productPriceConvert}">
            <input type="number" style="display: none" id = "prodDiversity" value="${avaliation.diversityConvert}">
            <input type="number" style="display: none" id = "employees" value="${avaliation.employeesConvert}">
            <input type="number" style="display: none" id = "ambience" value="${avaliation.ambienceConvert}">
            <canvas id="bar-chart" width="400" height="150"></canvas>

        </c:if>
        <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"/>"></script>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>