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
            <c:import url="/WEB-INF/views/templates/header_consumer.jsp">
            </c:import>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">
                                Início
                            </a>
                            <a href="<c:url value="!#"/>" class="breadcrumb"> 
                                Mercados mais próximos
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
            <div>
                <input type="text" style="display: none" name="establishmentList" id="establishmentList" value="">
                <!--    <var id="establishmentList"></var>-->
            </div>
            <div class="container" id="map">
                <div class="row">
                    <div class="col s12">
                        <div class="card">
                            <div class="card-content">
                                <c:import url="/WEB-INF/views/maps/google_maps_local_markets.jsp"/>
                                
                                
                            </div>
                        </div>
                    </div>
                </div> 
            </div>                 
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>

    </body>
</html>
