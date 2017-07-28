<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/disFinal.controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>
        <p class="lead">Configuraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Parametrizaci&oacute;n Disposici&oacute;nes Finales</small>
        </p>

        <div class="table-actions btn-toolbar">
            <button id="btmAgrDisposiciones" class="btn btn-default  btn-sm" data-toggle="modal"
                    data-target="#modalAddEditDisposiciones">
                <i class="fa fa-plus"></i> Crear
            </button>
            <button id="btmEditDisposiciones" class="btn btn-default  btn-sm" data-toggle="modal">
                <i class="fa fa-pencil"></i> Editar
            </button>
        </div>

        <table id="tableDisposiciones" class="text-center edt hover table-responsive table table table-bordered table-striped">
            <thead>
            <tr>
                <th>Nombre</th>
            </tr>
            </thead>
            <tr>
                <td colspan="5">Cargando datos, por favor espere...</td>
            </tr>
        </table>
        <jsp:include page="disFinal-panel.jsp"/>

    </jsp:body>
</ui:composition>