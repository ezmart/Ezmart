<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
        <body>  
        <c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                        <a href="<c:url value="/login"/>" class="breadcrumb">Autenticação</a>
                        <a href="<c:url value="/register"/>" class="breadcrumb">Cadastro</a>
                        <a href="#!" class="breadcrumb">Cadastro consumidor</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="container">
            <div class="row">
                <div class="col s12">
                    <h3 style="color: #2196f3">Cadastro para consumidor</h3>
                    <div class="card">
                        <div class="card-content">
                            <form method="POST">
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="name">Nome:</label>
                                    <input type="text" class="form-control" name="name" id="name" value="${name}">
                                    <span style="color: orangered">${errors.name}</span>
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="lastName">Sobrenome:</label>
                                    <input type="text" class="form-control" name="lastName" id="lastName" value="${lastName}">
                                    <span style="color: orangered">${errors.lastName}</span>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input type="email" class="form-control" name="email" id="email" value="${email}">
                                    <span style="color: orangered">${errors.email}</span>
                                </div>
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="password">Senha:</label>
                                    <input type="password" class="form-control" name="password" id="password" value="${password}">
                                    <span style="color: orangered">${errors.password}</span>
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="passwordConfirm">Confirmar Senha:</label>
                                    <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" value="${passwordConfirm}">
                                    <span style="color: orangered">${errors.passwordConfirm}</span>
                                </div>
                                <div class="form-group" style="padding-right: 0">
                                    <label for="addressLocation">Logradouro:</label>
                                    <input type="text" class="form-control" name="addressLocation" id="addressLocation" value="${addressLocation}">
                                    <span style="color: orangered">${errors.addressLocation}</span>
                                </div>
                                <div class="form-group">
                                    <label for="numberHouse">Número:</label>
                                    <input type="number" class="form-control" name="numberHouse" id="numberHouse" value="${numberHouse}">
                                    <span style="color: orangered">${errors.numberHouse}</span>
                                </div>
                                <div class="form-group">
                                    <label for="neighborhood">Bairro:</label>
                                    <input type="text" class="form-control" name="neighborhood" id="neighborhood" value="${neighborhood}">
                                    <span style="color: orangered">${errors.neighborhood}</span>
                                </div>
                                <div class="form-group col s6">
                                    <label for="stateId">Estado:</label>
                                    <select onchange="setCitiesWithState()" class="form-control input-text-register" name="stateId" id="stateId">
                                        <option notselected></option>
                                        <c:forEach items="${stateList}" var="state">
                                            <option  <c:if test="${stateIdConsumer eq state.id}">selected="true"</c:if> id="stateId" value="${state.id}">${state.name}</option>                            
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col s6">
                                    <label>Municipio:</label>
                                    <select class="form-control input-text-register" name="cityId" id="cityId">
                                        <option notselected>Selecione primeiro seu estado</option>

                                    </select>
                                    <span style="color: orangered">${errors.cityId}</span>
                                </div>
                                <div class="form-group">
                                    <label for="zipCode">CEP:</label>
                                    <input type="text" class="form-control" name="zipCode" id="zipCode" value="${zipCode}">
                                    <span style="color: orangered">${errors.zipCode}</span>
                                </div>
                                <div class="form-group">
                                    <label for="cpf">CPF:</label>
                                    <input type="text" class="form-control" name="cpf" id="cpfUserRegister" value="${cpf}">
                                    <span style="color: orangered">${errors.cpf}</span>
                                </div>
                                <div class="form-group">
                                    <label for="telephone">Celular:</label>
                                    <input type="text"  class="form-control" name="telephone" id="cellUserConsumerRegister" value="${telephone}">
                                    <span style="color: orangered">${errors.telephone}</span>
                                </div>
                                <br/>
                                <input type="text" style="display: none" name="latitude" id="latitude" value="">
                                <input type="text" style="display: none" name="longitude" id="longitude" value="">
                                <button id="btn-btn-ezmart-style" type="submit" class="btn" value="confirmar"><i class="material-icons left">border_color</i>Cadastrar</button>
                            </form>
                            <br/>
                            <p>Ao clicar em cadastrar você estará concordando com os <a href="<c:url value="/terms"/>">termos de uso</a></p>
                        </div>
                    </div>
                </div>  
            </div>   
        </div> 

        <div class="container" id="map">
            <div class="row">
                <div class="col s12">

                    <div class="card">
                        <div class="card-content">
                            <c:import url="/WEB-INF/views/maps/google_maps_register.jsp"/>
                        </div>
                    </div>
                </div>
            </div> 
        </div> 

        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/mascara.js"/>"></script>
    </body>
</html>



