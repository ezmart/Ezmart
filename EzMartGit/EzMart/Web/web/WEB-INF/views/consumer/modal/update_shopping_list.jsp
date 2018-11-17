<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col s12">
    <div class="card">
        <div class="card-content">
            <form method="POST">
                <div class="form-group col s6" style="padding: 0">
                    <label for="name">Nome da lista:</label>
                    <input type="text" style="display: none" class="form-control input-text-register" name="type" id="type" value="UPDATE">
                    <input type="text" maxlength="15" class="form-control input-text-register" name="value" id="valueName">
                    <input type="text" class="form-control input-text-register" name="idUpdateNameShoppingList" id="idUpdateNameShoppingList" hidden="">
                </div>
                <br/>
                <br/>
                <!--<a id="btn-btn-ezmart-style" href="/newShoppingList" class="btn">Salvar nova lista</a>-->
                <button id="btn-btn-ezmart-style" name="update-name-list" type="submit" class="btn" value="confirmar"><i class="material-icons left">border_color</i>Salvar nova lista</button>
            </form>
        </div>
    </div>
    <br/>
    <br/>
    <div>
        <img style="height: 40px; width: 110px;" src="<c:url value="/resources/img/logo_colorido.png"/>"/> 
    </div>
</div>