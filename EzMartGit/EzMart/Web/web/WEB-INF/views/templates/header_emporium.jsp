<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <ul style="margin-top: 65px" id="main" class="dropdown-content">
        <li><a class="dropdown-content-user-header-style" href="<c:url value="quotation"/>">Cota��o</a></li>
        <li class="divider"></li>
        <li><a class="dropdown-content-user-header-style" href="<c:url value="avaliation-report"/>">Dashboard Avalia��o</a></li>
        <li class="divider"></li>
        <li><a class="dropdown-content-user-header-style" href="<c:url value="product_establishment"/>">Meus produtos</a></li>
        <li class="divider"></li>
        <li><a class="dropdown-content-user-header-style" href="<c:url value="promotion"/>">Minhas promo��es</a></li>
        <li class="divider"></li>
    </ul>
    <ul style="margin-top: 65px" id="user" class="dropdown-content">
        <li><a class="dropdown-content-user-header-style" href="<c:url value="/changePassword"/>">Alterar senha</a></li>
        <li class="divider"></li>
        <li><a class="dropdown-content-user-header-style" href="<c:url value="/profile"/>">Meu perfil</a></li>
        <li class="divider"></li>
        <li><a class="dropdown-content-user-header-style" href="<c:url value="/logout"/>">Sair</a></li>
        <li class="divider"></li>
    </ul>
    <nav style="height: 70px">
        <div class="nav-wrapper">
            <div class="container">
                <div class="row">
                    <a href="<c:url value="/home"/>" class="brand-logo">
                        <img style="height: 40px; width: 110px" src="<c:url value="/resources/img/logo_colorido.png"/>"/> 
                    </a>
                    <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">format_align_justify</i></a>
                    <ul class="right hide-on-med-and-down">
                        <li><a class="user-header-style" href="<c:url value="/home"/>"><i class="material-icons left" style="font-size: 20px">home</i>In�cio</a></li>
                        <li><a class="user-header-style" href="<c:url value="/information"/>"><i class="material-icons left" style="font-size: 20px">supervisor_account</i>Sobre</a></li>  
                        <li><a class="user-header-style" href="<c:url value="/contact"/>"><i class="material-icons left" style="font-size: 20px">contacts</i>Contato</a></li>
                        <!--<li><a class="user-header-style" href="<c:url value="#!"/>"><i class="material-icons left" style="font-size: 20px">email</i>Mensagens</a></li>-->
                        <li>
                            <a class="dropdown-button user-header-style" href="<c:url value="#!"/>" data-activates="main"><i class="material-icons left" style="font-size: 20px">settings</i>Minhas op��es</a>
                        </li>
                        <li>
                            <a class="dropdown-button user-header-style" href="#!" id="avatar" data-activates="user">
                                <i class="material-icons left" style="font-size: 20px">
                                    <img class="circle responsive" style="width:25px;height:25px; padding-top: 0px;" name="img1" src="imgProfile">
                                </i>${userLogged.name}
                            </a>
                        </li>
                    </ul>
                    <ul class="side-nav" id="mobile-demo">
                        <!--                        <li>
                                                    <a class="dropdown-button" href="<c:url value="#!"/>" data-activates="user">
                                                        <i class="material-icons left" style="font-size: 20px">
                                                            <img class="circle responsive" style="width:25px;height:25px; padding-top: 0px;" name="img1" src="imgProfile">
                                                        </i>${userLogged.name}
                                                    </a>
                                                </li>-->
                        <li><a class="user-header-style" href="<c:url value="/home"/>" class="active">In�cio</a></li>
                        <li><a class="user-header-style" href="<c:url value="/information"/>">Sobre</a></li>
                        <!--                        <li>
                                                    <a class="dropdown-button" href="<c:url value="#!"/>" data-activates="main">Minha op��es<i class="material-icons right">arrow_drop_down</i></a>
                                                </li>-->
                        <li><a class="user-header-style" href="<c:url value="/contact"/>">Contato</a></li>
                        <li><a class="user-header-style" href="#!">Mensagens</a></li>
                        <li><a class="user-header-style" href="<c:url value="/logout"/>">Sair</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>