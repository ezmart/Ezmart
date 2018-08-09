<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col s12">
    <div class="card">
        <div class="card-content">
            <h1>Editar Perfil</h1>
            <form method="POST">
                <div class="form-group col s6" style="padding: 0">
                    <label for="name">Nome:</label>
                    <input type="text" class="form-control input-text-register" name="name" id="name" value="${consumer.name}" required>
                </div>
                <div class="form-group col s6" style="padding-right: 0">
                    <label for="lastName">Sobrenome:</label>
                    <input type="text" class="form-control input-text-register" name="lastName" id="lastName" value="${consumer.lastName}" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control input-text-register" name="email" id="email" value="${consumer.email}" disabled="" required>
                </div>
                <div class="form-group" style="padding-right: 0">
                    <label for="addressLocation">Logradouro:</label>
                    <input type="text" class="form-control input-text-register" name="addressLocation" id="addressLocation" value="${consumer.addressLocation}" required>
                </div>
                <div class="form-group">
                    <label for="numberHouse">Número:</label>
                    <input type="number" class="form-control input-text-register" name="numberHouse" id="numberHouse" value="${consumer.numberHouse}" required>
                </div>
                <div class="form-group">
                    <label for="neighborhood">Bairro:</label>
                    <input type="text" class="form-control input-text-register" name="neighborhood" id="neighborhood" value="${consumer.neighborhood}" required>
                </div>
                <div class="form-group col s6">
                    <label for="stateId">Estado:</label>
                    <select class="form-control input-text-register" name="stateId" id="stateId">
                        <option notselected></option>
                        <c:forEach items="${stateList}" var="state">
                            <option <c:if test="${stateIdConsumer eq state.id}">selected="true"</c:if> id="stateId" value="${state.id}">${state.name}</option>                            
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col s6">
                    <label for="cityId">Municipio:</label>
                    <select class="form-control input-text-register" name="cityId" id="cityId">
                        <option notselected></option>
                        <c:forEach items="${cityList}" var="city">
                            <option <c:if test="${cityIdConsumer eq city.id}">selected="true"</c:if> id="cityId" value="${city.id}">${city.name}</option>                            
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="zipCode">CEP:</label>
                    <input type="text" class="form-control input-text-register" name="zipCode" id="zipCode" value="${consumer.zipCode}" required>
                </div>
                <div class="form-group">
                    <label for="zipCode">CPF:</label>
                    <input type="text" class="form-control input-text-register" name="cpf" id="cpf" value="${consumer.cpf}" disabled="" required>
                </div>
                <div class="form-group">
                    <label for="telephone">Telefone:</label>
                    <input type="text" class="form-control input-text-register" name="telephone" id="telephone" value="${consumer.telephone}" required>
                </div>
                <button id="btn-btn-ezmart-style" type="submit" class="btn" value="confirmar"><i class="material-icons left">border_color</i>Atualizar Dados</button>
            </form>
        </div>
    </div>
</div>
                