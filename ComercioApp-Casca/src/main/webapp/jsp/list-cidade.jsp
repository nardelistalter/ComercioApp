<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script>       
    </head>

    <body>    
        <%@include file="menu.jspf" %>
        <div class="container">
            <h2>Cidade</h2>
            <!--Search Form -->
            <form action="/cidade" method="get" id="searchCidade" role="form">
                <input type="hidden" id="action" name="action" value="search">
                <div class="form-group col-xs-5">
                    <input type="text" name="search" id="search" class="form-control" required="true" placeholder="Digite a descrição da cidade a procurar"/>                    
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
            <form action="/cidade" method="post" id="cidadeForm" role="form" >              
                <input type="hidden" id="id" name="id">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty entities}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>#</td>
                                    <td>Nome</td>
                                    <td>Estado</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <c:forEach var="obj" items="${entities}">
                                <tr class="${id == obj.id?"info":""}">
                                    <td>
                                        <a href="/cidade?id=${obj.id}&action=searchById">${obj.id}</a>
                                    </td>                                    
                                    <td>${obj.nome}</td>
                                    <td>${obj.estado}</td>
                                    <td><a href="#" id="remove" 
                                           onclick="document.getElementById('action').value = 'remove';
                                                   document.getElementById('id').value = '${obj.id}';
                                                   document.getElementById('cidadeForm').submit();"> 
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
            <form action ="/cidade?action=new" method="POST">            
                <br></br>
                <button type="submit" class="btn btn-primary  btn-md">Novo</button> 
            </form>
        </div>
    </body>
</html>