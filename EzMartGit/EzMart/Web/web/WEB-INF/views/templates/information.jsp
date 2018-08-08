<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
        <body>
        <c:if test="${not empty  userLogged}">
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
        <div class="container" >
            <div class="card">
                <div class="row">
                    <div class="center-align col m6 s6">
                        <h4 style="color: #000000; padding: 20px">EzMart</h4>
                        <div>
                            <h2>Quem Somos ?</h2>
                            <h1>
                                
                            </h1>
                        </div>
                    </div>

                    <div class="col s6">
                        <img src="<c:url value = "/resources/img/register/carrinho_registro.png"/>">
                    </div>
                </div>
            </div>
        </div>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
    </body>
</html>
