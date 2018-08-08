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
                            <a href="<c:url value="/home"/>" class="breadcrumb">inicio</a>
                        <a href="<c:url value="/login"/>" class="breadcrumb">autenticação</a>
                        <a href="<c:url value="/register"/>" class="breadcrumb">cadastro</a>
                        <a href="#!" class="breadcrumb">cadastro consumidor</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="container" >
            <div class="row">
                <div class="col s12">
                    <h3 style="color: #2196f3">Cadastro Para Consumidor</h3>
                    <div class="card">
                        <div class="card-content">
                            <form method="POST">
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="name">Nome:</label>
                                    <input type="text" class="form-control" name="name" id="name" value="${consumer.name}" required>
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="lastName">Sobrenome:</label>
                                    <input type="text" class="form-control" name="lastName" id="lastName" value="${consumer.lastName}" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input type="email" class="form-control" name="email" id="email" value="${consumer.email}" required>
                                </div>
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="password">Senha:</label>
                                    <input type="password" class="form-control" name="password" id="password" required>
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="passwordConfirm">Confirmar Senha:</label>
                                    <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" required>
                                </div>
                                <div class="form-group" style="padding-right: 0">
                                    <label for="addressLocation">Logradouro:</label>
                                    <input type="text" class="form-control" name="addressLocation" id="addressLocation" value="${consumer.addressLocation}" required>
                                </div>
                                <div class="form-group">
                                    <label for="numberHouse">Número:</label>
                                    <input type="number" class="form-control" name="numberHouse" id="numberHouse" value="${consumer.numberHouse}" required>
                                </div>
                                <div class="form-group">
                                    <label for="neighborhood">Bairro:</label>
                                    <input type="text" class="form-control" name="neighborhood" id="neighborhood" value="${consumer.neighborhood}" required>
                                </div>
                                <div class="form-group col s6">
                                    <label for="cityId">Estado:</label>
                                    <select class="form-control" name="cityId" id="cityId">
                                        <c:forEach items="${cityList}" var="city">
                                            <option <c:if test="${consumer.cityId eq city.id}">selected="true"</c:if> value="${city.id}">${categoria.nome}</option>                            
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col s6">
                                    <label for="cityId">Municipio:</label>
                                    <select class="form-control" name="cityId" id="cityId">
                                        <option notselected></option>
                                        <c:forEach items="${cityList}" var="city">
                                            <option <c:if test="${consumer.cityId eq city.id}">selected="true"</c:if> value="${city.id}">${city.name}</option>                            
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="zipCode">CEP:</label>
                                    <input type="text" class="form-control" name="zipCode" id="zipCode" value="${consumer.zipCode}" required>
                                </div>
                                <div class="form-group">
                                    <label for="zipCode">CPF:</label>
                                    <input type="text" class="form-control" name="cpf" id="cpf" value="${consumer.cpf}" required>
                                </div>
                                <div class="form-group">
                                    <label for="telephone">Telefone:</label>
                                    <input type="text"  class="form-control" name="telephone" id="telephone" value="${consumer.telephone}" required>
                                </div>
                                <button id="btn-btn-ezmart-style" type="submit" class="btn" value="confirmar"><i class="material-icons left">border_color</i>Cadastrar</button>
                            </form>
                        </div>
                    </div>
                </div>  
            </div>                    
        </div>      
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
<script>
var password = document.getElementById("password")
  , passwordConfirm = document.getElementById("passwordConfirm");

function validatePassword(){
  if(password.value !== passwordConfirm.value) {
    passwordConfirm.setCustomValidity("Confirmação de Senha invalida");
  } else {
    passwordConfirm.setCustomValidity('');
  }
}
password.onchange = validatePassword;
passwordConfirm.onkeyup = validatePassword;

$("#telephone").mask("(00) 0000-00009");

</script>