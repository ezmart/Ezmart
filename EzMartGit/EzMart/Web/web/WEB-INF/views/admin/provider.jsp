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
                        <h4>Adicionar Fornecedor<i class=" small material-icons" style="margin-left: 10px;">add_box</i></h4>
                        <div class="row">
                            <form class="col s12" method="POST">
                                <div>
                                    <label for="cnpj">CNPJ</label>
                                    <input id="providerCnpj" name="cnpjProvider" maxlength="18" type="text" class="validate" required>
                                </div>
                                <div>
                                    <label for="nome">Nome Fantasia</label>
                                    <input id="providerName" name="nameProvider" type="text" class="validate" required>
                                </div>
                                <div>
                                    <label for="businessName">Razão Social</label>
                                    <input id="providerBusinessName" name="businessNameProvider" type="text" class="validate">
                                </div>
                                <div class="modal-footer">
                                    <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Voltar</a>
                                    <button class=" modal-action green btn-flat" id="addButton" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="modal-update" class="modal">
                    <div class="modal-content">
                        <h4>Editar Fornecedor<i class=" small material-icons" style="margin-left: 10px;">edit</i></h4>
                        <div class="row">
                            <form class="col s12" method="POST" action="/ezmartWeb/providerEdit">
                                <div>
                                    <label for="cnpj">CNPJ</label>
                                    <input type="text" style="display: none"  name="providerId" id = "idProvider">
                                    <input id="cnpjProviderEdit" name="providerCnpjEdit" maxlength="18" type="text" class="validate" required>
                                </div>
                                <div>
                                    <label for="nome">Nome Fantasia</label>
                                    <input id="nameProviderEdit" name="providerNameEdit" type="text" class="validate" required>
                                </div>
                                <div>
                                    <label for="razaoSocial">Razão Social</label>
                                    <input id="businessNameProviderEdit" name="providerBusinessNameEdit" type="text" class="validate">
                                </div>
                                <div class="modal-footer">
                                    <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Voltar</a>
                                    <button class=" modal-action green btn-flat" type="submit" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="modal-delete" class="modal">
                    <div class="modal-content">
                        <h4>Excluir Fornecedor<i class=" small material-icons" style="margin-left: 10px;">delete</i></h4>
                        <p><font size="5">Tem certeza que deseja excluir o fornecedor: </font><font color="red" size="5"><container id = "provider-id"></container></font> <font size="5">?</font></p>
                        <div class="modal-footer">
                            <form method="POST" action="/ezmartWeb/providerDelete">
                                <input type="text" style="display: none"  name="providerId" id = "idProviderDelete">
                                <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Não</a>
                                <button class="btn btn-sm btn-default btn-small green" type="submit" style="color:white; margin-right: 10px;" >Sim</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div>
                    <nav>
                        <div class="nav-wrapper">
                            <div style="margin-left: 40px" class="col s12">
                                <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                            <a href="<c:url value=""/>" class="breadcrumb">Fornecedores</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div>
                <div class="row">
                    <div class="col s12">
<!--                        <div class="card">
                            <div class="card-content">-->
                                <h4>Fornecedores</h4>
                                <table class="table">
                                    <tr>
                                        <th>Id</th>
                                        <th>CNPJ</th>
                                        <th>Nome Fantasia</th>
                                        <th>Razão Social</th>
                                    <hr>
                                    <th></th>
                                    </tr>
                                    <a href="#modal-create" class="btn btn-sm btn-default btn-small green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>Adicionar</a>
                                    <br><br/>
                                    <br><br/>
                                    <c:forEach items="${providerList}" var="provider">
                                        <tr>
                                            <td>${provider.id}</td>
                                            <td>${provider.cnpj}</td>
                                            <td>${provider.name}</td>
                                            <td>${provider.businessName}</td>
                                            <td>
                                                <a class="btn btn-sm btn-default btn-small ffc400 amber accent-3 modal-trigger" href="#modal-update" style="color:white;" onclick="setProviderEdit(${provider.id}, '${provider.cnpj}', '${provider.name}', '${provider.businessName}')"><i class="material-icons right">edit</i>Alterar</a>
                                                <a class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadosModalProvider(${provider.id}, '${provider.name}')" style="color:white;"><i class="material-icons right">delete</i>Excluir</a>
                                            </td>
                                        </tr>    
                                    </c:forEach>
                                </table>
<!--                            </div>
                        </div>-->
                    </div>
                </div>
            </div>   
        </c:if>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
