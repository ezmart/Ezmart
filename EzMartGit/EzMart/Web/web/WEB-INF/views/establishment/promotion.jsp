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
                            <a href="<c:url value=""/>" class="breadcrumb">Minhas promoções</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div id="modal-create" class="modal">
                <div class="modal-content">
                    <h4>Criar promoção<i class=" small material-icons" style="margin-left: 10px;">add_box</i></h4>
                    <div class="row">
                        <form class="col s12" method="POST">
                            <div>
                                <label for="nome">Nome</label>
                                <input id="promotionName" placeholder="Digite aqui..." name="namePromotion" type="text" class="validate" required>
                                <label for="startDate">Início da promoção</label>
                                <input type="date" name="promotionStart" required>
                                <label for="finalDate">Término da promoção</label>
                                <input type="date" name="promotionFinal" required>
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
                <h4>Minhas promoções</h4>
                <a href="#modal-create" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>CRIAR PROMOÇÃO</a>
                <br><br/>
                <c:if test="${empty promotionList}">
                    <div class="row" style="padding: 20px;">
                        <h4 class="center-align" style="color: #2196f3">Nenhuma promoção cadastrada!</h4>
                    </div>
                </c:if>
                <c:if test="${not empty promotionList}">

                        <div class="row">
                            <div class="col s12 ">
                                <table class="table">
                                    <tr>
                                        <th>Nome</th>
                                        <th>Data inicial</th>
                                        <th>Data final</th>
                                    <hr>
                                    <th></th>
                                    </tr>
                                    <c:forEach items="${promotionList}" var="promotionList">
                                        <tr>
                                            <td>${promotionList.promotion.name}</td>
                                            <td>${promotionList.promotion.startDateConvert}</td>
                                            <td>${promotionList.promotion.finalDateConvert}</td>
                                            <td>
                                                <a class="btn btn-default btn-small green modal-trigger" href="promotion-product?establishmentId=${promotionList.establishmentProduct.establishment.id}&promotionId=${promotionList.promotion.id}&isVisualizar=false" style="color:white;"><i class="material-icons right">add_shopping_cart</i>ADICIONAR PRODUTOS</a>
                                            </td>
                                            <td>
                                                <a class="btn btn-default btn-small ffc400 amber accent-3 modal-trigger" href="promotion-product?establishmentId=${promotionList.establishmentProduct.establishment.id}&promotionId=${promotionList.promotion.id}&isVisualizar=true" style="color:white;"><i class="material-icons right">remove_red_eye</i>VISUALIZAR PRODUTOS</a>
                                            </td>
                                        </tr>    
                                    </c:forEach>
                                </table>
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
            </div>  
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
