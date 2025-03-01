<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>
<div class="container">
    <form action="/cartaoFidelidade" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">                        	
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="id" name="id" value="${obj.id}">
        <h2>Cart�o Fidelidade</h2>
        <div class="form-group col-xs-12">
            <label for="cliente" class="control-label col-xs-6">Cliente:
                <select name="cliente" class="form-control">
                    <c:forEach var="cliente" items="${listPessoa}">
                        <option value="${cliente.id}" ${cliente.id == obj.cliente.id?"selected":""}>${cliente}</option>
                    </c:forEach>
                </select>
            </label>
            <br><br><br><br>
            <label for="vencimento" class="control-label col-xs-3">Dia de Vencimento:
                <input type="text" name="vencimento" id="vencimento" class="form-control" value="${obj.vencimento}" required="true"/>                                   
            </label>
            <br><br><br><br>
            <label for="limite" class="control-label col-xs-3">Limite:
                <input type="text" name="limite" id="limite" class="form-control" value="${obj.limite}" required="true"/>                                   
            </label>
            <br><br><br><br>
            <label for="fatorConversao" class="control-label col-xs-3">Fator de Convers�o:
                <input type="text" name="fatorConversao" id="fatorConversao" class="form-control" value="${obj.fatorConversao}" required="true"/>                                   
            </label>
            <br><br><br><br>
            <label for="qtdPontos" class="control-label col-xs-3">Qtd de Pontos:
                <input type="text" name="qtdPontos" id="qtdPontos" class="form-control" value="${obj.qtdPontos}" required="true"/>                                   
            </label>
            <br><br><br><br>
            <label for="senha" class="control-label col-xs-3">Senha:
                <input type="password" name="senha" id="senha" class="form-control" value="${obj.senha}" required="true"/>                                   
            </label>
            <br><br><br><br>
            <button type="submit" class="btn btn-primary  btn-md">Gravar</button> 
        </div>                                                      
    </form>
</div>
<%@include file="../footer.jspf" %>