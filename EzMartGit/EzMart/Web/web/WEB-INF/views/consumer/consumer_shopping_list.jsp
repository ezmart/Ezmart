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
                        <h4>Insira o nome da nova lista</h4>
                    <c:import url="/WEB-INF/views/consumer/modal/create_shopping_list.jsp"></c:import>
                    </div>
                </div>
                <div id="modal-update" class="modal">
                    <div class="modal-content">
                        <h4>Altere o nome da lista</h4>
                    <c:import url="/WEB-INF/views/consumer/modal/update_shopping_list.jsp"></c:import>
                    </div>
                </div>
                <div id="modal-delete" class="modal">
                    <div class="modal-content">
                        <h4>Tem certeza que deseja excluir a linha  ?</h4>
                        <div class="modal-footer">
                            <form method="POST">
                                <input type="text" style="display: none" name="type" id="type" value="DELETE">
                                <input type="text" style="display: none"  name="value" id="idDeleteShoppingList" value="">
                                <a class="btn btn-sm right btn-default btn-small red modal-close modal-action" style="color:white; margin-left: 10px;">Não</a>
                                <button id="delete-confimation-list" type="submit" class="btn btn-sm btn-default btn-small green" value="confirmar" style="color:white;">Sim</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="modal-select-search" class="modal" style="background-color: #F1F1F1">
                    <div class="modal-content">
                        <h4 class="center-align">Selecione o tipo de pesquisa</h4>
                        <br/>
                        <br/>
                        <br/>
                        <form method="POST">
                            <input type="text" style="display: none"  name="value" id="idShoppingList" value="">
                            <ul class="menu-profile center">                                               
                                <li>
                                    <div class="col s12">   
                                        <a class="right btn btn-sm btn-default btn-small red modal-close modal-action" id="" style="color:white; margin-left: 10px;">Fechar</a>
                                    </div>
                                </li>
                                <li>
                                    <div class="col s12" style="margin-top: 20px"> 
                                        <button type="submit" class="center btn btn-sm btn-default btn-small green" id="" value="confirmar" style="color:white; margin-left: 10px;">Mercado mais barato</button>
                                    </div>
                                </li>
                                <li>
                                    <div class="col s12" style="margin-top: 20px">   
                                        <button type="submit" class="left btn btn-sm btn-default btn-small yellow" id="" value="confirmar" style="color:white; margin-left: 10px;">Preços mais baratos</button>
                                    </div>
                                </li>
                                <li>
                                    <div class="col s12" style="margin-top: 20px">   
                                        <button type="submit" class="left btn btn-sm btn-default btn-small blue" id="" value="confirmar" style="color:white; margin-left: 10px;">Preços mais baratos proximos de mim</button>
                                    </div>
                                </li>
                                <li>
                                    <div class="col s12" style="margin-top: 20px">   
                                        <button type="submit" class="left btn btn-sm btn-default btn-small blue-grey" id="" value="confirmar" style="color:white; margin-left: 10px;">Mercado mais proximo</button>
                                    </div>
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
                <div>
                    <nav>
                        <div class="nav-wrapper">
                            <div style="margin-left: 40px" class="col s12">
                                <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                            <a href="<c:url value="/shoppingList"/>" class="breadcrumb">Minha listas</a>
                        </div>
                    </div>
                </nav>
            </div>
            <c:if test="${empty shoppingList}">
                <div class="card">
                    <div class="row center-align" style="padding: 20px;">
                        <h4 id="text-null-list" class="center-align" style="color: #2196f3">Nenhuma lista encontrada! :)</h4>
                        <br/>
                        <br/>
                        <a href="#modal-create" id="add-list" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>Adicionar</a>
                        <!--<a href="#modal-create" id="btn-btn-ezmart-style" class="btn btn-sm btn-default btn-small modal-trigger">Adicionar nova lista de compras</a>-->
                    </div>
                </div>
                <br/>
                <br/>
            </c:if>
            <c:if test="${not empty shoppingList}">
                <div class="container">
                    <div class="card">
                        <div class="card-content" style="background-color: #F1F1F1">
                            <div class="row">
                                <div class="col s4">
                                    <a href="#modal-create" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>Adicionar</a>
                                </div>
                                <div class="col s8" style="margin-top: 0">
                                    <p id="text-all-list" style="font-size: xx-large">Listas cadastradas</p>
                                </div>
                            </div>
                            <table class="table s12">
                                <!--<a href="#modal-create" id="btn-btn-ezmart-style" class="btn modal-trigger">Adicionar</a>-->
                                <br/>
                                <br/>
                                <c:forEach items="${shoppingList}" var="shoppingList">
                                    <tr>
                                        <td id="shopping-list-name" style="font-weight: bold;">${shoppingList.name}</td>
                                        <td>
                                            <a id="delete-list" class="right btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDeleteDadaModalShoppingList(${shoppingList.id})" style="color:white; margin-left: 15px"><i class="material-icons right">delete</i>Excluir</a>
                                            <a id="update-new-list" class="right btn btn-sm btn-default btn-small ffc400 amber accent-3 modal-trigger" href="#modal-update" onclick="setUpdateDadaModalShoppingList(${shoppingList.id})" style="color:black; margin-left: 15px"><i class="material-icons right">edit</i>Alterar</a>
                                            <a id="products-list" class="right btn btn-sm btn-default btn-small blue-grey" href="<c:url value="/products-${shoppingList.id}"/>" style="color:white; margin-left: 15px"><i class="material-icons right">add_shopping_cart</i>Produtos</a>
                                            <a id="compare-list" class="right btn btn-sm btn-default btn-small f80113 modal-trigger" href="<c:url value="/comparePrices-${shoppingList.id}"/>" style="color:white; margin-left: 15px"><i class="material-icons right">attach_money</i>Comparar</a>
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
