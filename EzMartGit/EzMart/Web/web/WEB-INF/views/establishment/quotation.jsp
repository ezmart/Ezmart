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
                            <a href="<c:url value=""/>" class="breadcrumb">Cotação</a>
                        </div>
                    </div>
                </nav>
            </div>

            <div class="container">
                <h4>Cotação</h4>
                <br><br/>
                <c:if test="${empty establishmentList}">
                    <div class="row" style="padding: 20px;">
                        <h4 class="center-align" style="color: #2196f3">Nenhum outro Supermercado cadastrado!</h4>
                    </div>
                </c:if>
                <c:if test="${not empty establishmentList}">
                    <form method="POST">
                        <div class="row">
                            <div class="col s12 ">
                                <div class="form-group col s6">
                                    <label for="stateId">Escolha o concorrente:</label>
                                    <select class="form-control input-text-register" name="competitorId" id="competitorId" required>
                                        <option notselected></option>
                                        <c:forEach items="${establishmentList}" var="esblishment">
                                            <option id="competitorId" value="${esblishment.id}">${esblishment.businessName}</option>                            
                                        </c:forEach>
                                    </select>
                                    <div>
                                        <button class=" modal-action green btn-flat" type="submit" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:if>
            </div>  
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
