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
                            <a href="<c:url value="/quotation"/>" class="breadcrumb">Cotação</a>
                            <a href="<c:url value="!#"/>" class="breadcrumb">Resultado</a>
                        </div>
                    </div>
                </nav>
            </div>
            <c:if test="${empty establishmentProductList}">
                <div class="row" style="padding: 20px;">
                    <h4 class="center-align" style="color: #2196f3">Cadastre ao menos um produto primeiro =)</h4>
                </div>
            </c:if>

            <c:if test="${empty competitorList}">
                <div class="row" style="padding: 20px;">
                    <h4 class="center-align" style="color: #2196f3">Concorrente escolhido não cadastrou nenhum produto =(</h4>
                </div>
            </c:if>

            <c:if test="${not empty establishmentProductList}">
                <c:if test="${not empty competitorList}">
                    <div class="row">
                        <div class="col s12 m12">
                            <div class="card horizontal">
                                <div class="card-stacked">
                                    <div class="card-content" style="background-color: #F1F1F1">   
                                        <h4 class="center-align">Resultado da cotação</h4>
                                        <div class="row">
                                            <div class="col s12 m12">
                                                <ul>
                                                    <c:forEach items="${establishmentList}" var="establishment">
                                                        <div class="col s4">
                                                            <div class="card">
                                                                <div class="card-panel medium" id="scroll-product">
                                                                    <div class="col s12 m12">
                                                                        <div>
                                                                            <p>${establishment.businessName}</p>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col s12 m12">
                                                                        <c:forEach items="${establishment.productList}" var="product">
                                                                            <div class="card horizontal" style="padding: 0">
                                                                                <div class="card-content" id="scroll-product-list">   
                                                                                    <div class="col s12 m12">
                                                                                        <ul>
                                                                                            <div class=" center col s3" style="padding: 0">
                                                                                                <img class="responsive-img" src="<c:url value = "/resources/img/product/${product.product.id}.jpg"/>">
                                                                                            </div>
                                                                                            <div class="col s9">
                                                                                                <p><b>${product.product.name}</b></p>
                                                                                                <p><b>R$ ${product.price}</b></p>
                                                                                                <p><b>${product.product.barCode}</b></p>
                                                                                            </div>
                                                                                        </ul>
                                                                                    </div>
                                                                                </div>
                                                                            </div>    
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </ul>
                                            </div> 
                                        </div> 
                                    </div> 
                                </div> 
                            </div> 
                        </div> 
                    </div>  
                </c:if>
            </c:if>
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
    </body>
</html>
