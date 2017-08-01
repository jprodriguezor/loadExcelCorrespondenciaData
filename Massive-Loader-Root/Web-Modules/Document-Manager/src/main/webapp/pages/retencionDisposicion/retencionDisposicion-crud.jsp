<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>

    <jsp:attribute name="header">
        <script type="text/javascript"
                src="/Document-Manager/resources/js-controllers/retencionDisposicion-controller.js"></script>
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/comun-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <p class="lead">Administraci&oacute;n <i class="fa fa-angle-double-right"></i>
            <small class="text-muted">Parametrizaci&oacute;n de Retención Documental y Disposición Final</small>
        </p>

        <hr>
        <div class="container">


            <form id="formRetencionDisposicion" class="form-horizontal">

                <div class="">

                    <input type="hidden" id="ideTabRetDoc" name="ideTabRetDoc"/>

                    <div class="row">

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="control-label col-md-12" style="text-align:left">Unidad
                                    Administrativa</label>

                                <div id="cbxCodUniAmt" class=" col-md-12">
                                    <select id="codUniAmt" name="codUniAmt" class="form-control"
                                            onchange="recargarCombosOrganigrama(this)" data-required="true">
                                        <option value=""> --- Seleccione ---</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="control-label col-md-12" style="text-align:left">Oficina
                                    Productora</label>

                                <div id="cbxOfcProduc" class=" col-md-12">
                                    <select id="codOfcProd" name="codOfcProd" class="form-control"
                                            onchange="recargarCombosOrganigrama(this)" data-required="true">
                                        <option value=""> --- Seleccione ---</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="control-label col-md-12" style="text-align:left">Serie</label>

                                <div id="cbxSerie" class=" col-md-12">
                                    <select id="idSerie" name="idSerie" class="form-control"
                                            onchange="recargarCombosSerie(this)" data-required="true">
                                        <option value=""> --- Seleccione ---</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="control-label col-md-12" style="text-align:left">Subserie</label>

                                <div id="cbxSubserie" class=" col-md-12">
                                    <select id="idSubserie" name="idSubserie" class="form-control"
                                            onchange="recargarCombosSerie(this)" data-required="true">
                                        <option value=""> --- Seleccione ---</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="control-label col-md-12" style="text-align:left">Disposición Final</label>

                                <div id="cbxDispFinal" class=" col-md-12">
                                    <select id="ideDisFinal" name="ideDisFinal" class="form-control"
                                            data-required="true">
                                        <option value=""> --- Seleccione ---</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <br/>

                    <div class="panel panel-default">
                        <div class="panel-heading">Fase de Archivo</div>
                        <div class="panel-body">
                            <br/>

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="control-label col-md-5">Retención Archivo Gestión</label>

                                        <div class="col-md-5">
                                            <input id="agTrd" name="agTrd" type="text" class="form-control"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="control-label col-md-5">Retención Archivo Central</label>

                                        <div class="col-md-5">
                                            <input id="acTrd" name="acTrd" type="text" class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br/>

                        </div>
                    </div>


                    <div class="row">
                        <div class="col-sm-12">

                            <div class="form-group">
                                <div class="col-md-12">
                                    <label class="control-label col-sd-12 "
                                           style="text-align:left">Procedimientos</label>
                                </div>
                                <div class="col-md-12">
                                    <textarea class="form-control" id="proTrd" rows="3" name="proTrd"></textarea>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

                <br/>
                <br/>

                <div class="col-sm-12">
                    <div class="pull-right form-inline">
                        <button type="submit" class="btn btn-default" id="guardarDisRet">Guardar</button>
                        <button type="submit" class="btn btn-primary" id="cancelarDisRet">Cancelar</button>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </form>
        </div>

        <jsp:include page="retencionDisposicion-panel.jsp"/>

    </jsp:body>
</ui:composition>