<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/series-controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <p class="lead">Administraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Parametrizaci&oacute;n de Series Documentales</small>
        </p>

        <div class="table-actions btn-toolbar">
            <button id="masiveLoadSerie" class="btn btn-default  btn-sm">
                <i class="fa fa-file"></i> Carga Masiva
            </button>
            <button id="addSerie" class="btn btn-default  btn-sm" data-toggle="modal"
                    data-target="#modalAddEditSerieDocumental">
                <i class="fa fa-plus"></i> Crear Serie
            </button>
            <button id="editSerie" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-pencil"></i> Editar
            </button>
            <button id="detailsSerie" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-list"></i> Detalles
            </button>
            <button id="btnremove" class="btn btn-default  btn-sm" data-toggle="modal"
                    data-target="#modalDetTipoDocumental">
                <i class="fa fa-trash-o"></i> Eliminar
            </button>
        </div>

        <br/>

        <table id="tableSeries" class="text-center edt hover table-responsive table table table-bordered table-striped">
            <thead>
            <tr>
                <th>C&oacute;digo</th>
                <th>Nombre</th>
                <th>Acto Administrativo</th>
                <th>Motivo de Creaci&oacute;n</th>
                <th>Activo</th>
            </tr>
            </thead>
            <tr>
                <td colspan="5">Cargando datos, por favor espere...</td>
            </tr>
        </table>

        <jsp:include page="series-panel.jsp"/>

    </jsp:body>
</ui:composition>