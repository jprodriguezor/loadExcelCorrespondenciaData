<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript"
                src="/Document-Manager/resources/js-controllers/trd-publicar-controller.js"></script>
		<script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <p class="lead">Administraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Generar estructura ECM TRD</small>
        </p>

        <form id="formPublicarEcmTRD">

            <hr/>
            <p class="lead"><i class="fa fa-paperclip"></i>
                Recuerde configurar todas sus tablas de retención documental, antes de generar la estructura en el
                gestor documental.
            </p>

            <div class="row">
                <div class="col-sm-12">
                    <div class="pull-right">
                        <button id="publicarTRDEcm" class="btn btn-default btn-sm require-confirmation ">
                            <i class="glyphicon glyphicon-ok-sign"></i> Publicar Estructura ECM
                        </button>
                    </div>
                </div>
            </div>
            <hr/>

        </form>
    </jsp:body>
</ui:composition>