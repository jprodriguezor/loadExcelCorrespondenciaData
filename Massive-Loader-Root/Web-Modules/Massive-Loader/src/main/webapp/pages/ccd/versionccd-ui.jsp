<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript"
                src="/Document-Manager/resources/js-controllers/versionccd-controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>
        <p class="lead">Visualizaci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Cuadro de Clasificaci&oacute;n Documental</small>
        </p>

        <form id="formVersionCCD" action="/Document-Manager/versionCcd/generateVersionCDDPDF" target="_blank"
              method="get">
            <div class="form-horizontal">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-sm-4 text-left" for="idVerCcd">Consultar versiones</label>

                            <div id="cbxVersion" class=" col-sm-8">
                                <select id="idVerCcd" name="idVerCcd" class="form-control"
                                        onchange="recargarCombosUnidadAmd(this),recargarCombos(this)"
                                        data-required="true">

                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 text-right">

                        <div class="version-fecha"><label><strong>Fecha de Creaci&oacute;n: </strong><span><span
                                style="text-align:right" name="fecha" id="fecha"></span> </span></label></div>
                        <div class="version-numero">
                            <input type="hidden" name="currentShowVersion" id="currentShowVersion"
                                   value="${model.currentIdeVersion}"/>

                            <div id="datosOrganigramaVersion"><label><strong>Versi&oacute;n: </strong><span><span
                                    style="text-align:right" name="version" id="version"></span></span></label></div>
                        </div>
                        <div>
                            <button type="submit" id="btReportPDF" class="btn btn-default  btn-sm">
                                <i class="fa fa-file-pdf-o"></i> Ver PDF
                            </button>
                            <button type="submit" id="btReportEXCEL" class="btn btn-default  btn-sm">
                                <i class="fa fa-file-pdf-o"></i> Ver EXCEL
                            </button>
                        </div>
                        <input type="hidden" name="reportType" id="reportType" value=""/>
                    </div>
                </div>
            </div>

        </form>

        <hr/>
        <div class="form-horizontal">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="idUniAmd" class="col-sm-4 control-label" style="padding-top: 0;">Unidad
                            Administrativa</label>

                        <div id="cbxLugarAdmin" class=" col-sm-8">
                            <select id="idUniAmd" name="idUniAmd" class="form-control"
                                    onchange="recargarCombosList(this)" data-required="true">
                                <option value="0"> --- Seleccione ---</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="idOfcProd" class="col-sm-4 control-label" style="padding-top: 0;">Oficina
                            Productora</label>

                        <div id="cbxUniProductora" class=" col-sm-8">
                            <select id="idOfcProd" name="idOfcProd" class="form-control"
                                    onchange="recargarCombosList(this)" data-required="true">
                                <option value="0"> --- Seleccione ---</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr/>

        <table id="tableCCD" class="text-center edt hover table-responsive table table table-bordered table-striped">
            <thead>
            <tr>
                <th>Sede Administrativa</th>
                <th>Codigo/Nombre Dependencia</th>
                <th>Codigo/Nombre Serie</th>
                <th>Codigo/Nombre SubSerie</th>
                <th>Activo</th>
            </tr>
            </thead>
            <tr>
                <td colspan="5">Cargando datos, por favor espere...</td>
            </tr>
        </table>

    </jsp:body>
</ui:composition>