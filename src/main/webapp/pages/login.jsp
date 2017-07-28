<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>
    <jsp:attribute name="header">
        <link href="${pageContext.request.contextPath}/resources/soaint-ui/css/login.css" rel="stylesheet">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js-controllers/login-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <div class = "container">
            <div class="wrapper">
                <form class="form-signin" id="loginForm">
                    <h3 class="form-signin-heading">COLPENSIONES - INICIO DE SESION</h3>
                    <hr class="colorgraph"><br>

                    <div class="form-group">
                        <input type="text" class="form-control" id="usuario" placeholder="Usuario"
                               name="usuario"/>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" id="clave" placeholder="Clave"
                               name="clave"/>
                    </div>
                    <div class="form-group">
                        <button id="btnLogin" name="btnLogin" class="login loginmodal-submit" style="width: 100%;">CONTINUAR</button>
                    </div>
                </form>
            </div>
        </div>

    </jsp:body>
</ui:composition>