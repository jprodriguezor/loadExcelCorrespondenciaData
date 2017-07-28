<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/relEqui-controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>
        <p class="lead">Administraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Generar Relaci&oacute;n de Equivalencia</small>
        </p>

        <div class="table-actions btn-toolbar">

            <button id="addItemOrg" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-plus"></i> Crear Relacion
            </button>
            <button id="detailsRelacion" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-list"></i> Detalles
            </button>
            <button id="btnremove" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-trash-o"></i> Eliminar
            </button>

        </div>

        <br/>

        <table id="tableRelEqui" class="text-center edt hover table-responsive table table table-bordered table-striped">
            <thead>
            <tr>

                <th colspan="4" style="text-align: center;">Origen</th>
                <th colspan="4" style="text-align: center;">Destino</th>

            </tr>
            <tr>

                <th>Sede Administrativa</th>
                <th>Codigo/Nombre Dependencia</th>
                <th>Codigo/Nombre Serie</th>
                <th>Codigo/Nombre SubSerie</th>
                <th>Sede Administrativa</th>
                <th>Codigo/Nombre Dependencia</th>
                <th>Codigo/Nombre Serie</th>
                <th>Codigo/Nombre SubSerie</th>
            </tr>
            </thead>
            <tr>
                <td colspan="5">Cargando datos, por favor espere...</td>
            </tr>
        </table>

        <jsp:include page="relEqui-panel.jsp"/>

    </jsp:body>
</ui:composition>