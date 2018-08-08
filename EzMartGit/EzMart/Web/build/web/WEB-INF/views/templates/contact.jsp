<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js" lang="PT-BR">
    <c:import url="/WEB-INF/views/templates/head.jsp"></c:import>
    <body>
        <c:if test="${not empty  userLogged}">
            <c:if test="${userLogged.userType eq 'consumer'}">
                <c:import url="/WEB-INF/views/templates/header_consumer.jsp"></c:import>
            </c:if>
            <c:if test="${userLogged.userType eq 'emporium'}">
                <c:import url="/WEB-INF/views/templates/header_emporium.jsp"></c:import>
            </c:if>
        </c:if>
        <c:if test="${empty userLogged}">
            <c:import url="/WEB-INF/views/templates/header_logout.jsp"></c:import>
        </c:if>     
        <!--        <div>
                    <img class="responsive-img" src="<c:url value = "/resources/img/banners/home_cabecalho_1.jpg"/>">
                </div>-->
        <div class="container">
            <div class="card">
                <div class="row">
                    <h4 class="center-align" style="color: #0059bc; padding: 20px">Fale conosco!</h4>
                    <div class="col s12 m12">
                        <ul>
                            <li class="col l4 m6 s12">
                                <div class="card teal lighten-5">
                                    <div class="row" style="padding: 20px;">   
                                        <h5 class="col s12 center-align">Marcos Paulo Moreno</h5>
                                        <h6 class="col s12"><strong>Email:</strong> mpmoreno1990@gmail.com</h6>
                                        <h6 class="col s12"><strong>Celular:</strong> (35) 9 8413-9587</h6>
                                        <h6 class="col s12"><strong>Função:</strong> Diretor de desenvolvimento</h6>
                                    </div>
                                </div>
                            </li>
                            <li class="col l4 m6 s12">
                                <div class="card teal lighten-5">
                                    <div class="row" style="padding: 20px;">
                                        <h5 class="col s12 center-align">Jéssica Souza Pivoto</h5>
                                        <h6 class="col s12"><strong>Email:</strong> jessica_spivoto@hotmail.com</h6>
                                        <h6 class="col s12"><strong>Celular:</strong> (35) 9 9811-5396</h6>
                                        <h6 class="col s12"><strong>Função:</strong> Diretora Comercial e Gerente de projetos</h6>
                                    </div>
                                </div>
                            </li>
                            <li class="col l4 m6 s12">
                                <div class="card teal lighten-5">
                                    <div class="row" style="padding: 20px;">
                                        <h5 class="col s12 center-align">Diego Amaral</h5>
                                        <h6 class="col s12"><strong>Email:</strong> diego123amaral@gmail.com</h6>
                                        <h6 class="col s12"><strong>Celular:</strong> (35) 9 9186-4857</h6>
                                        <h6 class="col s12"><strong>Função:</strong> Comprador de Coca Cola pra Equipe</h6>
                                    </div>
                                </div>
                            </li>
                            <li class="col l4 m6 s12">
                                <div class="card teal lighten-5">
                                    <div class="row" style="padding: 20px;">
                                        <h5 class="col s12 center-align">Marcos Henrique</h5>
                                        <h6 class="col s12"><strong>Email:</strong>markjuniorazevedo@gmail.com</h6>
                                        <h6 class="col s12"><strong>Celular:</strong> (35) 9 9248-6767</h6>
                                        <h6 class="col s12"><strong>Função:</strong> Diretor de desenvolvimento</h6>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
    </body>
</html>
