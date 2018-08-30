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
                        <h4>Adicionar Linha<i class=" small material-icons" style="margin-left: 10px;">add_box</i></h4>
                        <div class="row">
                            <form class="col s12" method="POST">
                                <div class="input-field col s12">
                                    <input id="sectorName" name="nameSector" type="text" class="validate">
                                    <input type="text" style="display: none" class="form-control input-text-register" name="type" id="type" value="CREATE">
                                    <label for="nome">Nome</label>
                                </div>
                                <div class="modal-footer">
                                    <button class=" modal-action modal-close green btn-flat" type="submit" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                                    <!--<button id="btn-btn-ezmart-style" type="submit" class="btn" value="confirmar"><i class="material-icons left">border_color</i>CONFIRMAR</button>-->
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="modal-update" class="modal">
                    <div class="modal-content">
                        <h4>Editar Linha<i class=" small material-icons" style="margin-left: 10px;">edit</i></h4>
                        <div class="row">
                            <form class="col s12">
                                <div class="input-field col s12">
                                    <input required name="sectorNameEdit" type="text" value="">
                                    <label for="nome">Nome</label>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <a class=" modal-action modal-close green btn-flat" style="margin-right: 15px; color:white;">CONFIRMAR</a>
                        </div>
                    </div>
                </div>
                <div id="modal-delete" class="modal">
                    <div class="modal-content">
                        <h4>Excluir Linha<i class=" small material-icons" style="margin-left: 10px;">delete</i></h4>
                        <p><font size="5">Tem certeza que deseja excluir a linha</font><font color="red" size="5"><container id = "sector-id"></container></font> <font size="5">?</font></p>
                        <div class="modal-footer">
                            <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Não</a>
                            <a class="btn btn-sm btn-default btn-small green" style="color:white; margin-right: 10px;" >Sim</a>
                        </div>
                    </div>
                </div>
                <div>
                    <nav>
                        <div class="nav-wrapper">
                            <div style="margin-left: 40px" class="col s12">
                                <a href="<c:url value="/home"/>" class="breadcrumb">inicio</a>
                            <a href="<c:url value=""/>" class="breadcrumb">Linhas</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col s10 offset-s1">
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
                                    <a href="#modal-create" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>Adicionar</a>
                                    <br><br/>
                                    <br><br/>
                                    <c:forEach items="${sectorList}" var="sector">
                                        <tr>
                                            <td>${sector.id}</td>
                                            <td>${sector.name}</td>
                                            <td>
                                                <a class="btn btn-sm btn-default btn-small ffc400 amber accent-3 modal-trigger" href="#modal-update" style="color:white;" onclick="setNameSectorEdit('${sector.name}')"><i class="material-icons right">edit</i>Alterar</a>
                                                <a class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadosModal(${sector.id}, '${sector.name}')" style="color:white;"><i class="material-icons right">delete</i>Excluir</a>
                                            </td>
                                        </tr>    
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   
        </c:if>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
        <script src="<c:url value="/resources/js/initializeModal.js"/>"></script>
        <script>
                                                    function setNameSectorEdit(sectorName) {
                                                        document.querySelector("[name='sectorNameEdit']").value = sectorName;
                                                    }
        </script>
    </body>
</html>
