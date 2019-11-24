<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>
        <div class="container">
            <h2>Promocao</h2>
            <!--Search Form -->
            <form action="/promocao" method="get" id="searchPromocao" role="form">
                <input type="hidden" id="action" name="action" value="search">
                <div class="form-group col-xs-5">
                    <input type="text" name="search" id="search" class="form-control" required="true" placeholder="Digite a descrição da promocao a procurar"/>                    
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
            <form action="/promocao" method="post" id="promocaoForm" role="form" >              
                <input type="hidden" id="id" name="id">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty entities}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>#</td>
                                    <td>inicio</td>
                                    <td>fim</td>
                                    <td>percentualDesconto</td>
                                    <td>soFidelidade</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <c:forEach var="obj" items="${entities}">
                                <tr class="${id == obj.id?"info":""}">
                                    <td>
                                        <a href="/promocao?id=${obj.id}&action=searchById">${obj.id}</a>
                                    </td>                                    
                                    <td>${obj.inicio}</td>
                                    <td>${obj.fim}</td>
                                    <td>${obj.percentualDesconto}</td>
                                    <td>${obj.soFidelidade}</td>
                                    <td><a href="#" id="remove" 
                                           onclick="document.getElementById('action').value = 'remove';
                                                   document.getElementById('id').value = '${obj.id}';
                                                   document.getElementById('promocaoForm').submit();"> 
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
            <form action ="/promocao?action=new" method="POST">            
                <br></br>
                <button type="submit" class="btn btn-primary  btn-md">Novo</button> 
            </form>
        </div>
<%@include file="../footer.jspf" %>