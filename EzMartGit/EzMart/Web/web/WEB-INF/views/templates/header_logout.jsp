<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav style="height: 70px">
        <div class="nav-header" >
            <div class="container">
                <div class="row">                   
                    <a href="<c:url value="/home"/>" class="brand-logo">
                        <img style="height: 40px; width: 110px" src="<c:url value="/resources/img/logo_colorido.png"/>"/> 
                    </a>
                    <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                    <ul class="right hide-on-med-and-down">
                        <li><a class="user-header-style" href="<c:url value="/home"/>"><i class="material-icons left" style="font-size: 20px">home</i>Início</a></li>
                        <li><a class="user-header-style" href="<c:url value="#!"/>"><i class="material-icons left" style="font-size: 20px">supervisor_account</i>Sobre</a></li>
                        <li><a class="user-header-style" href="<c:url value="/contact"/>"><i class="material-icons left" style="font-size: 20px">contacts</i>Contato</a></li>
                        <li><a class="user-header-style" href="<c:url value="/login"/>"><i class="material-icons left" style="font-size: 20px">subdirectory_arrow_right</i>Entrar</a></li>
                    </ul>
                    <ul class="side-nav" id="mobile-demo">
                        <li><a href="<c:url value="/home"/>" class="active">Início</a></li>
                        <li><a href="<c:url value="#!"/>">Sobre</a></li>
                        <li><a href="<c:url value="/contact"/>">Contato</a></li>
                        <li><a href="<c:url value="/login"/>">Entrar</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>