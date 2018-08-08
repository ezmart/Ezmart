<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav style="height: 75px">
        <div class="nav-header">
            <div class="container">
                <div class="row">
                    <a href="<c:url value="/home"/>" class="brand-logo">
                        <img style="height: 40px; width: 110px" src="<c:url value="/resources/img/logo_colorido.png"/>"/>  
                    </a>
                    <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                    <ul class="right hide-on-med-and-down">
                        <li><a href="<c:url value="/home"/>">In�cio</a></li>
                        <li><a href="<c:url value="#!"/>">Sobre</a></li>
                        <li><a href="<c:url value="/contact"/>">Contatos</a></li>
                    </ul>
                    <ul class="side-nav" id="mobile-demo">
                        <li><a href="<c:url value="/home"/>" class="active">In�cio</a></li>
                        <li><a href="<c:url value="#!"/>">Sobre</a></li>
                        <li><a href="<c:url value="/contact"/>">Contatos</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>