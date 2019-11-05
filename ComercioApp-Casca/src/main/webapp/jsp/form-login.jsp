<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.jspf" %>

<div class="container">
    <c:if test="${not empty message}">                
        <div class="alert alert-success">
            ${message}
        </div>
    </c:if> 
    <form class="border border-light p-5" action="/login" method="POST">

        <p class="h4 mb-4 text-center">Login</p>
        <input name="url" value="${param.url}" type="hidden"/>
        <input type="text" 
               id="defaultLoginFormLogin" 
               class="form-control mb-4" 
               placeholder="Usuário"
               name="login">

        <input type="password" 
               id="defaultLoginFormPassword" 
               class="form-control mb-4" 
               placeholder="Senha"
               name="senha">

        <div class="d-flex justify-content-between">
            <div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" 
                           id="defaultLoginFormRemember"
                           name="lembrar">
                    <label class="custom-control-label" 
                           for="defaultLoginFormRemember">Lembrar</label>
                </div>
            </div>
            <div>
                <a href="">Esqueceu a senha?</a>
            </div>
        </div>

        <button class="btn btn-info btn-block my-4" type="submit">Login</button>

        <div class="text-center">
            <p>Não tem cadastro?
                <a href="">Cadastre-se</a>
            </p>

            <p>ou faça seu login com:</p>
            <a type="button" class="light-blue-text mx-2">
                <i class="fab fa-facebook-f"></i>
            </a>
            <a type="button" class="light-blue-text mx-2">
                <i class="fab fa-twitter"></i>
            </a>
            <a type="button" class="light-blue-text mx-2">
                <i class="fab fa-linkedin-in"></i>
            </a>
            <a type="button" class="light-blue-text mx-2">
                <i class="fab fa-github"></i>
            </a>
        </div>
    </form>
</div>
<%@include file="../footer.jspf" %>
