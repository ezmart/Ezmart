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
        <div>
            <nav>
                <div class="nav-wrapper">
                    <div style="margin-left: 40px" class="col s12">
                        <a href="<c:url value="/home"/>" class="">Início</a>
                        <!--<a href="<c:url value="/login"/>" class="">login</a> |-->
                        <!--<a href="<c:url value="/login"/>" class="">Esquenceu a senha?</a>-->
                    </div>
                </div>
            </nav>
        </div>

        <div class="container" >
            <div class="row">
                <h1 style="color: #2196f3">Olá!</h1>                
                <br/>
                <div class="col s5">
                    <p style="color: #898989; font-size: x-large">
                        OPS! Algo não ocorreu conforme o esperado.
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