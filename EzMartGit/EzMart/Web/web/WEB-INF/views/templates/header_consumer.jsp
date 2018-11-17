<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <ul style="margin-top: 65px" id="main" class="dropdown-content">
        <li><a class="dropdown-content-user-header-style" href="<c:url value="/marketEvaluation"/>">Avaliar mercados</a></li>
        <li class="divider"></li>
        <li><a class="dropdown-content-user-header-style" href="<c:url value="/localMarkets"/>">Mercados próximos</a></li>
        <li class="divider"></li>
        <li><a id="my-list" class="dropdown-content-user-header-style" href="<c:url value="/shoppingList"/>">Minhas listas</a></li>
        <li class="divider"></li>
    </ul>
    <ul style="margin-top: 65px" id="user" class="dropdown-content">
        <li><a class="dropdown-content-user-header-style" href="<c:url value="/changePassword"/>">Alterar senha</a></li>
        <li class="divider"></li>
        <li><a class="dropdown-content-user-header-style" href="<c:url value="/profile"/>">Meu perfil</a></li>
        <li class="divider"></li>
        <li><a id="logout" class="dropdown-content-user-header-style" href="<c:url value="/logout"/>">Sair</a></li>
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
                        <li><a class="user-header-style" href="<c:url value="/home"/>"><i class="material-icons left" style="font-size: 20px">home</i>Início</a></li>
                        <li><a class="user-header-style" href="<c:url value="/information"/>"><i class="material-icons left" style="font-size: 20px">supervisor_account</i>Sobre</a></li>  
                        <li><a class="user-header-style" href="<c:url value="/contact"/>"><i class="material-icons left" style="font-size: 20px">contacts</i>Contato</a></li>
                        <!--<li><a class="user-header-style" href="<c:url value="#!"/>"><i class="material-icons left" style="font-size: 20px">email</i>Mensagens</a></li>-->
                        <li>
                            <a id="my-option" class="dropdown-button user-header-style" href="<c:url value="#!"/>" data-activates="main"><i class="material-icons left" style="font-size: 20px">settings</i>Minhas opções</a>
                        </li>
                        <li>
                            <a class="dropdown-button user-header-style" href="#!" id="avatar" data-activates="user">
                                <i class="material-icons left" style="font-size: 20px">
                                    <img class="circle responsive" style="width:25px;height:25px; padding-top: 0px;" id="imgProfile" name="imgProfile" src="imgProfile"/>
                                </i>
                                ${userLogged.name}
                            </a>
                        </li>
                    </ul>
                    <ul class="side-nav" id="mobile-demo">
                        <li><a class="user-header-style" href="<c:url value="/home"/>" class="active">Início</a></li>
                        <li><a class="user-header-style" href="<c:url value="/information"/>" class="active">Sobre</a></li>
                        <li><a class="dropdown-content-user-header-style" href="<c:url value="/profile"/>">Meu perfil</a></li>
                        <li><a class="dropdown-content-user-header-style" href="<c:url value="/changePassword"/>">Alterar senha</a></li>
                        <li><a class="dropdown-content-user-header-style" href="<c:url value="/shoppingList"/>">Minhas listas</a></li>
                        <li><a class="dropdown-content-user-header-style" href="<c:url value="/localMarkets"/>">Mercados próximos</a></li>
                        <li><a class="dropdown-content-user-header-style" href="<c:url value="/marketEvaluation"/>">Avaliar mercados</a></li>
                        <li><a class="user-header-style" href="/contact">Contato</a></li>
                        <li><a class="user-header-style" href="<c:url value="/logout"/>">Sair</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>