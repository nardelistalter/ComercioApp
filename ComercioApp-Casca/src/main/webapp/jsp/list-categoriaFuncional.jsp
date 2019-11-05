<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script>       
    </head>

    <body>    
        <%@include file="menu.jspf" %>
        <div class="container">
            <h2>Categorias Funcionais</h2>
            <!--Search Form -->
            <form action="/categoriaFuncional" method="get" id="searchCategoriaFuncionalForm" role="form">
                <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
                <div class="form-group col-xs-5">
                    <input type="text" name="search" id="search" class="form-control" required="true" placeholder="Digite a descrição da categoria funcional a procurar"/>                    
                </div>
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Procurar
                </button>
                <br></br>
                <br></br>
            </form>

            <!-- List-->
            <c:if test="${not empty message}">                
                <div class="alert alert-success">
                    ${message}
                </div>
            </c:if> 
            <form action="/categoriaFuncional" method="post" id="categoriaFuncionalForm" role="form" >              
                <input type="hidden" id="id" name="id">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty entities}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>#</td>
                                    <td>Descrição</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <c:forEach var="obj" items="${entities}">
                                <tr class="${id == obj.id?"info":""}">
                                    <td>
                                        <a href="/categoriaFuncional?id=${obj.id}&searchAction=searchById">${obj.id}</a>
                                    </td>                                    
                                    <td>${obj.descricao}</td>
                                    <td><a href="#" id="remove" 
                                           onclick="document.getElementById('action').value = 'remove';document.getElementById('id').value = '${obj.id}';                                                    
                                                    document.getElementById('categoriaFuncionalForm').submit();"> 
                                            <span class="glyphicon glyphicon-trash"/>
                                        </a>
                                                   
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
            <form action ="jsp/form-categoriaFuncional.jsp">            
                <br></br>
                <button type="submit" class="btn btn-primary  btn-md">Novo</button> 
            </form>
        </div>
    </body>
</html>