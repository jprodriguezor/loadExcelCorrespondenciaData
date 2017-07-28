<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>
    <jsp:attribute name="header">
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/cargaMasivaDetail-controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <p class="lead">Administraci&oacute;n Carga Masiva <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Detalle Por Registro</small>
        </p>

        <div class="table-actions btn-toolbar">
            <button id="adminMasiveLoad" class="btn btn-default  btn-sm">
                <i class="glyphicon glyphicon-share-alt"></i> Regresar
            </button>
        </div>

        <br/>

        <table id="tableRegistroCargaMasiva" class="text-center edt hover table-responsive table table table-bordered table-striped">
            <thead>
            <tr>
                <th>Estado</th>
                <th>Registro</th>
                <th>Mensaje</th>
            </tr>
            </thead>
            <tr>
                <td colspan="3">Cargando datos, por favor espere...</td>
            </tr>
        </table>

    </jsp:body>
</ui:composition>