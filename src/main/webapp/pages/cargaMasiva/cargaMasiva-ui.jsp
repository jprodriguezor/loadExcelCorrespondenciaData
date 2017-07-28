<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/cargaMasiva.controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>

    </jsp:attribute>

    <jsp:body>
        <p class="lead">Administraci&oacute;n Carga Masiva <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Resultado Carga Masiva </small>
        </p>

        <div class="table-actions btn-toolbar">
            <button id="detailsCargaMasiva" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-list"></i> Detalles
            </button>
        </div>

        <br/>

        <table id="tableCargaMasiva" class="text-center edt hover table-responsive table table table-bordered table-striped">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Fecha Creaci&oacute;n</th>
                <th>Total Registros</th>
                <th>Registros Exitosos</th>
                <th>Registros con Error</th>
                <th>Estado</th>
            </tr>
            </thead>
            <tr>
                <td colspan="5">Cargando datos, por favor espere...</td>
            </tr>
        </table>

    </jsp:body>
</ui:composition>