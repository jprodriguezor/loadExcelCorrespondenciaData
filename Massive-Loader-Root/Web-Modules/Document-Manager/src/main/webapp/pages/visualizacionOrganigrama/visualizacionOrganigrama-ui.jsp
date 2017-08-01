<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">

        <script type="text/javascript" src="/Document-Manager/resources/soaint-ui/js/vendor/jstree.min.js"></script>
        <link href="${pageContext.request.contextPath}/resources/soaint-ui/css/jtree/style.min.css" rel="stylesheet">
        <script type="text/javascript"src="/Document-Manager/resources/js-controllers/visualOrganigama-controller.js"></script>
		<script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>

    </jsp:attribute>

    <jsp:body>
        <p class="lead">Visualizaci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Organigrama</small>
        </p>
        <div class="row">

            <div class="form-group ">

                <div class="col-sm-6">
                    <ui:richComboBox id="cbxVersiones" label="Versiones"/>
                </div>

                <div class="version-fecha text-right"><label><strong>Fecha de Creaci&oacute;n: </strong><span><span  style="text-align:right"  name="fecha" id="fecha"  ></span> </span></label></div>
                <div class="version-numero">
                    <div id="datosOrganigramaVersion " class="text-right"><label><strong>Versi&oacute;n: </strong><span><span  style="text-align:right"  name="version" id="version" ></span></span></label></div>
                </div>

            </div>
        </div>



        <br/>

        <div class="row">
            <div class="col-sm-6">
                <ui:richInput id="search-input" label="Buscar" tabindex="2"/>
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




    </jsp:body>
</ui:composition>