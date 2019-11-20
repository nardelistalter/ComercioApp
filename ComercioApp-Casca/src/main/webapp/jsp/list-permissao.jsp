<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>
<div class="container">
    <h2>Permissões</h2>
    <!--Search Form -->
    <form action="/permissao" method="get" id="searchPermissao" role="form">
        <input type="hidden" id="" name="action" value="search">
        <div class="form-group col-xs-5">
            <input type="text" name="search" id="search" class="form-control" placeholder="Digite a informação a procurar"/>                    
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Procurar
        </button>
        <br></br>
        <br></br>
    </form>

    <!-- Include Botton -->
    <form action ="jsp/form-permissao.jsp">            
        <c:if test="${permissao.getCriar()}">
            <button type="submit" class="btn btn-primary  btn-md">Novo Cadastro</button> 
        </c:if>
        <br></br>
    </form>

    <!-- List-->
    <c:if test="${not empty message}">                
        <div class="alert alert-success">
            ${message}
        </div>
    </c:if> 
    <form action="/permissao" method="post" id="permissaoForm" role="form" >              
        <input type="hidden" id="id" name="id">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty entities}">
                <table  class="table-striped table-xl col-xs-10">
                    <thead>
                        <tr>
                            <td>#</td>
                            <td>Programa</td>
                            <td>Categoria</td>
                            <td>Alterar</td>
                            <td>Criar</td>
                            <td>Excluir</td>
                            <td>Consultar</td>
                            <td></td>
                        </tr>
                    </thead>
                    <c:forEach var="obj" items="${entities}">
                        <tr class="${id == obj.id?"info":""}">
                            <td>
                                <c:if test="${permissao.getAlterar()}">
                                    <a href="/permissao?id=${obj.id}&action=searchById">${obj.id}</a>
                                </c:if>
                                <c:if test="${!permissao.getAlterar()}">
                                    ${obj.id}
                                </c:if>
                            </td>                                    
                            <td>${obj.programa}</td>
                            <td>${obj.categoriaFuncional}</td>
                            <td><span class="glyphicon glyphicon-${obj.alterar?"ok":"remove"}"/></td>
                            <td><span class="glyphicon glyphicon-${obj.criar?"ok":"remove"}"/></td>
                            <td><span class="glyphicon glyphicon-${obj.excluir?"ok":"remove"}"/></td>
                            <td><span class="glyphicon glyphicon-${obj.consultar?"ok":"remove"}"/></td>
                            <td>
                                <c:if test="${permissao.getExcluir()}">
                                    <a href="#" id="remove" 
                                       onclick="document.getElementById('action').value = 'remove';
                                               document.getElementById('id').value = '${obj.id}';
                                               document.getElementById('permissaoForm').submit();"> 
                                        <span class="glyphicon glyphicon-trash"/>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>               
                </table>  
            </c:when>                    
            <c:otherwise>
                <br>           
                <div class="alert alert-info">
                    Nenhum registro encontrado.
                </div>
            </c:otherwise>
        </c:choose>                        
    </form>
</div>
<%@include file="../footer.jspf" %>