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
            <c:import url="/WEB-INF/views/templates/header_consumer.jsp">
            </c:import>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">
                                Início
                            </a>
                            <a href="<c:url value="!#"/>" class="breadcrumb"> 
                                Mercados mais próximos
                            </a>
                        </div>
                    </div>
                </nav>
            </div> 
            <div class="container">
                <div class="row">
                    <div class="col s12">
                        <div class="col s12">
                            <div class="col s8">
                                <h3 style="color: #2196f3">Avaliação dos mercados</h3>
                            </div>
                            <div class="col s4 center">
                                <img class="circle responsive-img" style="margin-top: 10px" width="100px" height="100px" src="<c:url value = "/resources/img/evaluation/evalution_market.png"/>">
                        </div>
                        </div>
                        <div class="card" style="margin-top: 100px">
                            <div class="card-content">
                                <form method="POST">
                                    <div class="form-group col s12">
                                        <label for="establishmentId" style="color: #000000; font-size: medium"><b>Selecione o mercado</label>
                                        <select class="form-control input-text-register" name="establishmentId" id="establishmentId">
                                            <option notselected></option>
                                            <c:forEach items="${establishmentList}" var="establishment">
                                                <option id="establishmentId" value="${establishment.id}">${establishment.businessName}</option>                            
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s12" style="margin-top: 80px">
                                        <div class="form-group col s3">
                                            <label for="satisfaction" style="color: #000000; font-size: medium"><b>Grau de satisfação</b></label>
                                            <select class="form-control input-text-register" name="satisfaction" id="satisfaction">
                                                <option notselected></option>
                                                <option id="establishmentId" value="1">1</option> 
                                                <option id="establishmentId" value="2">2</option>                            
                                                <option id="establishmentId" value="3">3</option>                            
                                                <option id="establishmentId" value="4">4</option>                            
                                                <option id="establishmentId" value="5">5</option>                            
                                            </select>
                                        </div>
                                        <div class="form-group col s3">
                                            <label for="priceProduct" style="color: #000000; font-size: medium"><b>Preços dos produtos</b></label>
                                            <select class="form-control input-text-register" name="priceProduct" id="priceProduct">
                                                <option notselected></option>
                                                <option id="establishmentId" value="1">1</option> 
                                                <option id="establishmentId" value="2">2</option>                            
                                                <option id="establishmentId" value="3">3</option>                            
                                                <option id="establishmentId" value="4">4</option>                            
                                                <option id="establishmentId" value="5">5</option>                           
                                            </select>
                                        </div>
                                        <div class="form-group col s3">
                                            <label for="diversity" style="color: #000000; font-size: medium"><b>Ambiente</b></label>
                                            <select class="form-control input-text-register" name=diversity" id="diversity">
                                                <option notselected></option>
                                                <option id="establishmentId" value="1">1</option> 
                                                <option id="establishmentId" value="2">2</option>                            
                                                <option id="establishmentId" value="3">3</option>                            
                                                <option id="establishmentId" value="4">4</option>                            
                                                <option id="establishmentId" value="5">5</option>                           
                                            </select>
                                        </div>
                                        <div class="form-group col s3">
                                            <label for="employees" style="color: #000000; font-size: medium"><b>Funcionários</b></label>
                                            <select class="form-control input-text-register" name="employees" id="employees">
                                                <option notselected></option>
                                                <option id="establishmentId" value="1">1</option> 
                                                <option id="establishmentId" value="2">2</option>                            
                                                <option id="establishmentId" value="3">3</option>                            
                                                <option id="establishmentId" value="4">4</option>                            
                                                <option id="establishmentId" value="5">5</option>                           
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col s12">
                                        <div class="form-group col s3">
                                            <label for="ambience" style="color: #000000; font-size: medium"><b>Diversidade de produtos</b></label>
                                            <select class="form-control input-text-register" name="ambience" id="ambience">
                                                <option notselected></option>
                                                <option id="establishmentId" value="1">1</option> 
                                                <option id="establishmentId" value="2">2</option>                            
                                                <option id="establishmentId" value="3">3</option>                            
                                                <option id="establishmentId" value="4">4</option>                            
                                                <option id="establishmentId" value="5">5</option>                           
                                            </select>
                                        </div>
<!--                                        <div class="form-group col s3">
                                            <label for="accessibility" style="color: #000000; font-size: medium"><b>Acessibilidade</b></label>
                                            <select class="form-control input-text-register" name="accessibility" id="accessibility">
                                                <option notselected></option>
                                                <option id="establishmentId" value="1">1</option> 
                                                <option id="establishmentId" value="2">2</option>                            
                                                <option id="establishmentId" value="3">3</option>                            
                                                <option id="establishmentId" value="4">4</option>                            
                                                <option id="establishmentId" value="5">5</option>                           
                                            </select>
                                        </div>-->
                                    </div>

                                    <div style="margin-top: 80px" class="input-field col s12">
                                        <textarea maxlength="100" minlength="3" id="commentary" class="materialize-textarea" rows="20" ></textarea>
                                        <label style="color: #000000; font-size: medium" for="commentary">Opinião</label>
                                    </div>

                                    <button id="btn-btn-ezmart-style" type="submit" class="btn" value="confirmar"><i class="material-icons left">border_color</i>Avaliar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>                   
        </c:if>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
        <script src="<c:url value="/resources/js/appEZMart.js"/>"></script>
    </body>
</html>
