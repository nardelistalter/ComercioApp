<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>
<div class="container">
    <form action="/estado" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">                        	
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="id" name="id" value="${obj.id}">
        <h2>Estado</h2>
        <div class="form-group col-xs-4">
            <label for="nome" class="control-label col-xs-4">Nome:</label>
            <input type="text" name="nome" id="nome" class="form-control" value="${obj.nome}" required="true"/>                                   

            <label for="uf" class="control-label col-xs-4">UF:</label>                   
            <input type="text" name="uf" id="uf" class="form-control" value="${obj.uf}" required="true" maxlength="2"/> 

            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Gravar</button> 
        </div>                                                      
    </form>
</div>
<%@include file="../footer.jspf" %>
