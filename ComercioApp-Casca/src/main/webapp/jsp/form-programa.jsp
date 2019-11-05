<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script>     
    </head>
    <body>
        <%@include file="menu.jspf" %>
        <div class="container">
            <form action="/programa" method="post"  role="form" data-toggle="validator" >
                <c:if test ="${empty action}">                        	
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="id" name="id" value="${obj.id}">
                <h2>Programas</h2>
                <div class="form-group col-xs-4">
                    <label for="nome" class="control-label col-xs-4">Nome:</label>
                    <input type="text" name="nome" id="nome" class="form-control" value="${obj.nome}" required="true"/>                                   

                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Gravar</button> 
                </div>                                                      
            </form>
        </div>
    </body>
</html>