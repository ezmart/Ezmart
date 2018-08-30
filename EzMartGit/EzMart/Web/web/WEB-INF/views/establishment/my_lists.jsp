<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col s12">
    <div class="card">
        <div class="card-content">
            <h1>Minhas Lista de compras</h1>
            <a class="btn btn-primary" href="<c:url value="/produtos/novo"/>">Criar</a>
            <br/>
            <br/>
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>Nome da Lista</th>
                    <th>Finalidade</th>
                    <th></th>
                </tr>
                <c:forEach items="${produtoList}" var="produto">
                    <tr>
                        <td>${produto.id}</td>
                        <td>${produto.nome}</td>
                        <td>${produto.categoria.nome}</td>
                        
                        <td>
                            <a class="btn btn-sm btn-default" href="<c:url value="/produtos/${produto.id}/alterar"/>">Alterar</a>
                            <a class="btn btn-sm btn-danger" href="<c:url value="/produtos/${produto.id}/excluir"/>">Excluir</a>
                        </td>
                    </tr>                
                </c:forEach>
            </table>
        </div>
    </div>
</div>