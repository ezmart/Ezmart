<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col s12">
    <div class="card">
        <div class="card-content">
            <h1>Editar Perfil</h1>
            <form method="POST">
                <div class="form-group col s6" style="padding: 0">
                    <label for="businessName">Nome Fantasia:</label>
                    <input type="text" class="form-control input-text-register" name="businessName" id="businessName" value="${establishment.businessName}" required>
                </div>
                <div class="form-group col s6" style="padding-right: 0">
                    <label for="name">Sobrenome:</label>
                    <input type="text" class="form-control input-text-register" name="name" id="name" value="${establishment.name}" required>
                </div>
                <div class="form-group col s6">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control input-text-register" name="email" id="email" value="${establishment.email}" disabled="">
                </div>
                <div class="form-group col s6">
                    <label for="secondEmail">Email Alternativo:</label>
                    <input type="email" class="form-control input-text-register" name="secondEmail" id="secondEmail" value="${establishment.secondEmail}" required>
                </div>
                <div class="form-group" style="padding-right: 0">
                    <label for="addressLocation">Logradouro:</label>
                    <input type="text" class="form-control input-text-register" name="addressLocation" id="addressLocation" value="${establishment.addressLocation}" required>
                </div>
                <div class="form-group col s6">
                    <label for="neighborhood">Bairro:</label>
                    <input type="text" class="form-control input-text-register" name="neighborhood" id="neighborhood" value="${establishment.neighborhood}" required>
                </div>
                <div class="form-group col s6">
                    <label for="numberHouse">Número:</label>
                    <input type="number" class="form-control input-text-register" name="numberHouse" id="numberHouse" value="${establishment.numberHouse}" required>
                </div>
                <div class="form-group col s6">
                    <label for="stateId">Estado:</label>
                    <select class="form-control input-text-register" name="stateId" id="stateId">
                        <option notselected></option>
                        <c:forEach items="${stateList}" var="state">
                            <option <c:if test="${stateIdEstablishment eq state.id}">selected="true"</c:if> id="stateId" value="${state.id}">${state.name}</option>                            
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col s6">
                    <label for="cityId">Municipio:</label>
                    <select class="form-control input-text-register" name="cityId" id="cityId">
                        <option notselected></option>
                        <c:forEach items="${cityList}" var="city">
                            <option <c:if test="${cityIdEstablishment eq city.id}">selected="true"</c:if> id="cityId" value="${city.id}">${city.name}</option>                            
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col s6">
                    <label for="zipCode">CEP:</label>
                    <input type="text" class="form-control input-text-register" name="zipCode" id="zipCode" value="${establishment.zipCode}" required>
                </div>
                <div class="form-group col s6">
                    <label for="cnpj">CNPJ:</label>
                    <input type="text" class="form-control input-text-register" name="cnpj" id="cnpj" value="${establishment.cnpj}" disabled="">
                </div>
                <div class="form-group">
                    <label for="telephone">Telefone:</label>
                    <input type="text" class="form-control input-text-register" name="telephone" id="telephone" value="${establishment.telephone}" required>
                </div>
                <div class="form-group col s6" style="padding: 0">
                    <label for="planStartDate">Inicio do Plano:</label>
                    <input type="text" class="form-control input-text-register" name="planStartDate" id="planStartDate" value="${establishment.planStartDate}" disabled="">
                </div>
                <div class="form-group col s6 input-text-register" style="padding-right: 0">
                    <label for="planFinalDate">Fim do Plano:</label>
                    <input type="text"  class="form-control" name="planFinalDate" id="planFinalDate" value="${establishment.planFinalDate}" disabled="">
                </div>
                <button id="btn-btn-ezmart-style" type="submit" class="btn" value="confirmar"><i class="material-icons left">border_color</i>Atualizar Dados</button>
            </form>
        </div>
    </div>
</div>