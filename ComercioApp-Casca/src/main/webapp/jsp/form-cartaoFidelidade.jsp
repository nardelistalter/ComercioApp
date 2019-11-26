<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>
<div class="container">
    <form action="/cartaoFidelidade" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">                        	
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="id" name="id" value="${obj.id}">
        <h2>Cartão Fidelidade</h2>
        <div class="form-group col-xs-12">
            <label for="funcionario" class="control-label col-xs-6">Cliente:
                <select name="funcionario" class="form-control">
                    <c:forEach var="funcionario" items="${listFuncionario}">
                        <option value="${funcionario.id}" ${funcionario.id == obj.funcionario.id?"selected":""}>${funcionario}</option>
                    </c:forEach>
                </select>
            </label>
            <br></br>
            <label for="vencimento" class="control-label col-xs-4">Dia de Vencimento:
                <input type="text" name="vencimento" id="vencimento" class="form-control" value="${obj.vencimento}" required="true"/>                                   
            </label>
            <br></br>
            <label for="limite" class="control-label col-xs-4">Limite:
                <input type="text" name="limite" id="limite" class="form-control" value="${obj.limite}" required="true"/>                                   
            </label>
            <br></br>
            <label for="fatorconversao" class="control-label col-xs-4">Fator de Conversão:
                <input type="text" name="fatorconversao" id="fatorconversao" class="form-control" value="${obj.fatorconversao}" required="true"/>                                   
            </label>
            <br></br>
            <label for="qtdpontos" class="control-label col-xs-4">Qtd de Pontos:
                <input type="text" name="qtdpontos" id="qtdpontos" class="form-control" value="${obj.qtdpontos}" required="true"/>                                   
            </label>
            <br></br>
            <label for="senha" class="control-label col-xs-4">Senha:
                <input type="password" name="senha" id="senha" class="form-control" value="${obj.senha}" required="true"/>                                   
            </label>
            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Gravar</button> 
        </div>                                                      
    </form>
</div>
<%@include file="../footer.jspf" %>