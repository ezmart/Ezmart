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
        <!--<form class="gb_ag" action="/store/search" target="" id="gbqf" method="get" name="gbqf"><fieldset class="gbxx"><legend class="gbxx">Campos ocultos</legend><div id="gbqffd"></div></fieldset><fieldset class="gbqff gb_R" id="gbqff"><legend class="gbxx">Pesquisar</legend><div id="gbfwa" class="gbqfwa "><div id="gbqfqw" class="gbqfqw"><div id="gbqfaa"></div><div id="gbqfqwb" class="gbqfqwb"><input id="gbqfq" class="gbqfif" name="q" type="text" autocomplete="off" value="" placeholder="Pesquisar" aria-label="Pesquisar" role="combobox" aria-autocomplete="list"></div><div id="gbqfab"></div></div></div></fieldset><div class="gb_R gb_9f" id="gbqfbw"><button class="gbqfb" aria-label="Pesquisa Google" name="" id="gbqfb"><span class="gbqfi gb_fc"></span></button></div></form>-->
        <nav>
            <div class="nav-wrapper">
                <div class="container">
                    <form method="GET" action="/ezmartWeb/searthProduct">
                        <div class="input-field">
                            <input style="height: 60px; font-size: x-large" name="search" id="search" type="search" placeholder="Pesquise pelo produto" required>
                            <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                            <i class="material-icons">close</i>
                        </div>
                    </form>
                </div>
            </div>
        </nav>
        <div id="modal-add" class="modal">
            <div class="modal-content">
                <c:if test="${not empty userLogged}">
                    <h4>Adicionar Produto<i class=" small material-icons" style="margin-left: 10px;">note_add</i></h4>
                    <p><font size="5">Selecione a lista que deseja adicionar o produto: </font></p>
                    <div class="modal-footer">
                        <form method="POST" action="/ezmartWeb/newproducts">
                            <input type="text" style="display: none" name="productId" id="productId" value="${product.id}">
                            <div class="form-group col s6">
                                <label for="listId">Lista:</label>
                                <select class="form-control input-text-register" name="listId" id="listId">
                                    <option notselected></option>
                                    <c:forEach items="${shoppingList}" var="list">
                                        <option  id="listId" value="${list.id}">${list.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Não</a>
                            <button class="btn btn-sm btn-default btn-small green" style="color:white; margin-right: 10px;" >Sim</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
        <div id="modal-view" class="modal">
            <div class="modal-footer">
                <div style="background-color: #F1F1F1" class="card-panel medium">
                    <div class="row">
                        <h3>Produto</h3>
                        <div class="col s12">
                            <div class="card-panel medium col s4" style="align-content: center">
                                <img class="responsive-img-center" id="productImgValue" src="<c:url value = "/resources/img/product/${product.id}.jpg"/>" alt="${product.barCode} - ${product.name}" width="120">
                            </div>
                            <div class="col s8">
                                <input style="color: #000000; border: 0" id="productNameModal" name="productNameModal" type="text" class="validate" disabled="">
                                <!--<input style="color: #000000; border: 0" id="productSectorNameModal" name="productSectorNameModal" type="text" class="validate" disabled="">-->
                                <input style="color: #000000; border: 0" id="productBrandModal" name="productBrandModal" type="text" class="validate" disabled="">
                                <input style="color: #000000; border: 0" id="productProviderNameModal" name="productProviderNameModal" type="text" class="validate" disabled="">
                            </div>
                        </div>
                        <a class="btn btn-sm btn-default btn-small green modal-close modal-action" style="color:white; margin: -10px">Fechar</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="slider">
            <c:if test="${not empty userLogged}">
                <c:if test="${userLogged.userType eq 'admin'}">
                    <c:import url="/WEB-INF/views/home/banners/consumer/banner_consumer.jsp"></c:import>
                </c:if>
                <c:if test="${userLogged.userType eq 'consumer'}">
                    <c:import url="/WEB-INF/views/home/banners/consumer/banner_consumer.jsp"></c:import>
                </c:if>
                <c:if test="${userLogged.userType eq 'emporium'}">
                    <c:import url="/WEB-INF/views/home/banners/establishment/banner_establishment.jsp"></c:import>
                </c:if>
            </c:if>
            <c:if test="${empty userLogged}">
                <c:import url="/WEB-INF/views/home/banners/consumer/banner_consumer.jsp"></c:import>
            </c:if>
        </div>
        <!--<div class="container">-->
        <div class="row">
            <div class="col s12 m12">
                <div class="card horizontal">
                    <div class="card-stacked">
                        <div class="card-content" style="background-color: #F1F1F1">   
                            <h4 class="center-align">Produtos: ${count} encontrados</h4>
                            <div class="row">
                                <div class="col s12 m12">
                                    <ul>
                                        <c:forEach items="${productList}" var="product">
                                            <li class="col l4 m6 s12" id="modal-teste">
                                                <div class="card-panel medium" id="scroll-product">
                                                    <div class="row" style="padding: 20px;">
                                                        <c:if test="${product.value > 0.0}">
                                                            <p class="center" style="font-size: 18px; background-color: red; color: white"><b>Em promoção:</b> R$ ${product.value}</p>
                                                            <p class="center" style="font-size: 18px; background-color: red; color: white"><b>Mercado:</b> ${product.aux}</p>
                                                            <br/>
                                                        </c:if>
                                                        <div>
                                                            <img class="responsive-img-center" src="<c:url value = "/resources/img/product/${product.id}.jpg"/>" alt="${product.barCode} - ${product.name}" width="150">
                                                        </div>
                                                        <br/>
                                                        <p style="font-size: 18px"><b>Nome:</b> ${product.name}</p>
                                                        <p style="font-size: 18px; margin-top: 10px"><b>Marca:</b> ${product.brand}</p>
                                                        <div class="card-action card-content" style="margin-top: 20px">
                                                            <div class="col s12 center">
                                                                <c:if test="${userLogged.userType eq 'consumer'}">
                                                                    <a class="btn btn-sm btn-danger btn-small green modal-trigger" onclick="setProductId(${product.id})" href="#modal-add"  style="color:white;" title="Clique para adicionar o produto a sua lista"><i class="material-icons center">add</i></a>
                                                                </c:if>

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
            <ul class="pagination center" >
                <c:if test="${(offset-limit)>=0}">
                    <li class="waves-effect"><a href="<c:url value="/searthProduct?limit=${limit}&offset=${offset-limit}"/>"><i class="material-icons">chevron_left</i></a></li>
                    </c:if>
                    <c:if test="${(limit+offset)<count}">
                    <li class="waves-effect"><a href="<c:url value="/searthProduct?limit=${limit}&offset=${offset+limit}"/>"><i class="material-icons">chevron_right</i></a></li>
                    </c:if>
            </ul>
        </div>
        <!--</div>-->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
