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
            <c:import url="/WEB-INF/views/templates/header_admin.jsp"></c:import>
                <div id="modal-create" class="modal">
                    <div class="modal-content">
                        <h4>Adicionar Produto<i class=" small material-icons" style="margin-left: 10px;">add_box</i></h4>
                        <div class="row">
                            <form class="col s12" enctype="multipart/form-data" method="POST" >
                                <div>
                                    <label for="nome">Nome</label>
                                    <input id="productName" name="nameProduct" type="text" class="validate" required>
                                </div>
                                <div>
                                    <label for="codBarras">Código de Barras</label>
                                    <input id="productBarCode" name="barCode" maxlength="13" pattern="[0-9]+$" title="Somente números!" type="text" class="validate" required>
                                </div>
                                <div>
                                    <label for="marca">Marca</label>
                                    <input id="productBrand" name="brandProduct" type="text" class="validate">
                                </div>
                                <div>
                                    <label for="sector">Linha</label>
                                    <select name="sectorIdProduct" id="productSectorId" required>
                                        <option value=""></option>
                                    <c:forEach items="${sectorList}" var="sector">
                                        <option value="${sector.id}" id="sectorId">${sector.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <label for="provider">Fabricante</label>
                                <select name="providerIdProduct" id="productProviderId" required>
                                    <option value=""></option>
                                    <c:forEach items="${providerList}" var="provider">
                                        <option value="${provider.id}" id="providerId">${provider.name} - ${provider.cnpj}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div>
                                <label for="imageProduct">Imagem</label>
                                <div class="file-field input-field">
                                    <div class="btn">
                                        <span><i class="material-icons center">file_upload</i></span>
                                        <input type="file" id="imageProduct" name="productImage" required>
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text" placeholder="Upload imagem">
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Voltar</a>
                                <button class=" modal-action  green btn-flat" id="addButton" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="modal-update" class="modal">
                <div class="modal-content">
                    <h4>Editar Produto<i class=" small material-icons" style="margin-left: 10px;">edit</i></h4>
                    <div class="row">
                        <form class="col s12" method="POST" action="/ezmartWeb/productEdit">
                            <div>
                                <label for="nome">Nome</label>
                                <input type="text" style="display: none"  name="productIdEdit" id = "idProduct">
                                <input id="nameProductEdit" name="productNameEdit" type="text" class="validate" required>
                            </div>
                            <div>
                                <label for="codBarras">Código de Barras</label>
                                <input id="barCodeProductEdit" name="productBarCodeEdit" maxlength="13" pattern="[0-9]+$" title="Somente números!" type="text" class="validate" required>
                            </div>
                            <div>
                                <label for="marca">Marca</label>
                                <input id="brandProductEdit" name="productBrandEdit" type="text" class="validate">
                            </div>
                            <div>
                                <label for="sector">Linha</label>
                                <select name="sectorIdProductEdit" id="sectorIdProductEdit" required>
                                    <option notselected></option>
                                    <c:forEach items="${sectorList}" var="sector">
                                        <option 
                                            <%--<c:if test="${productList.sector.id eq sector.id}">selected="true"</c:if>--%>
                                            value="${sector.id}" id="sectorIdProductEdit">${sector.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <label for="provider">Fabricante</label>
                                <select name="providerIdProductEdit" id="providerIdProductEdit" required>
                                    <option notselected></option>
                                    <c:forEach items="${providerList}" var="provider">
                                        <option 
                                            <%--<c:if test="${productList.provider.id eq provider.id}">selected="true"</c:if>--%>
                                            value="${provider.id}">${provider.name} - ${provider.cnpj}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Voltar</a>
                                <button class=" modal-action green btn-flat" type="submit" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="modal-delete" class="modal">
                <div class="modal-content">
                    <h4>Excluir Produto<i class=" small material-icons" style="margin-left: 10px;">delete</i></h4>
                    <p><font size="5">Tem certeza que deseja excluir o produto: </font><font color="red" size="5"><container id = "product-id"></container></font> <font size="5">?</font></p>
                    <div class="modal-footer">
                        <form method="POST" action="/ezmartWeb/productDelete">
                            <input type="text" style="display: none"  name="productId" id = "idProductDelete">
                            <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Não</a>
                            <button class="btn btn-sm btn-default btn-small green" type="submit" style="color:white; margin-right: 10px;" >Sim</button>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                            <a href="<c:url value=""/>" class="breadcrumb">Produtos</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                <h4>Produtos</h4>
                <a href="#modal-create" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>Adicionar</a>
                <br><br/>
                <div class="row">
                    <div class="col s12 m12">
                        <ul>
                            <c:forEach items="${productList}" var="product">
                                <li class="col l4 m6 s12" >
                                    <div class="card-panel medium" id="scroll-user-list">
                                        <div class="row" style="padding: 20px;">
                                            <div>
                                                <img class="responsive-img-center" src="<c:url value = "/resources/img/product/${product.id}.jpg"/>" alt="${product.barCode} - ${product.name}" width="150">
                                                <form method="post" enctype="multipart/form-data" action="/ezmartWeb/product/uploadImage">
                                                    <div class="file-field input-field">
                                                        <div class="btn">
                                                            <span><i class="material-icons center">file_upload</i></span>
                                                            <input type="file" id="productImage" name="productImage">
                                                            <input value="${product.id}" name="idProductForImage" style="display: none">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" placeholder="Alterar imagem">
                                                        </div>
                                                    </div>
                                                    <container>
                                                        <button id="btnUpload" type="submit" class="btn btn-success btn-small"><i class="material-icons left">save</i>SALVAR</button>
                                                    </container>
                                                </form>
                                            </div>
                                            <br/>
                                            <p style="font-size: 18px"><b>Id:</b> ${product.id}</p>
                                            <p style="font-size: 18px"><b>Nome:</b> ${product.name}</p>
                                            <p style="font-size: 18px"><b>Código EAN:</b> ${product.barCode}</p>
                                            <p style="font-size: 18px"><b>Marca:</b> ${product.brand}</p>
                                            <p style="font-size: 18px"><b>Linha:</b> ${product.sector.name}</p>
                                            <p style="font-size: 18px"><b>Fabricante:</b> ${product.provider.name}</p>
                                            <div class="card-action card-content">
                                                <div class="col s12 center">
                                                    <a class="btn btn-sm btn-default btn-small ffc400 amber accent-3 modal-trigger" href="#modal-update" style="color:white;" onclick="setProductEdit(${product.id}, '${product.name}', '${product.barCode}', '${product.brand}')"><i class="material-icons center">edit</i></a>
                                                    <a class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadosModalProduct(${product.id}, '${product.name}')" style="color:white;"><i class="material-icons center">delete</i></a>
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
        </c:if>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
