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
                                <a href="<c:url value="/home"/>" class="breadcrumb">inicio</a>
                            <a href="<c:url value="/login"/>" class="breadcrumb">Meus Produtos</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                <br/>
                <div style="margin-top:10px">
                    <div>
                        <a id="btn-login" href="<c:url value="/produto/novo"/>" class="btn btn-success" style="background-color: #01579b"><i class="material-icons left">add_circle_outline</i>Novo produto</a>
                    </div>
                </div>
                <br/>
                <div class="row">   
                    <c:if test="${empty produtoList}">
                        <div class="card">
                            <div class="row" style="padding: 20px;">
                                <h4 class="center-align" style="color: #2196f3">Nenhum produto encontrado! :)</h4>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty produtoList}">
                        <div class="row">
                            <h4 class="center-align" style="color: #2196f3">Meus Produtos</h4>
                            <hr>
                        </div>
                        <div class="row">
                            <div class="col s12 m12">
                                <ul>
                                    <c:forEach items="${productList}" var="product">
                                        <li class="col l4 m6 s12">
                                            <div class="card">
                                                <div class="card-image img-responsive">
                                                    <img src="<c:url value="/anuncio/${product.img}/img.jpg"/>">
                                                </div>
                                                <div class="row" style="padding: 20px;">
                                                    <div>
                                                        <img class="responsive-img" style="align-items: center" src="data:image/jpg;base64, ${produto.foto}" alt="foto" height="150" />
                                                    </div>
                                                    <br/>
                                                    <h6 class="col s6"> Nome do Produto:</h6>
                                                    <h4 class="center" style="font-size: 20px">${produto.nomeProduto}</h4>
                                                    <h6 class="col s6"> Preço:</h6>
                                                    <h4 class="center" style="font-size: 20px">R$ ${produto.preco}</h4>
                                                    <span class="col s6"> Quantidade:</span>
                                                    <h4 class="center" style="font-size: 20px">${produto.qtdDisponivel}</h4>
                                                    <span class="col s6"> Categoria:</span>
                                                    <h4 class="center" style="font-size: 20px">${produto.categoriaProduto.nomeCategoria}</h4>
                                                    <span class="col s6"> Tipo Animal:</span>
                                                    <h4 class="center" style="font-size: 20px">${produto.tipoAnimal.tipo}</h4>
                                                    <div class="card-action card-content">
                                                        <div class="col s6">
                                                            <a href="<c:url value="/product/${product.id}/update"/>" class="btn blue"><i class="small material-icons">input</i></a>                             
                                                        </div>
                                                        <div class="col s6">
                                                            <a href="<c:url value="/product/${product.id}/delete"/>"  class="btn modal-trigger red"><i class="small material-icons">delete</i></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <!--                    <ul class="pagination center" >
                        <c:if test="${(offset - limit)>=0}">
                            <li class="waves-effect"><a href="<c:url value="/meuperfil?limit=${limit}&offset=${offset-limit}"/>"><i class="material-icons">chevron_left</i></a></li>
                        </c:if>
                        <c:if test="${(limit+offset)< count}">
                        <li class="waves-effect"><a href="<c:url value="/meuperfil?limit=${limit}&offset=${offset+limit}"/>"><i class="material-icons">chevron_right</i></a></li>
                        </c:if>
                </ul>-->
                    </c:if>
                </div>
            </div>  
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
