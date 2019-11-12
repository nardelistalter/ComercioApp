<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>
        <div class="container">
            <h2>Cidade</h2>
            <!--Search Form -->
            <form action="/cidade" method="get" id="searchCidade" role="form">
                <input type="hidden" id="action" name="action" value="search">
                <div class="form-group col-xs-5">
                    <input type="text" name="search" id="search" class="form-control" required="true" placeholder="Digite a descri��o da cidade a procurar"/>                    
                </div>
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Procurar
                </button>
                <br></br>
                <br></br>
            </form>

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
<%@include file="../footer.jspf" %>

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
<%@include file="../footer.jspf" %>