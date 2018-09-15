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
                            <form class="col s12" method="POST">
                                <!--                                <div class="input-field col s12">
                                                                    <label for="imagem">Imagem</label>
                                                                    <input id="productImage" name="ImageProduct" type="text" class="validate">
                                                                </div>-->
                                <div class="input-field col s12">
                                    <label for="nome">Nome</label>
                                    <input id="productName" name="nameProduct" type="text" class="validate">
                                </div>
                                <div class="input-field col s12">
                                    <label for="codBarras">Código de Barras</label>
                                    <input id="productBarCode" name="barCode" maxlength="13" type="text" class="validate">
                                </div>
                                <div class="input-field col s12">
                                    <label for="marca">Marca</label>
                                    <input id="productBrand" name="brandProduct" type="text" class="validate">
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
                                <div class="input-field col s12">
                                    <label for="id">Id</label>
                                    <input id="idProduct" name="idProductEdit" type="text" class="validate">
                                </div>
                                <div class="input-field col s12">
                                    <label for="nome">Nome</label>
                                    <input type="text" style="display: none"  name="productId" id = "idProduct">
                                    <input id="nameProductEdit" name="productNameEdit" type="text" class="validate">
                                </div>
                                <div class="input-field col s12">
                                    <label for="codBarras">Código de Barras</label>
                                    <input id="barCodeProductEdit" name="productBarCodeEdit" maxlength="13" type="text" class="validate">
                                </div>
                                <div class="input-field col s12">
                                    <label for="marca">Marca</label>
                                    <input id="brandProductEdit" name="productBrandEdit" type="text" class="validate">
                                </div>
                                <div class="modal-footer">
                                    <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Voltar</a>
                                    <button class=" modal-action modal-close green btn-flat" type="submit" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
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
            <div>
                <div class="row">
                    <div class="col s12">
                        <div class="card">
                            <div class="card-content">
                                <h3>Produtos</h3>
                                <table class="table">
                                    <tr>
                                        <th>Id</th>
                                        <th>Imagem</th>
                                        <th>Nome</th>
                                        <th>Código de Barras</th>
                                        <th>Marca</th>
                                        <th>Id da Linha</th>
                                        <th>Id do Fornecedor</th>
                                    <hr>
                                    <th></th>
                                    </tr>
                                    <a href="#modal-create" class="btn-floating btn-n-floatingsmall green left modal-trigger" style="color:white;"><i class="material-icons center">add</i></a>
                                    <br><br/>
                                    <br><br/>
                                    <c:forEach items="${productList}" var="product">
                                        <tr>
                                            <td>${product.id}</td>
                                            <td>${product.image}</td>
<!--                                                <form method="post" enctype="multipart/form-data" action="/ezmartWeb/product/uploadImage">
                                                    <div class="file-field input-field">
                                                        <div class="btn">
                                                            <span><i class="material-icons center">file_upload</i></span>
                                                            <input type="file" id="productImage" name="productImage">
                                                            <input value="${product.id}" name="idProductForImage" style="display: none">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" placeholder="Upload file">
                                                        </div>
                                                    </div>
                                                    <container>
                                                        <button id="btnUpload" type="submit" class="btn btn-success btn-small"><i class="material-icons left">save</i>Salvar</button>
                                                    </container>
                                                </form>-->
                                            <td>${product.name}</td>
                                            <td>${product.barCode}</td>
                                            <td>${product.brand}</td>
                                            <td>${product.sector.id}</td>
                                            <td>${product.provider.id}</td>
                                            <td>
                                                <a class="btn btn-sm btn-default btn-small ffc400 amber accent-3 modal-trigger" href="#modal-update" style="color:white;" onclick="setProductEdit(${product.id}, '${product.name}', '${product.barCode}', '${product.brand}')"><i class="material-icons center">edit</i></a>
                                                <a class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadosModalProduct(${product.id}, '${product.name}')" style="color:white;"><i class="material-icons center">delete</i></a>
                                            </td>
                                        </tr>    
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   
        </c:if>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
