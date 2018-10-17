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
                    <h4>Por qual produto deseja pesquisar ?</h4>
                    <input type="text" name="searchProduct" id="searchProduct" value="${searchProduct}">
                    <input type="text" style="display: none" name="listId" id="listId" value="${listId}">
                    <a class="btn waves-yellow"  onclick="setDadaModalProductList()"style="color:white;">Pesquisar</a>
                    <c:if test="${not empty userLogged}">
                    <div class="card">
                        <div class="card-content" style="background-color: #F1F1F1">
                            <div id="content_insert">

                            </div>
                        </div>
                    </div>
                    </c:if>
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
                            <input type="text" style="display: none"  name="value" id="idDeleteProductList" value="">
                            <a class="btn btn-sm right btn-default btn-small red modal-close modal-action" style="color:white; margin-left: 10px;">Não</a>
                            <button class="btn btn-sm btn-default btn-small green" type="submit" style="color:white;" >Sim</button>
                            <!--<button id="btn-btn-ezmart-style" type="submit" class="btn modal-close" value="confirmar">Sim</button>-->
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                            <a href="<c:url value="/shoppingList"/>" class="breadcrumb">Minhas listas</a>
                            <a href="<c:url value="#!"/>" class="breadcrumb">Meus produtos</a>
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
                        <a class="btn btn-sm btn-danger btn-small green modal-trigger" href="#modal-create" style="color:white;"><i class="material-icons right">add_box</i>Adicionar</a>
                        <!--<a href="#modal-create" id="btn-btn-ezmart-style" class="btn btn-sm btn-default btn-small modal-trigger">Adicionar nova lista de compras</a>-->
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty productsList}">
                <div class="container">
                    <div class="card">
                        <div class="card-content" style="background-color: #F1F1F1">
                            <div class="row left">
                                <div class="col s12" style="margin-top: 0">
                                    <h3>Produtos cadastrados na lista</h3>
                                </div>
                            </div>
                            <table class="table s12">
                                <tr>
                                    <th></th>
                                    <!--<th>Id</th>-->
                                    <th class="center">Nome do produto</th>
                                </tr>
                                <div class="col s6 right">
                                    <a class="btn btn-sm btn-danger btn-small green modal-trigger" href="#modal-create" style="color:white;"><i class="material-icons right">add_box</i>Adicionar</a>
                                </div>
                                <!--<a href="#modal-create" id="btn-btn-ezmart-style" class="btn modal-trigger">Adicionar</a>-->
                                <br/>
                                <br/>
                                <c:forEach items="${productsList}" var="productList">
                                    <tr>
                                        <td class="center"><img class="responsive-img" width="60px" height="60px" src="<c:url value = "/resources/img/product/${productList.productId}.jpg"/>"></td>
                                        <!--<td>${productList.id}</td>-->
                                        <td class="center">${productList.productName}</td>
                                        <td>
                                            <!--<a class="btn btn-sm btn-default btn-small yellow modal-trigger" href="#modal-update" style="color:white;">Gerênciar Lista</a>-->
                                            <!--<a class="btn yellow" href="<c:url value="/products/${productList.id}/list"/>" style="color:white;">Gerenciar Lista</a>-->
                                            <a class="center btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDeleteDadaModalProductInList(${productList.productId})" style="color:white; margin-left: 15px"><i class="material-icons right">delete</i>Excluir</a>
                                        </td>
                                    </tr>    
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
