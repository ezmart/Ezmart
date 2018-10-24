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
                            <a href="<c:url value="/promotion"/>" class="breadcrumb">Minhas Promoções</a>
                            <a href="<c:url value=""/>" class="breadcrumb">Produtos</a>
                        </div>
                    </div>
                </nav>
            </div>
            <!--<div class="container">-->
            <div class="col s12 center">
                <h4>Produtos</h4>
            </div>
            <br><br/>
            <!--                <input type="text" name="searchProduct" id="searchProduct" style="width: 50%; margin-left: 20%;">
                            <a class="btn btn-sm btn-default #ac1925 modal-trigger"  onclick="setDadaModalProductList()"style="color:ffc400 amber accent-3;"><i class="material-icons right">search</i>PESQUISAR</a>
                            <br><br/>-->
            <c:if test="${empty establishmentProductList}">
                <div class="row" style="padding: 20px;">
                    <h4 class="center-align" style="color: #2196f3">Nenhum produto encontrado!</h4>
                </div>
            </c:if>
            <c:if test="${not empty establishmentProductList}">

                <div id="modal-create" class="modal">
                    <div class="modal-content">
                        <h4>Preço Promocional<i class=" small material-icons" style="margin-left: 10px;">attach_money</i></h4>
                        <div class="row">
                            <form class="col s12" method="POST">
                                <div>
                                    <input id="productPrice" name="priceProduct" type="text" class="validate" placeholder="Digite o preço aqui" onKeyPress="return(monetario(this, '.', ',', event))" required>
                                    <input type="text" style="display: none"  name="establishmentProductId" id = "idEstablishmentProduct" required>
                                    <input type="text" style="display: none"  name="promotionId" id = "idPromotion" value="${promotionId}" required>
                                    <input type="text" style="display: none"  name="establishmentId" id = "idEstablishment" value="${establishmentId}" required>
                                </div>
                                <div class="modal-footer">
                                    <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">VOLTAR</a>
                                    <button class=" modal-action green btn-flat" type="submit" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!--<div class="container">-->
                <div class="row">
                    <div class="col s12 m12">
                        <ul>
                            <c:forEach items="${establishmentProductList}" var="establishmentProduct">
                                <li class="col l4 m6 s12" >
                                    <div class="card-panel medium" id="scroll-product-promotion">
                                        <div class="row" style="padding: 20px;">
                                            <div>
                                                <img class="responsive-img-center" src="<c:url value = "/resources/img/product/${establishmentProduct.product.id}.jpg"/>" alt="${establishmentProduct.product.barCode} - ${establishmentProduct.product.name}" width="150">
                                            </div>
                                            <br/>
                                            <p style="font-size: 18px"><b>Nome:</b> ${establishmentProduct.product.name}</p>
                                            <p style="font-size: 18px"><b>Código EAN:</b> ${establishmentProduct.product.barCode}</p>
                                            <p style="font-size: 18px"><b>Marca:</b> ${establishmentProduct.product.brand}</p>
                                            <p style="font-size: 18px"><b>Linha:</b> ${establishmentProduct.product.sector.name}</p>
                                            <p style="font-size: 18px"><b>Fabricante:</b> ${establishmentProduct.product.provider.name}</p>
                                            <c:if test="${isVisualizar eq true}">
                                                <p style="font-size: 18px"><b>Preço promocional:</b> ${establishmentProduct.priceConvert}</p>
                                            </c:if>
                                            <c:if test="${isVisualizar eq false}">
                                                <p style="font-size: 18px"><b>Preço:</b> ${establishmentProduct.priceConvert}</p>
                                                <a class="btn btn-sm btn-danger btn-small green modal-trigger" onclick="setEstablishmentProductId(${establishmentProduct.id})" href="#modal-create" style="color:white;margin-left: 10%;"><i class="material-icons right">add_shopping_cart</i>ADICIONAR</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <!--</div>-->
                <!--                    <ul class="pagination center" >
                <%--<c:if test="${(offset - limit)>=0}">--%>
                    <li class="waves-effect"><a href="<c:url value="/meuperfil?limit=${limit}&offset=${offset-limit}"/>"><i class="material-icons">chevron_left</i></a></li>
                <%--</c:if>--%>
                <%--<c:if test="${(limit+offset)< count}">--%>
                <li class="waves-effect"><a href="<c:url value="/meuperfil?limit=${limit}&offset=${offset+limit}"/>"><i class="material-icons">chevron_right</i></a></li>
                <%--</c:if>--%>
        </ul>-->
            </c:if>
            <!--</div>-->  
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
