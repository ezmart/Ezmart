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
        <div id="modal-add" class="modal">
            <div class="modal-content">
                <c:if test="${not empty userLogged}">
                    <h4>Adicionar Produto<i class=" small material-icons" style="margin-left: 10px;">note_add</i></h4>
                    <p><font size="5">Selecione a lista que deseja adicionar o produto: </font></p>
                    <div class="modal-footer">
                        <!--<form method="POST" action="/ezmartWeb/productDelete">-->
                        <div class="form-group col s6">
                            <label for="cityId">Lista:</label>
                            <select class="form-control input-text-register" name="cityId" id="cityId">
                                <option notselected></option>
                                <c:forEach items="${cityList}" var="city">
                                    <option <c:if test="${cityIdConsumer eq city.id}">selected="true"</c:if> id="cityId" value="${city.id}">${city.name}</option>                            
                                </c:forEach>
                            </select>
                            <span style="color: orangered">${errors.cityId}</span>
                        </div>
                        <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Não</a>
                        <button class="btn btn-sm btn-default btn-small green" style="color:white; margin-right: 10px;" >Sim</button>
                        <!--</form>-->
                    </div>
                </c:if>
                <c:if test="${empty userLogged}">
                    <div class="modal-footer">
                        <h4>Ops!!! Você não está logado no sistema.<i class=" small material-icons" style="margin-left: 10px;">sentiment_very_dissatisfied</i></h4>
                        <a class="btn btn-sm btn-default btn-small green modal-close modal-action" style="color:white;">Fechar</a>

                    </div>
                </c:if>
            </div>
        </div>
        <div id="modal-view" class="modal">
            <div class="row">
                <div class="col s12 m12">
                    <div class="modal-footer">
                        <div style="background-color: #F1F1F1" class="card-panel medium">
                            <div style="padding: 20px;">
                                <div style="align-content: center" class="card-panel medium col s4">
                                    <div class="">
                                        <img class="responsive-img-center" id="productImgValue" src="<c:url value = "/resources/img/product/${product.id}.jpg"/>" alt="${product.barCode} - ${product.name}" width="120">
                                    </div>
                                </div>
                                <br/>
                                <input style="color: #000000; border: 0" id="productNameModal" name="productNameModal" type="text" class="validate" disabled="">
                                <input style="color: #000000; border: 0" id="productSectorNameModal" name="productSectorNameModal" type="text" class="validate" disabled="">
                                <input style="color: #000000; border: 0" id="productBrandModal" name="productBrandModal" type="text" class="validate" disabled="">
                                <input style="color: #000000; border: 0" id="productProviderNameModal" name="productProviderNameModal" type="text" class="validate" disabled="">
                                <a class="btn btn-sm btn-default btn-small green modal-close modal-action" style="color:white; margin: -10px">Fechar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="slider">
            <ul class="slides" >
                <li>
                    <img src="<c:url value = "/resources/img/banners/img_fundo_mercado.png"/>"> <!-- random image -->
                    <div class="caption center-align">
                        <h3 class="light text-lighten-1">Bem vindo ao EzMart</h3>
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
                        <div class="card-content" style="background-color: #F1F1F1">   
                            <h4 class="center-align">Promoções</h4>
                            <!-- Listar uma lista de produtos e serviços aqui -->
                            <div class="row">
                                <div class="col s12 m12">
                                    <ul>
                                        <c:forEach items="${productList}" var="product">
                                            <li class="col l4 m6 s12" id="modal-teste">
                                                <div class="card-panel medium" id="scroll-product">
                                                    <div class="row" style="padding: 20px;">
                                                        <div>
                                                            <img class="responsive-img-center" src="<c:url value = "/resources/img/product/${product.id}.jpg"/>" alt="${product.barCode} - ${product.name}" width="150">
                                                        </div>
                                                        <br/>
                                                        <!--<p style="font-size: 18px"><b>Id:</b> ${product.id}</p>-->
                                                        <p style="font-size: 18px"><b>Nome:</b> ${product.name}</p>
                                                        <!--<p style="font-size: 18px"><b>Código EAN:</b> ${product.barCode}</p>-->
                                                        <p style="font-size: 18px; margin-top: 10px"><b>Marca:</b> ${product.brand}</p>
                                                        <!--<p style="font-size: 18px; margin-top: 10px"><b>Linha:</b> ${product.sector.name}</p>-->
                                                        <!--<p style="font-size: 18px"><b>Fornecedor:</b> ${product.provider.name}</p>-->
                                                        <div class="card-action card-content" style="margin-top: 20px">
                                                            <div class="col s12 center">
                                                                <!--<a class="btn btn-sm btn-default btn-small ffc400 amber accent-3 modal-trigger" href="#modal-update" style="color:white;" onclick="setProductEdit(${product.id}, '${product.name}', '${product.barCode}', '${product.brand}')"><i class="material-icons center">edit</i></a>-->
                                                                <a class="btn btn-sm btn-danger btn-small green modal-trigger" href="#modal-add"  style="color:white;" title="Clique para adicionar o produto a sua lista"><i class="material-icons center">add</i></a>
                                                                <a class="btn btn-sm btn-danger btn-small blue-grey modal-trigger" href="#modal-view"  style="color:white;" 
                                                                   onclick="setModalData(
                                                                                   '${product.name}',
                                                                                   '<c:url value = "/resources/img/product/${product.id}.jpg"/>',
                                                                                   '${product.sector.name}',
                                                                                   '${product.brand}',
                                                                                   '${product.provider.name}'
                                                                                   )" title="Visualizar produto"><i class="material-icons center">view_compact</i></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
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
