<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/motivosDoc.controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <p class="lead">Configuraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Parametrizaci&oacute;n Motivos De Creaci&oacute;n</small>
        </p>

        <div class="table-actions btn-toolbar">
            <button id="btmAgrMotivos" class="btn btn-default  btn-sm" data-toggle="modal"
                    data-target="#modalAddEditMotivoDoc">
                <i class="fa fa-plus"></i> Crear
            </button>
            <button id="btmEditMotivos" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-pencil"></i> Editar
            </button>
        </div>


        <table id="tableMotivos" class="text-center edt hover table-responsive table table table-bordered table-striped">
            <thead>
            <tr>
                <th>Nombre</th>
            </tr>
            </thead>
            <tr>
                <td colspan="5">Cargando datos, por favor espere...</td>
            </tr>
        </table>

        <jsp:include page="motivosDoc-panel.jsp"/>

    </jsp:body>
</ui:composition>