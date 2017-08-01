<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript"
                src="/Document-Manager/resources/js-controllers/versionTrd-controller.js"></script>
		<script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <p class="lead">Visualización <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Tabla de Retenci&oacute;n Documental</small>
        </p>

        <hr/>

        <form id="formVersionTRD" action="/Document-Manager/versionTrd/generateVersionTrdPDF" target="_blank"
              method="get">
            <div class="form-horizontal">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">

                            <label for="codVersionOrg" class="col-sm-4 control-label" style="padding-top: 0;">Version Organigrama </label>

                            <div id="cbxCodVersionOrg" class=" col-sm-8">
                                <select id="codVersionOrg" name="codVersionOrg" class="form-control"
                                        onchange="recargarCombosUniAmt(this)" data-required="true">
                                    <option value="0"> --- Seleccione ---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <hr/>
            <div class="form-horizontal">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">

                            <label for="codUniAmt" class="col-sm-4 control-label" style="padding-top: 0;">Unidad
                                Administrativa</label>

                            <div id="cbxCodUniAmt" class=" col-sm-8">
                                <select id="codUniAmt" name="codUniAmt" class="form-control"
                                        onchange="recargarCombosOfcProduc(this)" data-required="true">
                                    <option value="0"> --- Seleccione ---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="codOfcProd" class="col-sm-4 control-label" style="padding-top: 0;">Oficina
                                Productora</label>

                            <div id="cbxOfcProduc" class=" col-sm-8">
                                <select id="codOfcProd" name="codOfcProd" class="form-control"
                                        onchange="recargarComboVersionesTrdOfcProd(this)" data-required="true">
                                    <option value="0"> --- Seleccione ---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="form-horizontal">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-4" for="idVersionOfcProd">Consultar versiones</label>

                                <div id="cbxIdVersionOfcProduc" class="col-sm-8">
                                    <select id="idVersionOfcProd" name="idVersionOfcProd" class="form-control"
                                            onchange="definirTRD(this)" data-required="true">
                                        <option value="0"> --- Seleccione ---</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Datos de la version -->
                    <div class="col-sm-6 text-right">
                        <div class="version-fecha"><label><strong>Fecha de Creaci&oacute;n: </strong><span
                                id="currentVersionDate">-</span></label></div>
                        <div class="version-numero">
                            <input type="hidden" name="currentShowVersion" id="currentShowVersion"
                                   value=""/>

                            <div id="datosTRDVersion"><label><strong>Versi&oacute;n: </strong><span
                                    id="currentVersionNumber">-</span></label></div>
                        </div>
                        <div class="col-sm-2 col-sm-offset-8">
                            <button type="submit" id="btReportPDF" class="btn btn-default  btn-sm">
                                <i class="fa fa-file-pdf-o"></i> Ver PDF
                            </button>
                        </div>
                        <div class="col-sm-2">
                            <button type="submit" id="btReportExcel" class="btn btn-default  btn-sm">
                                <i class="fa fa-file-excel-o"></i> Ver EXCEL
                            </button>
                        </div>
                        <input type="hidden" name="reportType" id="reportType" value=""/>
                    </div>
                </div>
            </div>
            <hr/>
        </form>

        <hr id="data"/>

        <div class="panel-group hidden" id="filtroseccionsubseccion">
            <div class="row">
                <div class="form-horizontal col-lg-12 col-md-12 col-sm-12">
                    <div>
                        <label class="control-label col-md-3"
                               for="codUniAmt">UNIDAD ADMINISTRATIVA:</label>&nbsp;

                        <div class="col-md-8">
                            <label class="control-label">
                                <small id="seccionDetgroup" class="text-muted"></small>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-horizontal col-lg-12 col-md-12 col-sm-12">
                    <div>
                        <label class="control-label col-md-3 text-left"
                               for="codOfcProd">OFICINA PRODUCTORA:</label>

                        <div class="col-md-8">
                            <label class="control-label">
                                <small id="subseccionDetgroup" class="text-muted"></small>
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <table id="trdAdicionarTable" class="text-left edt hover table table table-bordered table-striped">
                <thead>
                <tr>
                    <th rowspan="2" style="width: 110px !important;">C&Oacute;DIGO</th>
                    <th rowspan="2" style="width: 280px">SERIE, SUBSERIE Y TIPO DOCUMENTAL</th>
                    <th colspan="2" style="text-align: center;">RETENCIÓN</th>
                    <th colspan="5" style="text-align: center;">DISPOSICIÓN FINAL</th>
                    <th rowspan="2" style="width: 200px;text-align: center">PROCEDIMIENTOS</th>
                </tr>
                <tr>
                    <th>ARCHIVO GESTIÓN</th>
                    <th>ARCHIVO CENTRAL</th>
                    <th>CT</th>
                    <th>E</th>
                    <th>M</th>
                    <th>S</th>
                    <th>D</th>
                </thead>
            </table>
        </div>


    </jsp:body>
</ui:composition>