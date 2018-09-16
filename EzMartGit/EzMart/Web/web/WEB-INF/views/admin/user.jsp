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
                <div id="modal-create-est" class="modal">
                    <div class="modal-content">
                        <h4>Adicionar Usuário<i class=" small material-icons" style="margin-left: 10px;">add_box</i></h4>
                        <div class="row">
                            <form class="col s12" enctype="multipart/form-data" method="POST" >
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="companyName">Nome Fantasia:</label>
                                    <input type="text" class="form-control" name="companyName" id="companyName">
                                    <input type="text" style="display: none"  name="userType" value="emporium">
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="businessName">Razão Social:</label>
                                    <input type="text" class="form-control" name="businessName" id="businessName">
                                </div>
                                <div class="form-group">
                                    <label for="cnpj">CNPJ:</label>
                                    <input type="text" class="form-control" name="cnpj" maxlength="18" id="cnpjUser">
                                </div>
                                <div class="form-group col s6">
                                    <label for="email">Email:</label>
                                    <input type="email" class="form-control" name="email" id="email">
                                </div>
                                <div class="form-group col s6">
                                    <label for="secondEmail">Outro Email:</label>
                                    <input type="email" class="form-control" name="secondEmail" id="secondEmail">
                                </div>
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="password">Senha:</label>
                                    <input type="password" class="form-control" name="password" id="password">
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="passwordConfirm">Confirmar Senha:</label>
                                    <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm">
                                </div>
                                <div class="form-groug" style="padding-right: 0">
                                    <label for="addressLocation">Logradouro:</label>
                                    <input type="text" class="form-control" name="addressLocation" id="addressLocation">
                                </div>
                                <div class="form-group">
                                    <label for="numberHouse">Número:</label>
                                    <input type="number" class="form-control" name="numberHouse" id="numberHouse">
                                </div>
                                <div class="form-group">
                                    <label for="neighborhood">Bairro:</label>
                                    <input type="text" class="form-control" name="neighborhood" id="neighborhood">
                                </div>
                                <div class="form-group col s6">
                                    <label for="stateId">Estado:</label>
                                    <select class="form-control input-text-register" name="stateId" id="stateId">
                                        <option notselected></option>
                                    <c:forEach items="${stateList}" var="state">
                                        <option id="stateId" value="${state.id}">${state.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6">
                                <label for="cityId">Municipio:</label>
                                <select class="form-control input-text-register" name="cityId" id="cityId">
                                    <option notselected></option>
                                    <c:forEach items="${cityList}" var="city">
                                        <option id="cityId" value="${city.id}">${city.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6" style="padding: 0">
                                <label for="zipCode">CEP:</label>
                                <input type="text" class="form-control" name="zipCode" id="zipCode">
                            </div>
                            <div class="form-group col s6" style="padding-right:0">
                                <label for="telephone">Telefone:</label>
                                <input type="text" class="form-control" name="telephone" id="telephone">
                            </div>
                            <div>
                                <label for="active">Ativo:</label>
                                <select class="form-control input-text-register" name="activeUser" id="activeId">
                                    <option notselected></option>
                                    <option value="true" selected="true">SIM</option>                            
                                    <option value="false">NÃO</option>                            
                                </select>
                            </div>
                                <div>
                                <label for="imageProduct">Imagem</label>
                                <div class="file-field input-field">
                                    <div class="btn">
                                        <span><i class="material-icons center">file_upload</i></span>
                                        <input type="file" id="imageUser" name="userImage">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text" placeholder="Upload imagem">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Voltar</a>
                                <button class=" modal-action green btn-flat" id="addButton" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div id="modal-create-consumer" class="modal">
                <div class="modal-content">
                    <h4>Adicionar Usuário<i class=" small material-icons" style="margin-left: 10px;">add_box</i></h4>
                    <div class="row">
                        <form class="col s12" enctype="multipart/form-data" method="POST" >
                            <div class="form-group col s6" style="padding: 0">
                                <label for="name">Nome:</label>
                                <input type="text" class="form-control" name="name" id="name" value="${name}">
                            </div>
                            <div class="form-group col s6" style="padding-right: 0">
                                <label for="lastName">Sobrenome:</label>
                                <input type="text" class="form-control" name="lastName" id="lastName" value="${lastName}">
                            </div>
                            <div class="form-group">
                                <label for="cpf">CPF:</label>
                                <input type="text" class="form-control" name="cpf" maxlength="11" id="cpf" value="${cpf}">
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" name="email" id="email" value="${email}">
                            </div>
                            <div class="form-group col s6" style="padding: 0">
                                <label for="password">Senha:</label>
                                <input type="password" class="form-control" name="password" id="password" value="${password}">
                            </div>
                            <div class="form-group col s6" style="padding-right: 0">
                                <label for="passwordConfirm">Confirmar Senha:</label>
                                <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" value="${passwordConfirm}">
                            </div>
                            <div class="form-group" style="padding-right: 0">
                                <label for="addressLocation">Logradouro:</label>
                                <input type="text" class="form-control" name="addressLocation" id="addressLocation" value="${addressLocation}">
                            </div>
                            <div class="form-group">
                                <label for="numberHouse">Número:</label>
                                <input type="number" class="form-control" name="numberHouse" id="numberHouse" value="${numberHouse}">
                            </div>
                            <div class="form-group">
                                <label for="neighborhood">Bairro:</label>
                                <input type="text" class="form-control" name="neighborhood" id="neighborhood" value="${neighborhood}">
                            </div>
                            <div class="form-group col s6">
                                <label for="stateId">Estado:</label>
                                <select class="form-control input-text-register" name="stateId" id="stateId">
                                    <option notselected></option>
                                    <c:forEach items="${stateList}" var="state">
                                        <option id="stateId" value="${state.id}">${state.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6">
                                <label for="cityId">Municipio:</label>
                                <select class="form-control input-text-register" name="cityId" id="cityId">
                                    <option notselected></option>
                                    <c:forEach items="${cityList}" var="city">
                                        <option id="cityId" value="${city.id}">${city.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6" style="padding: 0">
                                <label for="zipCode">CEP:</label>
                                <input type="text" class="form-control" name="zipCode" id="zipCode">
                            </div>
                            <div class="form-group col s6" style="padding-right:0">
                                <label for="telephone">Telefone:</label>
                                <input type="text" class="form-control" name="telephone" id="telephone">
                            </div>
                            <div>
                                <label for="adm">Administrador:</label>
                                <select class="form-control input-text-register" name="admin" id="adminId">
                                    <option notselected></option>
                                    <option value="true" selected="true">SIM</option>                            
                                    <option value="false">NÃO</option>                            
                                </select>
                            </div>
                            <div>
                                <label for="active">Ativo:</label>
                                <select class="form-control input-text-register" name="activeUser" id="activeId">
                                    <option notselected></option>
                                    <option value="true" selected="true">SIM</option>                            
                                    <option value="false">NÃO</option>                            
                                </select>
                            </div>
                            <div>
                                <label for="imageProduct">Imagem</label>
                                <div class="file-field input-field">
                                    <div class="btn">
                                        <span><i class="material-icons center">file_upload</i></span>
                                        <input type="file" id="imageUser" name="userImage">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text" placeholder="Upload imagem">
                                    </div>
                                </div>
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
                    <h4>Editar Usuário<i class=" small material-icons" style="margin-left: 10px;">edit</i></h4>
                    <div class="row">
                        <form class="col s12" method="POST" action="/ezmartWeb/userEdit">
                            <div>
                                <label for="nome">Nome</label>
                                <input type="text" style="display: none"  name="userIdEdit" id = "idProduct">
                                <input id="nameProductEdit" name="userNameEdit" type="text" class="validate" required>
                            </div>
                            <div>
                                <label for="codBarras">Código de Barras</label>
                                <input id="barCodeProductEdit" name="userBarCodeEdit" maxlength="13" pattern="[0-9]+$" title="Somente números!" type="text" class="validate" required>
                            </div>
                            <div>
                                <label for="marca">Marca</label>
                                <input id="brandProductEdit" name="userBrandEdit" type="text" class="validate">
                            </div>
                            <div>
                                <label for="sector">Linha</label>
                                <select name="sectorIdProductEdit" id="sectorIdProductEdit" required>
                                    <option notselected></option>
                                    <c:forEach items="${sectorList}" var="sector">
                                        <option 
                                            <%--<c:if test="${userList.sector.id eq sector.id}">selected="true"</c:if>--%>
                                            value="${sector.id}" id="sectorIdProductEdit">${sector.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <label for="provider">Fornecedor</label>
                                <select name="providerIdProductEdit" id="providerIdProductEdit" required>
                                    <option notselected></option>
                                    <c:forEach items="${providerList}" var="provider">
                                        <option 
                                            <%--<c:if test="${userList.provider.id eq provider.id}">selected="true"</c:if>--%>
                                            value="${provider.id}">${provider.name} - ${provider.cnpj}</option>
                                    </c:forEach>
                                </select>
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
                    <h4>Excluir Usuário<i class=" small material-icons" style="margin-left: 10px;">delete</i></h4>
                    <p><font size="5">Tem certeza que deseja excluir o usuário: </font><font color="red" size="5"><container id = "user-id"></container></font> <font size="5">?</font></p>
                    <div class="modal-footer">
                        <form method="POST" action="/ezmartWeb/userDelete">
                            <input type="text" style="display: none"  name="UserId" id = "idUserDelete">
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
                            <a href="<c:url value=""/>" class="breadcrumb">Produtos</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="container">
                <h4>Usuários</h4>
                <a href="#modal-create-est" class="btn btn-n-floatingsmall green left modal-trigger" style="color:white;"><i class="material-icons center">add</i>ESTABELECIMENTO</a>
                <a href="#modal-create-consumer" class="btn btn-n-floatingsmall green left modal-trigger" style="color:white; margin-left: 15px;"><i class="material-icons center">add</i>CONSUMIDOR/ADM</a>
                <br><br/>
                <div class="row">
                    <div class="col s12 m12">
                        <ul>
                            <c:forEach items="${userList}" var="user">
                                <li class="col l4 m6 s12" >
                                    <div class="card">
                                        <div class="row" style="padding: 20px;">
                                            <div>
                                                <img class="responsive-img-center" src="<c:url value = "/resources/img/user/${user.id}.jpg"/>" alt="imagem usuario" width="150">
                                                <form method="post" enctype="multipart/form-data" action="/ezmartWeb/user/uploadImage">
                                                    <div class="file-field input-field">
                                                        <div class="btn">
                                                            <span><i class="material-icons center">file_upload</i></span>
                                                            <input type="file" id="userImage" name="userImage">
                                                            <input value="${user.id}" name="idUserForImage" style="display: none">
                                                        </div>
                                                        <div class="file-path-wrapper">
                                                            <input class="file-path validate" type="text" placeholder="Alterar imagem">
                                                        </div>
                                                    </div>
                                                    <container>
                                                        <button id="btnUpload" type="submit" class="btn btn-success btn-small"><i class="material-icons left">save</i>SALVAR</button>
                                                    </container>
                                                </form>
                                            </div>
                                            <br/>
                                            <p style="font-size: 20px"><b>Id:</b> ${user.id}</p>
                                            <p style="font-size: 20px"><b>Nome:</b> <c:if test="${not empty user.consumer.name}">${user.consumer.name}</c:if> <c:if test="${not empty user.establishment.name}">${user.establishment.name}</c:if> </p>
                                            <p style="font-size: 20px"><b>E-mail:</b> ${user.email}</p>
    <!--                                            <p style="font-size: 20px"><b>Logradouro:</b> ${user.addressLocation}</p>
                                            <p style="font-size: 20px"><b>Número:</b> ${user.numberHouse}</p>
                                            <p style="font-size: 20px"><b>Bairro:</b> ${user.neighborhood}</p>
                                            <p style="font-size: 20px"><b>Cidade:</b> ${user.city.name}</p>
                                            <p style="font-size: 20px"><b>Estado:</b> ${user.state.initials}</p>
                                            <p style="font-size: 20px"><b>Telefone:</b> ${user.telephone}</p>-->
                                            <p style="font-size: 20px"><b>Tipo:</b> ${user.userType}</p>
                                            <p style="font-size: 20px"><b>Ativo:</b> ${user.activeString}</p>
                                            <div class="card-action card-content">
                                                <div class="col s12 center">
                                                    <a class="btn btn-sm btn-default btn-small ffc400 amber accent-3 modal-trigger" href="#modal-update" style="color:white;" ><i class="material-icons center">edit</i></a>
                                                    <a class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadosModalUser(${user.id}, '${user.consumer.name}', '${user.establishment.name}')" style="color:white;"><i class="material-icons center">delete</i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
    </body>
</html>
