<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            <c:if test="${userLogged.userType eq 'consumer'}">
                <c:import url="/WEB-INF/views/templates/header_consumer.jsp"></c:import>
            </c:if>
            <c:if test="${userLogged.userType eq 'emporium'}">
                <c:import url="/WEB-INF/views/templates/header_emporium.jsp"></c:import>
            </c:if>
            <c:if test="${userLogged.userType eq 'admin'}">
                <c:import url="/WEB-INF/views/templates/header_admin.jsp"></c:import>
            </c:if>
            <div>
                <nav>
                    <div class="nav-wrapper">
                        <div style="margin-left: 40px" class="col s12">
                            <a href="<c:url value="/home"/>" class="breadcrumb">Início</a>
                            <a href="<c:url value="/profile"/>" class="breadcrumb">Meu Perfil</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="card" style="padding: 5px">
                <div class="row">
                    <div class="col s3" style="text-align:center;" >
                        <ul class="menu-profile" style="margin-left: 20px; align-self: center">
                            <li>
                                <div>
                                    <a>
                                        <img class="circle responsive-img" name="imgProfile" src="imgProfile">
                                    </a>
                                </div>
                            </li>
                            <li>
                                <div style="margin-top: 10px;">
                                    <a>
                                        ${userLogged.name}
                                    </a>
                                </div>
                            </li>
                        </ul>
                        <ul class="menu-profile">                                               
                            <li>
                                <div class="col s12 controls">   
                                    <a class="waves-effect waves-teal btn" id="myAddress" href="<c:url value="/editProfileConsumer"/> "><i class="material-icons left">account_box</i>Editar Perfil</a>
                                </div>
                            </li>
                            <li>
                                <div class="col s12 controls" style="margin-top: 20px">   
                                    <a class="waves-effect waves-teal btn" id="myList"  href="<c:url value="/myList"/> "><i class="material-icons left">account_box</i>Minhas Listas</a>
                                </div>
                            </li>
                            <li>
                                <div class="col s12 controls" style="margin-top: 20px">                                                    
                                    <a class="waves-effect waves-teal btn " id="myPhoto" href="<c:url value="/updatePhoto"/> "><i class="material-icons left">account_box</i>Alterar Foto</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="col s9">
                        <ul>
                            <li>
                                <c:if test="${profile.profileValue eq 'page_initial'}">
                                    <div class="card">
                                        <div class="card-content">
                                            <img class="responsive-img" src="<c:url value = "/resources/img/register/carrinho_registro.png"/>">
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${profile.profileValue eq 'page_editProfile'}">
                                    <c:import url="/WEB-INF/views/register/update/update_consumer.jsp"></c:import>
                                </c:if>
                                <c:if test="${profile.profileValue eq 'page_myList'}">
                                    <c:import url="/WEB-INF/views/list/my_lists.jsp"></c:import>
                                </c:if>
                                <c:if test="${profile.profileValue eq 'page_photo'}">
                                    <c:import url="/WEB-INF/views/profile/update_image.jsp"></c:import>
                                </c:if>
                            </li>
                        </ul>
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
