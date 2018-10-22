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
        <div class="container">
            <div class="card">
                <div class="row" style="background: #F1F1F1;">
                    <div class="col s12 center">
                        <h4 style="color: #000000; padding: 20px">EzMart</h4>
                    </div>
                    <div class="center-align col m12 s12">
                        <h2>Quem Somos ?</h2>
                    </div>
                    <div class="center col m12 s12">
                        <p style="font-size: medium">O EzMart é uma empresa especializada em desenvolvimento de software de apoio à tomada de decisões.</p>
                        <p style="font-size: medium">Somos especialistas em apoiar os consumidores e estabelecimentos na tomada de decisão no setor supermercadista.</p>
                        <p style="font-size: medium">Possuímos qualidade no suporte aos clientes e na prestação de serviços.</p>
                        <p style="font-size: medium">Somos uma empresa madura e preparada para atender os grandes desafios.</p>
                        <p style="font-size: medium">Aliás, desafios nos motiva. Nos conduz a produzir conhecimento e a fazer diferença para os nossos clientes.</p>
                        <p style="font-size: medium">Contamos com especialistas em diversas áreas, portanto, estamos preparados para transformar conhecimento em resultados.</p>
                        <p style="font-size: medium">Somos reconhecidos pela qualidade, agilidade e estabilidade de nossa solução.</p>
                    </div>

                    <div class="col s12"  style="margin-top: 50px">
                        <div class="col s4 center">
                            <img class="circle responsive-img" width="170px" height="170px" src="<c:url value = "/resources/img/ezmart-team/marcos-paulo.jpeg"/>">
                            <p><b>Marcos Paulo</b></p>
                            <p>Graduando em Sistemas de Informação</p>
                        </div>

                        <div class="col s4 center">
                            <img class="circle responsive-img" width="170px" height="170px" src="<c:url value = "/resources/img/ezmart-team/marcos-henrique.jpeg"/>">
                            <p><b>Marcos Henrique</b></p>
                            <p>Graduando em Sistemas de Informação</p>
                        </div>


                        <div class="col s4 center">
                            <img class="circle responsive-img" width="170px" height="170px" src="<c:url value = "/resources/img/ezmart-team/diego-amaral.jpeg"/>">
                            <p style=""><b>Diego Amaral</b></p>
                            <p>Graduando em Sistemas de Informação</p>
                        </div>
                    </div>

                    <div class="col s12">
                        <div class="col s6 center">
                            <img class="circle responsive-img" width="170px" height="170px" src="<c:url value = "/resources/img/ezmart-team/jessica-pivoto.jpeg"/>">
                            <p><b>Jéssica Pivoto</b></p>
                            <p>Graduando em Sistemas de Informação</p>
                        </div>

                        <div class="col s6 center">
                            <img class="circle responsive-img" width="170px" height="170px" src="<c:url value = "/resources/img/ezmart-team/camila-costa.jpeg"/>">
                            <p><b>Camila Costa</b></p>
                            <p>Graduando em Sistemas de Informação</p>
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
