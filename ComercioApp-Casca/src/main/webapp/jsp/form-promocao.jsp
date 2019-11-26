<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>
<div class="container">
    <form action="/promocao" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">                        	
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" name="action" value="${action}">
        <input type="hidden" id="id" name="id" value="${obj.id}">
        <h2>Promoção</h2>
        <div class="form-group col-xs-6">
            <label for="produto" class="control-label col-xs-10">Produto:
                <select name="produto" class="form-control">
                    <c:forEach var="produto" items="${listProduto}">
                        <option value="${produto.id}" ${produto.id == obj.produto.id?"selected":""}>${produto}</option>
                    </c:forEach>
                </select>
            </label>
            <br><br><br>
            <label for="inicio" class="control-label col-xs-6">Inicio:
                <input type="date" name="inicio" id="inicio" class="form-control" value="${obj.inicio}" required="true"/>                                   
            </label>
            <br><br><br><br>
            <label for="fim" class="control-label col-xs-6">Fim:
                <input type="date" name="fim" id="fim" class="form-control" value="${obj.fim}" required="true"/>                                   
            </label>
            <br><br><br><br>
            <label for="porcentualDesconto" class="control-label col-xs-4">Percentual Desconto
                <input type="text" name="porcentualDesconto" id="porcentualDesconto" class="form-control" value="${obj.porcentualDesconto}" required="true"/>                                   
            </label>
            <br><br><br><br><br>
            <label for="soFidelidade" class="control-label col-xs-4">Só Fidelidade
                <input type="checkbox" name="soFidelidade" id="soFidelidade" class="form-control" value="${obj.soFidelidade?'true':'false'}" ${obj.soFidelidade?'checked':''}/>                                   
            </label>
            <br><br><br><br><br>
            <button type="submit" class="btn btn-primary  btn-md">Gravar</button> 
        </div>
    </form>
</div>
<%@include file="../footer.jspf" %>