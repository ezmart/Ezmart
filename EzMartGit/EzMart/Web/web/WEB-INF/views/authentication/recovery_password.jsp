<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
        <body> 
        <c:if test="${empty  userLogged}">
            <c:import url="/WEB-INF/views/templates/header_logout.jsp"></c:import>
                <div>
                    <nav>
                        <div class="nav-wrapper">
                            <div style="margin-left: 40px" class="col s12">
                                <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                            <a href="<c:url value="/login"/>" class="breadcrumb">login</a>
                            <a href="<c:url value="/recoveryPassword"/>" class="breadcrumb">Recuperar Senha</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container" >
                <div class="row">
                    <h3 style="color: #2196f3">Recuperar Senha</h3>                
                    <br/>
                    <div class="col s5">
                        <h2 style="color: #898989; font-size: x-large">Preencha os campos abaixo para recuperar sua senha!</h2>
                        <div class="card">
                            <div class="card-content">
                                <form method="POST">
                                    <div class=" form-group">
                                        <label for="email">Insira seu email:</label>
                                        <input id="email" type="email" class="form-control " name="email" placeholder="${errors.email}">
                                        <span style="color: orangered">${errors.email}</span>
                                    </div>
                                    <div>
                                        <button id="btn-btn-ezmart-style" type="submit" class="btn btn-success"><i class="material-icons left">vpn_key</i>Comfirmar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col s4 offset-s2">
                        <img class="responsive-img" src="<c:url value = "/resources/img/authentication/carrinho.png"/>">
                    </div>
                </div>
            </div> 
        </c:if>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>   
    </body>
</html>