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
            <c:import url="/WEB-INF/views/templates/header_consumer.jsp"></c:import>

                <div id="modal-create" class="modal">
                    <div class="modal-content">
                        <form method="POST">
                            <h4>CHUPETA</h4>
                            <input type="text" style="display: none" name="type" id="type" value="CREATE">
                            <input type="text" style="display: none"  name="value" id="idCreate" value="">
                            <button id="btn-btn-ezmart-style" type="submit" class="btn modal-close" value="confirmar">Adicionar</button>
                        </form>
                    </div>
                </div>
                <div id="modal-update" class="modal">
                    <div class="modal-content">
                        <h4>Modal Header</h4>

                    </div>
                </div>
                <div id="modal-delete" class="modal">
                    <div class="modal-content">
                        <h4>Tem certeza que deseja excluir a linha  ?</h4>
                        <div class="modal-footer">
                            <form method="POST">
                                <input type="text" style="display: none" name="type" id="type" value="DELETE">
                                <input type="text" style="display: none"  name="value" id="idDelete" value="">
                                <button id="btn-btn-ezmart-style" type="submit" class="btn modal-close" value="confirmar">Sim</button>
                            </form>
                            <a class="btn btn-sm btn-default btn-small red modal-close modal-sction">Não</a>
                        </div>
                    </div>
                </div>
                <div>
                    <nav>
                        <div class="nav-wrapper">
                            <div style="margin-left: 40px" class="col s12">
                                <a href="<c:url value="/home"/>" class="breadcrumb">inicio</a>
                            <a href="<c:url value="/login"/>" class="breadcrumb">Meus Produtos</a>
                        </div>
                    </div>
                </nav>
            </div>
            <c:if test="${empty productsList}">
                <div class="card">
                    <div class="row center-align" style="padding: 20px;">
                        <h4 class="center-align" style="color: #2196f3">Nenhuma produto encontrado! :)</h4>
                        <br/>
                        <br/>
                        <a href="<c:url value="/new/product/${list.id}"/>" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>Adicionar Novo Produto</a>
                        <!--<a href="#modal-create" id="btn-btn-ezmart-style" class="btn btn-sm btn-default btn-small modal-trigger">Adicionar nova lista de compras</a>-->
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty productsList}">
                <div class="container">
                    <!--<div class="card">-->
                    <!--<div class="card-content">-->
                    <h3>Produtos cadastrados na lista</h3>

                    <div class="row">
                        <div class="col s12 m12">
                            <ul>
                                <c:forEach items="${productsList}" var="productsList">
                                    <li class="col l4 m6 s12" >
                                        <div class="card" style="align-items: center">
                                            <div class="row" style="padding: 20px;">
                                                <div>
                                                    <img class="responsive-img" src="<c:url value = "/resources/img/product/${productsList.id}.png"/>" alt="foto" height="">
                                                    <!--<img class="responsive-img" style="align-items: center" src="data:image/jpg;base64, ${produto.foto}" alt="foto" height="150" />-->
                                                </div>
                                                <br/>
                                                <h6 class="col s6"> Nome do Produto:</h6>
                                                <h4 class="center" style="font-size: 20px">${productsList.productName}</h4>
                                                <span class="col s6"> Quantidade:</span>
                                                <h4 class="center" style="font-size: 20px">${productsList.productName}</h4>
                                                <div class="card-action card-content">
                                                    <div class="col s12">
                                                        <a class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadaModalList(${product.id})" style="color:white;">Excluir</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <table class="table">
                        <tr>
                            <th>Foto</th>
                            <th>Id</th>
                            <th>Nome</th>
                        </tr>
                        <!--<a href="<c:url value="/products/${listId}/list"/>" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>Adicionar Novo Produto</a>-->
                        <a class="btn btn-sm btn-danger btn-small green modal-trigger" href="#modal-create" onclick="setDadaModalProductList(${listId})" style="color:white;">ADD</a>

                        <!--<a href="#modal-create" id="btn-btn-ezmart-style" class="btn modal-trigger">Adicionar</a>-->
                        <br/>
                        <br/>
                        <c:forEach items="${productsList}" var="productList">
                            <tr>
                                <td><img class="responsive-img" width="25px" height="25px" src="<c:url value = "/resources/img/product/${productList.id}.png"/>"></td>
                                <td>${productList.id}</td>
                                <td>${productList.productName}</td>
                                <td>
                                    <!--<a class="btn btn-sm btn-default btn-small yellow modal-trigger" href="#modal-update" style="color:white;">Gerênciar Lista</a>-->
                                    <!--<a class="btn yellow" href="<c:url value="/products/${productList.id}/list"/>" style="color:white;">Gerenciar Lista</a>-->
                                    <a class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadaModalList(${product.id})" style="color:white;">Excluir</a>
                                </td>
                            </tr>    
                        </c:forEach>
                    </table>
                    <!--</div>-->
                    <!--</div>-->
                </div>
            </c:if>
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
