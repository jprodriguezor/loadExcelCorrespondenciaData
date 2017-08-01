<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">

        <script type="text/javascript" src="/Document-Manager/resources/soaint-ui/js/vendor/jstree.min.js"></script>
        <link href="${pageContext.request.contextPath}/resources/soaint-ui/css/jtree/style.min.css" rel="stylesheet">
        <script type="text/javascript"src="/Document-Manager/resources/js-controllers/organigama-controller.js"></script>
		<script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>

    </jsp:attribute>

    <jsp:body>
        <p class="lead">Administraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Organigrama Administrativo</small>
        </p>

        <div class="row">
            <div class="col-sm-12">
                <div class="pull-left">
                    <b>Estado:</b> <input type="hidden" style="border: none;" name="statusInstrument" id="statusInstrument" data-instrument="ORGANIGRAMA" />
                    <span id="statusInstrumentLabel"></span>
                </div>
                <div class="pull-right">
                    <button id="publicarOrganigrama" class="btn btn-default btn-sm require-confirmation"><i
                            class="glyphicon glyphicon-ok-sign" disabled></i> Publicar Versi&oacute;n
                    </button>
                    <button id="setForConfiguration" data-instrument="ORGANIGRAMA" class="btn btn-default btn-sm"><i
                            class="glyphicon glyphicon-cog"></i> Configurar
                    </button>
                    <button id="cancelConfiguration" data-instrument="ORGANIGRAMA" class="btn btn-default btn-sm"><i
                            class="glyphicon glyphicon-ban-circle"></i> Cancelar
                    </button>
                </div>
            </div>
        </div>
        <br/>


        <div class="table-actions btn-toolbar">
            <button id="addItemOrg" class="btn btn-default  btn-sm" data-toggle="modal" data-target="#modalAddEditOrganigrama" disabled>
                <i class="fa fa-file"></i> Crear Nodo
            </button>

            <button id="editItemOrg" class="btn btn-default  btn-sm" disabled>
                <i class="fa fa-pencil"></i> Editar Nodo
            </button>
        </div>

        <br/>

        <div class="row">
            <div class="col-sm-6">

                <div class="pull-left">
                    <input type="text" name="search-input" id="search-input" placeholder="Buscar..." size="45px">
                </div>
            </div>
        </div>

        <br/>

        <div class="col-lg-6" style="font-size:smaller; overflow-x: auto; overflow-y: auto" id="organigrama"></div>


        <div class="row" style="min-height: 300px;">
            <div class="col-lg-6" style="font-size:smaller; overflow-x: auto; overflow-y: auto" id="organigrama"></div>
            <div class="col-lg-6" id="detailsOrganigrama" style="padding-left: 50px;">
                <div class="form-horizontal">
                    <div class="form-group" id="placeholder-wrapper">
                        <div class="col-sm-12">Seleccione un elemento para ver los detalles</div>
                    </div>
                    <div style="min-height: 200px;">
                        <div class="form-group" id="detailsParent-wrapper" style="display: none;">
                            <label for="detailsParent" class="col-sm-4 control-label">Unidad administrativa
                                (Secci&oacute;n)</label>

                            <div class="col-sm-8">
                                <div id="detailsParent" class="form-control-static">[UNIDAD_ADMINISTRATIVA]</div>
                            </div>
                        </div>
                        <div class="form-group" id="detailsChild-wrapper" style="display: none;">
                            <label for="detailsChild" class="col-sm-4 control-label">Oficina Productora
                                (Subsecci&oacute;n)</label>

                            <div class="col-sm-8">
                                <div id="detailsChild" class="form-control-static">[OFICINA_PRODUCTORA]</div>
                            </div>
                        </div>
                        <div class="form-group" id="detailsChild-status-wrapper" style="display: none;">
                            <label for="detailsChild-status" class="col-sm-4 control-label">Estado</label>

                            <div class="col-sm-8">
                                <div id="detailsChild-status" class="form-control-static">[Activado]</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="organigramaAdmin-crud.jsp" />
    </jsp:body>
</ui:composition>