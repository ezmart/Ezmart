<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col s12">
    <div class="card">
        <div class="card-content">
            <h1>Imagem</h1>
            <img class="img-responsive" src="<c:url value="/imgProfile"/>">
            <br>
            <form method="post" enctype="multipart/form-data">
                <div class="file-field input-field">
                    <div class="btn">
                        <span>Arquivo</span>
                        <input type="file" id="imgTop" name="imgTop">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" placeholder="Carregar Foto">
                    </div>
                </div>
                <div>
                    <button id="btn-login" type="submit" class="btn btn-success"><i class="material-icons left">vpn_key</i>Salvar</button>
                </div>
            </form>
        </div>
    </div>
</div>