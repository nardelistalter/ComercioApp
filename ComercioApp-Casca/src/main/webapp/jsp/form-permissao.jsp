<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script>     
    </head>
    <body>
        <%@include file="menu.jspf" %>
        <div class="container">
            <form action="/permissao" method="post"  role="form" data-toggle="validator" >
                <c:if test ="${empty action}">                        	
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="id" name="id" value="${obj.id}">
                <h2>Permissões</h2>
                <div class="form-group col-xs-6">
                    <label for="programa" class="control-label col-xs-6">Programa:</label>
                    <select name="programa" class="form-control">
                        <c:forEach var="programa" items="${listPrograma}">
                            <option value="${programa.id}" ${programa.id == obj.programa.id?"selected":""}>${programa}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label for="categoriaFuncional" class="control-label col-xs-6">Categoria Funcional:</label>
                    <select name="categoriaFuncional" class="form-control">
                        <c:forEach var="categoriaFuncional" items="${listCategoriaFuncional}">
                            <option value="${categoriaFuncional.id}" ${categoriaFuncional.id == obj.categoriaFuncional.id?"selected":""}>${categoriaFuncional}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label for="alterar" class="control-label col-xs-6">Alterar:
                        <input type="checkbox" name="alterar" value="${obj.alterar?'true':'false'}" ${obj.alterar?'checked':''} />
                    </label>
                    <label for="criar" class="control-label col-xs-6">Criar: 
                        <input type="checkbox" name="criar" value="${obj.criar?'true':'false'}" ${obj.criar?'checked':''} />
                    </label>
                    <label for="excluir" class="control-label col-xs-6">Excluir
                        <input type="checkbox" name="excluir" value="${obj.excluir?'true':'false'}" ${obj.excluir?'checked':''} />
                    </label>
                    <label for="consultar" class="control-label col-xs-6">Consultar:
                        <input type="checkbox" name="consultar" value="${obj.consultar?'true':'false'}" ${obj.consultar?'checked':''} />
                    </label>
                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Gravar</button> 
                </div>                                                      
            </form>
        </div>
    </body>
</html>