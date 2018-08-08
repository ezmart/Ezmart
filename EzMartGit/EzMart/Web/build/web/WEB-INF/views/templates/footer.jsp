<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <div>
                    <a href="<c:url value="/home"/>" class="brand-logo">
                        <img style="height: 40px; width: 110px" src="<c:url value="/resources/img/logo_colorido.png"/>"/> 
                    </a>
                </div>
                <br/>
                <div>
                    <iframe src="https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2Ffai.ensinosuperior%2F%3Ffref%3Dts&tabs&width=340&height=154&small_header=true&adapt_container_width=true&hide_cover=false&show_facepile=true&appId" 
                            class="rdp_iframe" 
                            scrolling="no" frameborder="0" allowTransparency="true">
                    </iframe>  
                </div>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="grey-text text-lighten-3">Links</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="<c:url value="/home"/>">Home</a></li>
                    <li><a class="grey-text text-lighten-3" href="<c:url value="/contato"/>">Contato</a></li>
                    <li><a class="grey-text text-lighten-3" href="<c:url value="/sobre"/>">Sobre</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © EZMart 2018        
            <a class="#" href="#" title="Facebook">
                <i class="fa fa-facebook-official fa-2x right white-text"></i>
            </a>
            <a class="#" href="#" title="linkedin">
                <i class="fa fa-linkedin fa-2x right white-text"></i>
            </a> 
        </div>
    </div>
</footer>

