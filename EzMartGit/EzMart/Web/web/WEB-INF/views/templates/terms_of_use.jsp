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
        <div>
            <nav>
                <div class="nav-wrapper">
                    <div style="margin-left: 40px" class="col s12">
                        <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                        <a href="<c:url value="/login"/>" class="breadcrumb">Autenticação</a>
                        <a href="<c:url value="/register"/>" class="breadcrumb">Cadastro</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="container">
            <div class="card">
                <div class="row" style="background: #F1F1F1;">
                    <div class="col s12">
                        <h4 style="color: #000000; padding: 20px"><b>EzMart</b></h4>
                    </div>
                    <br/>
                    <div class="center col m12 s12">
                        <p style="font-size: medium">Com base na Lei de Softwares nº 9.609/98, este é um Termo de
                            Responsabilidade de Uso de Software entre o utilizador final do
                            software e o os desenvolvedores do EzMart.</p>
                        <p style="font-size: xx-large"><b>Por favor, leia atentamente este termo de uso.</b></p>
                        <p style="font-size: medium">Caso não aceite o Termo de Responsabilidade nos termos e
                            condições descritas, o cliente não poderá continuar a utilizar os
                            serviços providos pelo EzMart.</p>
                        <p style="font-size: medium">O EzMart é um software brasileiro, de propriedade da empresa
                            EzMart Ltda, com sede em Minas Gerais, registrado na FAI –
                            Centro de Ensino Superior em Gestão, Tecnologia e Educação. O
                            software é licenciado e não vendido. O software especificado neste
                            Termo de Responsabilidade é e continuará a ser propriedade do
                            Autor. O mesmo software está sujeito aos direitos de Autor e como
                            tal está protegido em todas as extensões permitidas por lei.</p>
                        <p style="font-size: medium">Em nenhuma hipótese o Autor conhecido como EzMart Ltda,
                            responsável pelo desenvolvimento do software EzMart, será
                            responsabilizado, por danos causados ou por perda de lucros,
                            incluindo, sem qualquer limitação, danos especiais causados por
                            parada do trabalho, perda de dados causados pela utilização e uso
                            inadequado deste software, ainda que o Autor tenha sido avisado
                            acerca desta possibilidade ou de tais danos ou perdas.</p>
                        <p style="font-size: medium">Ao aceitar este Termo de Responsabilidade, o cliente aceita que a
                            responsabilidade do Autor pelos danos causados pela utilização ou
                            pelo uso inadequado deste software fica limitado nesse item.  Além
                            disso, ao aceitar os termos de uso, o usuário concorda que todas
                            as informações fornecidas (exceto senhas) poderão ser utilizadas
                            pelo Autor, de forma que não prejudique o usuário.</p>
                        <p style="font-size: medium">O Autor se reserva o direito de alterar os termos de
                            responsabilidade do uso de software a qualquer momento sem
                            prévio aviso.</p>
                    </div>
                </div>
            </div>
        </div>
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
        <script src="<c:url value="/resources/js/materialize.min.js"/>"></script>
    </body>
</html>
