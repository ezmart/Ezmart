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
                            <a href="<c:url value="/product_establishment"/>" class="breadcrumb">Meus Produtos</a>
                            <a href="<c:url value=""/>" class="breadcrumb">Produtos</a>
                        </div>
                    </div>
                </nav>
            </div>

            <div id="modal-create" class="modal">
                <div class="modal-content">
                    <h4>Preço<i class=" small material-icons" style="margin-left: 10px;">attach_money</i></h4>
                    <div class="row">
                        <form class="col s12" method="POST">
                            <div>
                                <input id="productPrice" name="priceProduct" type="text" class="validate" placeholder="Digite o preço aqui" onKeyPress="return(monetario(this, '.', ',', event))" required>
                                <input type="text" style="display: none"  name="productId" id = "idProduct" required>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">VOLTAR</a>
                                <button class=" modal-action green btn-flat" type="submit" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="container">
                <h4>Produtos</h4>
                <br><br/>
                <input type="text" name="searchProduct" id="searchProduct" style="width: 50%; margin-left: 20%;">
                <a class="btn btn-sm btn-default #ac1925 modal-trigger"  style="color:ffc400 amber accent-3;"><i class="material-icons right">search</i>PESQUISAR</a>
                <br><br/>
                <div class="row">
                    <div class="col s12 m12">
                        <ul>
                            <c:forEach items="${productList}" var="product">
                                <li class="col l4 m6 s12" >
                                    <div class="card-panel medium">
                                        <div class="row" style="padding: 20px;">
                                            <div>
                                                <img class="responsive-img-center" src="<c:url value = "/resources/img/product/${product.id}.jpg"/>" alt="${product.barCode} - ${product.name}" width="150">
                                            </div>
                                            <br/>
                                            <p style="font-size: 18px"><b>Nome:</b> ${product.name}</p>
                                            <p style="font-size: 18px"><b>Código EAN:</b> ${product.barCode}</p>
                                            <p style="font-size: 18px"><b>Marca:</b> ${product.brand}</p>
                                            <p style="font-size: 18px"><b>Linha:</b> ${product.sector.name}</p>
                                            <p style="font-size: 18px"><b>Fabricante:</b> ${product.provider.name}</p>
                                            <a class="btn btn-default btn-small green modal-trigger" href="#modal-create" style="color:white;margin-left:10%;" onclick="setProductEstablishmentId(${product.id})"><i class="material-icons right">add_box</i>ADICIONAR</a>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
</html>
