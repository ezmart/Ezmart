<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
    <body>  
        <c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">inicio</a>
                        <a href="<c:url value="/login"/>" class="breadcrumb">autenticação</a>
                        <a href="<c:url value="/cadastro"/>" class="breadcrumb">cadastro</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="container">   
            <div class="row">
                <h3 style="color: #2196f3">Torne sua experiência por compras, a mais incrível possível!</h3>
                <div class="col s5">                        
                    <div style="margin-top:40px; border-top: 3px solid#888" class="mainbox">                    
                        <div class="panel panel-info">
                            <br/>
                            <div class="panel-title" style="color: #9e9e9e">
                                Ainda não tem conta? Cadastre-se agora!
                            </div>                        
                            <div style="padding-top:40px" class="panel-body">
                                <form id="loginform" class="form-horizontal">
                                    <div style="margin-top:10px" class="form-group">
                                        <label class="panel-info"  style="font-size: x-large; color: #9e9e9e">Cadastro Para Consumidor</label>
                                        <div class="col s12 controls">                                                    
                                            <a class="waves-effect waves-teal btn" id="cadpf" style="background-color: #01579b" href="<c:url value="/register/consumer"/> "><i class="material-icons left">account_box</i>Cadastrar</a>
                                        </div>
                                    </div> 
                                    <br/>
                                    <br/>
                                    <div style="margin-top:10px" class="form-group">
                                        <label class="panel-info" style="font-size: x-large ; color: #9e9e9e">Cadastro Para Estabelecimento</label>
                                        <div class="col s12">
                                            <a class="waves-effect waves-light btn" id="cadpj" style="background-color: #01579b" href="<c:url value="/register/emporium"/> "><i class="material-icons left">account_box</i>Cadastrar</a>
                                        </div>
                                    </div> 
                                        <div style="margin-top:60px; border-top: 3px solid#888"></div>
                                </form>     
                            </div>                     
                        </div>  
                    </div>
                </div>
                <div class="col s5 offset-s2">
                    <img class="responsive-img" src="<c:url value = "/resources/img/register/carrinho_registro.png"/>">
                </div>
            </div>
        </div>                                  
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
    </body>
</html>
