<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
    <body>       
        <c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
            <div>
                <nav>
                    <div class="nav-menu">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                        <a href="<c:url value="/login"/>" class="breadcrumb">Autenticação</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="container"> 
            <div class="row">
                <div class="col s5">
                    <div id="loginbox" style="margin-top:50px" class="mainbox">                    
                        <div class="panel panel-info" style="padding: 0">
                            <div class="panel-heading">
                                <div class="panel-title col s5" style="padding: 0">
                                    EZMart | Login 
                                </div>
                                <div style="float:right; font-size: 80%; padding-right: 0" class="col s5">
                                    <a href="<c:url value="/recoveryPassword"/>">Esqueceu a senha?</a>
                                </div>
                            </div>     
                            <div style="padding-top:20px" class="panel-body" >
                                <form class="form-horizontal" method="POST">
                                    <div>
                                        <div style="margin-bottom: 25px" class="input-group">
                                            <input id="email" type="text" class="form-control" name="email" placeholder="Email">
                                            <span style="color: orangered">${errors.email}</span>
                                        </div>
                                        <div style="margin-bottom: 25px" class="input-group">
                                            <input id="senha" type="password" class="form-control" name="password" placeholder="Senha">
                                            <span style="color: orangered">${errors.password}</span>
                                            <span style="color: orangered">${errors.information}</span>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <button id="btn-btn-ezmart-style" type="submit" class="btn" value="confirmar"><i class="material-icons left">subdirectory_arrow_right</i>Entrar</button>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                </form>   
                                <div style="border-top: 3px solid#888; padding-top:15px; font-size:85%">Não tem uma conta? 
                                    <a href="<c:url value="/register"/>">Crie uma agora.</a>
                                </div>
                            </div>                     
                        </div>  
                    </div>
                </div>
                <div class="col s5 offset-s2" style="margin-top: 40px">
                    <img class="responsive-img" src="<c:url value = "/resources/img/autentication/carrinho.png"/>">
                </div>
            </div>
        </div>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>            
    </body>
</html>
