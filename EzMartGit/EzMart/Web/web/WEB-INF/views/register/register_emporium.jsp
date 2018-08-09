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
                        <a href="#!" class="breadcrumb">cadastro estabelecimento</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="container" >
            <div class="row">
                <div class="col s12">
                    <h3 style="color: #2196f3">Cadastro Para Estabelecimento</h3>
                    <div class="card">
                        <div class="card-content">
                            <form method="POST">
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="companyName">Nome Fantasia:</label>
                                    <input type="text" class="form-control" name="companyName" id="companyName" value="${emporium.companyName}" required>
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="businessName">Razão Social:</label>
                                    <input type="text" class="form-control" name="businessName" id="businessName" value="${emporium.businessName}" required>
                                </div>
                                <div class="form-group">
                                    <label for="cnpj">CNPJ:</label>
                                    <input type="text" class="form-control" name="cnpj" id="cnpj" value="${emporium.cnpj}" required>
                                </div>
                                <div class="form-group">
                                    <label for="telefone">Email:</label>
                                    <input type="email" class="form-control" name="email" id="email" value="${emporium.email}" required>
                                </div>
                                <div class="form-group col s6" style="padding: 0">
                                    <label for="password">Senha:</label>
                                    <input type="password" class="form-control" name="password" id="password" value="${emporium.password}" required>
                                </div>
                                <div class="form-group col s6" style="padding-right: 0">
                                    <label for="addressLocation">Logradouro:</label>
                                    <input type="text" class="form-control" name="addressLocation" id="addressLocation" value="${emporium.addressLocation}" required>
                                </div>
                                <div class="form-group">
                                    <label for="numberHouse">Número:</label>
                                    <input type="number" class="form-control" name="numberHouse" id="numberHouse" value="${emporium.numberHouse}" required>
                                </div>
                                <div class="form-group">
                                    <label for="neighborhood">Bairro:</label>
                                    <input type="text" class="form-control" name="neighborhood" id="neighborhood" value="${emporium.neighborhood}" required>
                                </div>
                                <div class="form-group ">
                                    <label for="city">Cidade:</label>
                                    <input type="text" class="form-control" name="city" id="city" value="${emporium.city}" required>
                                </div>
                                <div class="form-group ">
                                    <label for="zipCode">CEP:</label>
                                    <input type="text" class="form-control" name="zipCode" id="zipCode" value="${emporium.zipCode}" required>
                                </div>
                                <div class="form-group ">
                                    <label for="telephone">Telefone:</label>
                                    <input type="text" class="form-control" name="telephone" id="telephone" value="${emporium.telephone}" required>
                                </div>
                                <button id="btn-btn-ezmart-style" type="submit" class="btn"><i class="material-icons left">border_color</i>Cadastrar</button>
                            </form>
                        </div>
                    </div>
                </div>  
            </div>                    
        </div>      
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
    </body>
</html>