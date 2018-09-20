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
                                    <input type="text" class="form-control" name="companyName" id="companyName" required>
                                    <input type="text" style="display: none"  name="userType" value="emporium">
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="businessName">Razão Social:</label>
                                    <input type="text" class="form-control" name="businessName" id="businessName" required>
                                </div>
                                <div class="form-group">
                                    <label for="cnpj">CNPJ:</label>
                                    <input type="text" class="form-control" name="cnpj" maxlength="18" id="cnpjUser" required>
                                </div>
                                <div class="form-group col s6">
                                    <label for="email">Email:</label>
                                    <input type="email" class="form-control" name="email" id="email" required>
                                </div>
                                <div class="form-group col s6">
                                    <label for="secondEmail">Outro Email:</label>
                                    <input type="email" class="form-control" name="secondEmail" id="secondEmail">
                                </div>
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="password">Senha:</label>
                                    <input type="password" class="form-control" name="password" id="password" required>
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="passwordConfirm">Confirmar Senha:</label>
                                    <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" required>
                                </div>
                                <div class="form-groug" style="padding-right: 0">
                                    <label for="addressLocation">Logradouro:</label>
                                    <input type="text" class="form-control" name="addressLocation" id="addressLocation" required>
                                </div>
                                <div class="form-group">
                                    <label for="numberHouse">Número:</label>
                                    <input type="number" class="form-control" name="numberHouse" id="numberHouse" required>
                                </div>
                                <div class="form-group">
                                    <label for="neighborhood">Bairro:</label>
                                    <input type="text" class="form-control" name="neighborhood" id="neighborhood" required>
                                </div>
                                <div class="form-group col s6">
                                    <label for="stateId">Estado:</label>
                                    <select class="form-control input-text-register" name="stateId" id="stateId" required>
                                        <option notselected></option>
                                    <c:forEach items="${stateList}" var="state">
                                        <option id="stateId" value="${state.id}">${state.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6">
                                <label for="cityId">Municipio:</label>
                                <select class="form-control input-text-register" name="cityId" id="cityId" required>
                                    <option notselected></option>
                                    <c:forEach items="${cityList}" var="city">
                                        <option id="cityId" value="${city.id}">${city.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6" style="padding: 0">
                                <label for="zipCode">CEP:</label>
                                <input type="text" class="form-control" name="zipCode" id="zipCode" required>
                            </div>
                            <div class="form-group col s6" style="padding-right:0">
                                <label for="telephone">Telefone:</label>
                                <input type="text" class="form-control" name="telephone" id="telephone" required>
                            </div>
                            <div>
                                <label for="planId">Plano:</label>
                                <select class="form-control input-text-register" name="plan" id="planId" required>
                                    <option notselected></option>
                                    <option id="planId" value="1">OURO</option>                            
                                    <option id="planId" value="2">PRATA</option>                            
                                    <option id="planId" value="3">BRONZE</option>                            
                                </select>
                            </div>
                            <div>
                                <label for="planStartId">Início do Plano:</label>
                                <input type="date" name="planStart" required>
                            </div>
                            <div>
                                <label for="planFinishId">Término do Plano:</label>
                                <input type="date" name="planFinish" required>
                            </div>
                            <div>
                                <label for="active">Ativo:</label>
                                <select class="form-control input-text-register" name="activeUser" id="activeId" required>
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
                                        <input type="file" id="imageUser" name="userImage" required>
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
                                <input type="text" class="form-control" name="name" id="name" value="${name}" required>
                            </div>
                            <div class="form-group col s6" style="padding-right: 0">
                                <label for="lastName">Sobrenome:</label>
                                <input type="text" class="form-control" name="lastName" id="lastName" value="${lastName}" required>
                            </div>
                            <div class="form-group">
                                <label for="cpf">CPF:</label>
                                <input type="text" class="form-control" name="cpf" maxlength="11" id="cpf" value="${cpf}" required>
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" name="email" id="email" value="${email}" required>
                            </div>
                            <div class="form-group col s6" style="padding: 0">
                                <label for="password">Senha:</label>
                                <input type="password" class="form-control" name="password" id="password" value="${password}" required>
                            </div>
                            <div class="form-group col s6" style="padding-right: 0">
                                <label for="passwordConfirm">Confirmar Senha:</label>
                                <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" value="${passwordConfirm}" required>
                            </div>
                            <div class="form-group" style="padding-right: 0">
                                <label for="addressLocation">Logradouro:</label>
                                <input type="text" class="form-control" name="addressLocation" id="addressLocation" value="${addressLocation}" required>
                            </div>
                            <div class="form-group">
                                <label for="numberHouse">Número:</label>
                                <input type="number" class="form-control" name="numberHouse" id="numberHouse" value="${numberHouse}" required>
                            </div>
                            <div class="form-group">
                                <label for="neighborhood">Bairro:</label>
                                <input type="text" class="form-control" name="neighborhood" id="neighborhood" value="${neighborhood}" required>
                            </div>
                            <div class="form-group col s6">
                                <label for="stateId">Estado:</label>
                                <select class="form-control input-text-register" name="stateId" id="stateId" required>
                                    <option notselected></option>
                                    <c:forEach items="${stateList}" var="state">
                                        <option id="stateId" value="${state.id}">${state.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6">
                                <label for="cityId">Municipio:</label>
                                <select class="form-control input-text-register" name="cityId" id="cityId" required>
                                    <option notselected></option>
                                    <c:forEach items="${cityList}" var="city">
                                        <option id="cityId" value="${city.id}">${city.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6" style="padding: 0">
                                <label for="zipCode">CEP:</label>
                                <input type="text" class="form-control" name="zipCode" id="zipCode" required>
                            </div>
                            <div class="form-group col s6" style="padding-right:0">
                                <label for="telephone">Telefone:</label>
                                <input type="text" class="form-control" name="telephone" id="telephone" required>
                            </div>
                            <div>
                                <label for="adm">Administrador:</label>
                                <select class="form-control input-text-register" name="admin" id="adminId" required>
                                    <option notselected></option>
                                    <option value="true">SIM</option>                            
                                    <option value="false">NÃO</option>                            
                                </select>
                            </div>
                            <div>
                                <label for="active">Ativo:</label>
                                <select class="form-control input-text-register" name="activeUser" id="activeId" required>
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
                                        <input type="file" id="imageUser" name="userImage" required>
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

            <div id="modal-update-consumer" class="modal">
                <div class="modal-content">
                    <h4>Editar Usuário<i class=" small material-icons" style="margin-left: 10px;">edit</i></h4>
                    <div class="row">
                        <form class="col s12" method="POST" action="/ezmartWeb/userEdit">
                            <div class="form-group col s6" style="padding: 0">
                                <label for="nameEdit">Nome:</label>
                                <input type="text" class="form-control" name="nameEdit" id="nameEdit" required>
                                <input type="text" style="display: none"  name="userIdEdit" id = "idUserEdit">
                            </div>
                            <div class="form-group col s6" style="padding-right: 0">
                                <label for="lastNameEdit">Sobrenome:</label>
                                <input type="text" class="form-control" name="lastNameEdit" id="lastNameEdit" required>
                            </div>
                            <div class="form-group">
                                <label for="cpfEdit">CPF:</label>
                                <input type="text" class="form-control" name="cpfEdit" maxlength="11" id="cpfEdit" required>
                            </div>
                            <div class="form-group">
                                <label for="emailEdit">Email:</label>
                                <input type="email" class="form-control" name="emailEdit" id="emailEdit" required>
                            </div>
                            <div class="form-group" style="padding-right: 0">
                                <label for="addressLocationEdit">Logradouro:</label>
                                <input type="text" class="form-control" name="addressLocationEdit" id="addressLocationEdit" required>
                            </div>
                            <div class="form-group">
                                <label for="numberHouseEdit">Número:</label>
                                <input type="number" class="form-control" name="numberHouseEdit" id="numberHouse" required>
                            </div>
                            <div class="form-group">
                                <label for="neighborhoodEdit">Bairro:</label>
                                <input type="text" class="form-control" name="neighborhoodEdit" id="neighborhoodEdit" required>
                            </div>
                            <div class="form-group col s6">
                                <label for="stateIdEdit">Estado:</label>
                                <select class="form-control input-text-register" name="stateIdEdit" id="stateIdEdit" required>
                                    <option notselected></option>
                                    <c:forEach items="${stateList}" var="state">
                                        <option id="stateIdEdit" value="${state.id}">${state.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6">
                                <label for="cityIdEdit">Municipio:</label>
                                <select class="form-control input-text-register" name="cityIdEdit" id="cityIdEdit" required>
                                    <option notselected></option>
                                    <c:forEach items="${cityList}" var="city">
                                        <option id="cityIdEdit" value="${city.id}">${city.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6" style="padding: 0">
                                <label for="zipCodeEdit">CEP:</label>
                                <input type="text" class="form-control" name="zipCodeEdit" id="zipCodeEdit" required>
                            </div>
                            <div class="form-group col s6" style="padding-right:0">
                                <label for="telephoneEdit">Telefone:</label>
                                <input type="text" class="form-control" name="telephoneEdit" id="telephoneEdit" required>
                            </div>
                            <div>
                                <label for="admEdit">Administrador:</label>
                                <select class="form-control input-text-register" name="adminEdit" id="adminIdEdit" required>
                                    <option notselected></option>
                                    <option value="true" name="adminEditTrue">SIM</option>                            
                                    <option value="false" name="adminEditFalse">NÃO</option>                            
                                </select>
                            </div>
                            <div>
                                <label for="activeEdit">Ativo:</label>
                                <select class="form-control input-text-register" name="activeUserEdit" id="activeIdEdit" required>
                                    <option notselected></option>
                                    <option value="true" name="activeUserTrue" >SIM</option>                            
                                    <option value="false" name="activeUserFalse" >NÃO</option>                            
                                </select>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Voltar</a>
                                <button class=" modal-action green btn-flat" type="submit" id="addButton" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div id="modal-update-est" class="modal">
                <div class="modal-content">
                    <h4>Editar Usuário<i class=" small material-icons" style="margin-left: 10px;">edit</i></h4>
                    <div class="row">
                        <form class="col s12" enctype="multipart/form-data" method="POST" action="/ezmartWeb/userEstEdit">
                            <div class="form-group col s6" style="padding: 0">
                                    <label for="companyNameEdit">Nome Fantasia:</label>
                                    <input type="text" class="form-control" name="companyNameEdit" id="companyNameEdit" required>
                                    <input type="text" style="display: none"  name="userIdEstEdit" id = "idUserEstEdit">
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="businessNameEdit">Razão Social:</label>
                                    <input type="text" class="form-control" name="businessNameEdit" id="businessNameEdit" required>
                                </div>
                                <div class="form-group">
                                    <label for="cnpjEdit">CNPJ:</label>
                                    <input type="text" class="form-control" name="cnpjEdit" maxlength="18" id="cnpjUserEdit" required>
                                </div>
                                <div class="form-group col s6">
                                    <label for="emailEstEdit">Email:</label>
                                    <input type="email" class="form-control" name="emailEstEdit" id="emailEstEdit" required>
                                </div>
                                <div class="form-group col s6">
                                    <label for="secondEmailEdit">Outro Email:</label>
                                    <input type="email" class="form-control" name="secondEmailEdit" id="secondEmailEdit">
                                </div>
                                <div class="form-groug" style="padding-right: 0">
                                    <label for="addressLocationEstEdit">Logradouro:</label>
                                    <input type="text" class="form-control" name="addressLocationEstEdit" id="addressLocationEstEdit" required>
                                </div>
                                <div class="form-group">
                                    <label for="numberHouseEstEdit">Número:</label>
                                    <input type="number" class="form-control" name="numberHouseEstEdit" id="numberHouseEstEdit" required>
                                </div>
                                <div class="form-group">
                                    <label for="neighborhoodEstEdit">Bairro:</label>
                                    <input type="text" class="form-control" name="neighborhoodEstEdit" id="neighborhoodEstEdit" required>
                                </div>
                                <div class="form-group col s6">
                                    <label for="stateIdEstEdit">Estado:</label>
                                    <select class="form-control input-text-register" name="stateIdEstEdit" id="stateIdEstEdit" required>
                                        <option notselected></option>
                                    <c:forEach items="${stateList}" var="state">
                                        <option id="stateIdEstEdit" value="${state.id}">${state.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6">
                                <label for="cityIdEstEdit">Municipio:</label>
                                <select class="form-control input-text-register" name="cityIdEstEdit" id="cityIdEstEdit" required>
                                    <option notselected></option>
                                    <c:forEach items="${cityList}" var="city">
                                        <option id="cityIdEstEdit" value="${city.id}">${city.name}</option>                            
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col s6" style="padding: 0">
                                <label for="zipCodeEstEdit">CEP:</label>
                                <input type="text" class="form-control" name="zipCodeEstEdit" id="zipCodeEstEdit" required>
                            </div>
                            <div class="form-group col s6" style="padding-right:0">
                                <label for="telephoneEstEdit">Telefone:</label>
                                <input type="text" class="form-control" name="telephoneEstEdit" id="telephoneEstEdit" required>
                            </div>
                            <div>
                                <label for="planIdEdit">Plano:</label>
                                <select class="form-control input-text-register" name="planEdit" id="planIdEdit" required>
                                    <option notselected></option>
                                    <option id="planId" value="1">OURO</option>                            
                                    <option id="planId" value="2">PRATA</option>                            
                                    <option id="planId" value="3">BRONZE</option>                            
                                </select>
                            </div>
                            <div>
                                <label for="planStartIdEdit">Início do Plano:</label>
                                <input type="date" name="planStartEdit" required>
                            </div>
                            <div>
                                <label for="planFinishIdEdit">Término do Plano:</label>
                                <input type="date" name="planFinishEdit" required>
                            </div>
                            <div>
                                <label for="activeEstEdit">Ativo:</label>
                                <select class="form-control input-text-register" name="activeUserEstEdit" id="activeIdEstEdit" required>
                                    <option notselected></option>
                                    <option value="true">SIM</option>                            
                                    <option value="false">NÃO</option>                            
                                </select>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-sm btn-default btn-small red modal-close modal-action" style="color:white;">Voltar</a>
                                <button class=" modal-action green btn-flat" id="addButton" value="confirmar" style="margin-right: 15px; color:white;">CONFIRMAR</button>
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
                            <input type="text" style="display: none"  name="userId" id = "idUserDelete">
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
                <a href="#modal-create-est" class="btn btn-n-floatingsmall green left modal-trigger" style="color:white;"><i class="material-icons right">add_box</i>ESTABELECIMENTO</a>
                <a href="#modal-create-consumer" class="btn btn-n-floatingsmall green left modal-trigger" style="color:white; margin-left: 15px;"><i class="material-icons right">add_box</i>CONSUMIDOR/ADM</a>
                <br><br/>
                <div class="row">
                    <div class="col s12 m12">
                        <ul>
                            <c:forEach items="${userList}" var="user">
                                <li class="col l4 m6 s12" >
                                    <div class="card-panel medium">
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
                                            <p style="font-size: 18px"><b>Id:</b> ${user.id}</p>
                                            <p style="font-size: 18px"><b>Nome:</b> <c:if test="${not empty user.consumer.name}">${user.consumer.name}</c:if> <c:if test="${not empty user.establishment.name}">${user.establishment.name}</c:if> </p>
                                            <p style="font-size: 18px"><b>E-mail:</b> ${user.email}</p>
    <!--                                            <p style="font-size: 20px"><b>Logradouro:</b> ${user.addressLocation}</p>
                                            <p style="font-size: 20px"><b>Número:</b> ${user.numberHouse}</p>
                                            <p style="font-size: 20px"><b>Bairro:</b> ${user.neighborhood}</p>
                                            <p style="font-size: 20px"><b>Cidade:</b> ${user.city.name}</p>
                                            <p style="font-size: 20px"><b>Estado:</b> ${user.state.initials}</p>
                                            <p style="font-size: 20px"><b>Telefone:</b> ${user.telephone}</p>-->
                                            <p style="font-size: 18px"><b>Tipo:</b> ${user.userType}</p>
                                            <p style="font-size: 18px"><b>Ativo:</b> ${user.activeString}</p>
                                            <div class="card-action card-content">
                                                <div class="col s12 center">
                                                    <a title="Alterar" class="btn btn-sm btn-default btn-small ffc400 amber accent-3 modal-trigger" <c:if test="${not empty user.consumer.name}"> href="#modal-update-consumer" onclick="setUserEdit(${user.id}, '${user.consumer.name}', '${user.consumer.lastName}', '${user.consumer.cpf}', '${user.email}', '${user.addressLocation}', ${user.numberHouse}, '${user.neighborhood}', '${user.state.name}', '${user.city.name}', '${user.zipCode}', '${user.telephone}', ${user.adm}, ${user.active})" </c:if> <c:if test="${not empty user.establishment.name}"> href="#modal-update-est" onclick="setUserEstEdit(${user.id}, '${user.establishment.name}', '${user.establishment.businessName}', '${user.establishment.cnpj}', '${user.email}','${user.establishment.secondEmail}', '${user.addressLocation}', ${user.numberHouse}, '${user.neighborhood}', '${user.state.name}', '${user.city.name}', '${user.zipCode}', '${user.telephone}', ${user.establishment.plan}, ${user.establishment.planStartDate}, ${user.establishment.planFinalDate}, ${user.active})"</c:if> style="color:white;" ><i class="material-icons center">edit</i></a>
                                                    <a title="Excluir" class="btn btn-sm btn-danger btn-small red modal-trigger" href="#modal-delete" onclick="setDadosModalUser(${user.id}, '${user.consumer.name}', '${user.establishment.name}')" style="color:white;"><i class="material-icons center">delete</i></a>
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
