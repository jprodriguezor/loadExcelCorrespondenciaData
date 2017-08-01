<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/subseries-controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <p class="lead">Administraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Parametrizaci&oacute;n de Subseries Documentales</small>
        </p>

        <div class="table-actions btn-toolbar">
            <button id="masiveLoadSubserie" class="btn btn-default  btn-sm">
                <i class="fa fa-file"></i> Carga Masiva
            </button>
            <button id="btmAddSubserie" class="btn btn-default  btn-sm" data-toggle="modal"
                    data-target="#modalAddEditSubseries">
                <i class="fa fa-plus"></i> Crear Subserie
            </button>
            <button id="btmEditSubserie" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-pencil"></i> Editar
            </button>
            <button id="btmDetailsSubserie" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-list"></i> Detalles
            </button>
            <button id="btnSubserieRemove" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-trash-o"></i> Eliminar
            </button>
        </div>

        <br/>

        <table id="tableSubseries" class="text-center edt hover table-responsive table table table-bordered table-striped">
            <thead>
            <tr>
                <th>C&oacute;digo</th>
                <th>Nombre</th>
                <th>Nota de alcance</th>
                <th>Fuente</th>
                <th>Activo</th>
            </tr>
            </thead>
            <tr>
                <td colspan="5">Cargando datos, por favor espere...</td>
            </tr>
        </table>

        <jsp:include page="subseries-panel.jsp" />

    </jsp:body>
</ui:composition>