<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:body>

        <p class="lead">Administraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Carga Masiva de Asociación de Tipos Documentales a Series/Subseries</small>
        </p>

        <div class="table-actions btn-toolbar">
            <button id="adminAsocLoad" class="btn btn-default  btn-sm">
                <i class="glyphicon glyphicon-share-alt"></i> Regresar
            </button>
        </div>

        <br>
        <br>

        <ui:massiveLoader uploadUrl="/Document-Manager/asociacion-ml/uploadFile"/>

    </jsp:body>
</ui:composition>