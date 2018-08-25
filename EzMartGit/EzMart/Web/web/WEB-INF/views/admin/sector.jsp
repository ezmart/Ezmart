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
                        <h4>Modal Header</h4>
                        <p>A bunch of text</p>
                    </div>
                </div>
                <div id="modal-update" class="modal">
                    <div class="modal-content">
                        <h4>Modal Header</h4>
                        <p>A bunch of text</p>
                    </div>
                </div>
                <div id="modal-delete" class="modal">
                    <div class="modal-content">
                        <h5>Tem certeza que deseja excluir a linha <container id = "sector-id"></container> ?</h5>
                        <div class="modal-footer">
                            <a class="btn btn-sm btn-default btn-small red modal-close modal-sction" style="color:white;">Não</a>
                            <a class="btn btn-sm btn-default btn-small green" style="color:white; margin-right: 10px;" >Sim</a>
                        </div>
                    </div>
                </div>
                <div>
                    <nav>
                        <div class="nav-wrapper">
                            <div style="margin-left: 40px" class="col s12">
                                <a href="<c:url value="/home"/>" class="breadcrumb">inicio</a>
                            <a href="<c:url value="#"/>" class="breadcrumb">Linhas</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                <div class="col s12">
                    <div class="card">
                        <div class="card-content">
                            <h3>Linhas cadastradas</h3>
                            <table class="table">
                                <tr>
                                    <th>Id</th>
                                    <th>Linha</th>
                                <hr>
                                <th></th>
                                </tr>
                                <a href="#modal-create" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;">Adicionar</a>
                                <br><br/>
                                <br><br/>
                                <c:forEach items="${sectorList}" var="sector">
                                    <tr>
                                        <td>${sector.id}</td>
                                        <td>${sector.name}</td>
                                        <td>
                                            <a class="btn btn-sm btn-default btn-small yellow modal-trigger" href="#modal-update" style="color:white;">Alterar</a>
                                            <a class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadosModal(${sector.id}, '${sector.name}')" style="color:white;">Excluir</a>
                                            <hr> 
                                        </td>
                                    </tr>    
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>   
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
        <script src="<c:url value="/resources/js/initializeModal.js"/>"></script>
    </body>
</html>
