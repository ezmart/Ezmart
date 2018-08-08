<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
        <c:if test="${not empty  userLogged}">
            <c:if test="${userLogged.userType eq 'consumer'}">
                <c:import url="/WEB-INF/views/templates/header_consumer.jsp"></c:import>
            </c:if>
            <c:if test="${userLogged.userType eq 'emporium'}">
                <c:import url="/WEB-INF/views/templates/header_emporium.jsp"></c:import>
            </c:if>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">Home</a> 
                            <a href="<c:url value="/changePassword"/>" class="breadcrumb">Alterar Senha</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container" >
                <div class="row">
                    <h3 style="color: #2196f3">Alterar Senha</h3>                
                    <br/>
                    <div class="col s5">
                        <h2 style="color: #898989; font-size: x-large">Preencha os campos abaixo para alterar sua senha!</h2>
                        <div class="card">
                            <div class="card-content">
                                <form method="POST">
                                    <div class=" form-group">
                                        <label for="currentPassword">Senha Atual:</label>
                                        <input id="currentPassword" type="password" class="form-control " name="currentPassword" placeholder="${errors.currentPassword}">
                                        <span style="color: orangered">${errors.currentPassword}</span>
                                    </div>
                                    <div class="form-group">
                                        <label for="newPassword">Nova Senha:</label>
                                        <input id="newPassword" type="password" class="form-control " name="newPassword" placeholder="${errors.newPassword}" >
                                    </div>
                                    <div class="form-group">
                                        <label for="confirmNewPassword">Confirme a nova Senha:</label>
                                        <input id="confirmNewPassword" type="password" class="form-control " name="confirmNewPassword" placeholder="${errors.confirmNewPassword}" >
                                        <span style="color: orangered">${errors.confirmNewPassword}</span>
                                    </div>
                                    <div>
                                        <button id="btn-login" type="submit" class="btn btn-success" style="background-color: #01579b"><i class="material-icons left">vpn_key</i>Salvar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col s4 offset-s2">
                        <img class="responsive-img" src="<c:url value = "/resources/img/autentication/carrinho.png"/>">
                    </div>
                </div>
            </div> 
        </c:if>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>   
    </body>
</html>