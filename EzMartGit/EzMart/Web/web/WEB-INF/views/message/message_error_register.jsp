<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
        <body>       
        <c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">inicio</a>
                        <a href="<c:url value="/login"/>" class="breadcrumb">autenticação</a>
                        <a href="<c:url value="/register"/>" class="breadcrumb">cadastro</a>
                        <a href="#!" class="breadcrumb">cadastro consumidor</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="container" >
            <div class="row">
                <h1 style="color: #2196f3">Ops!</h1>                
                <br/>
                <div class="col s5">
                    <p style="color: #898989; font-size: x-large">
                        Algo de inesperado ocorreu enquanto fazíamos seu cadastro no sistema, 
                        tente novamente ou entre em contato com a nossa equipe!
                    </p>
                </div>
                <div class="col s4 offset-s2">
                    <img class="responsive-img" src="<c:url value = "/resources/img/register/carrinho_registro.png"/>">
                </div>
            </div>
        </div> 
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>