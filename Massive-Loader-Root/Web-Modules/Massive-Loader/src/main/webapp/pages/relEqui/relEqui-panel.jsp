<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<!-- Model add and edit serie -->

<div class="modal fade bs-modal-lg" id="modalAddEditRelEqui" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <form id="formRelEqui">

            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="modalAddEditRelEquiTittle"> xxxx </h4>
                </div>

                <div class="modal-body">
                    <div class="well bs-component">


                        <div class="">
                            <input type="hidden" id="valVersionCCD" name="valVersionCCD"/>
                            <input type="hidden" id="ideRelOrigen" name="ideRelOrigen"/>
                            <div class="row col-sm-12">
                                <div class="form-group col-sm-5">
                                    <label class="control-label col-md-12" style="text-align:left" >Versiones Origen</label>
                                    <div id="cbxVersion" class=" col-md-12">
                                        <select id="versionCcd" name="versionCcd" class="form-control" onchange="recargarVersion(this)" data-required="true">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-sm-6" >
                                <label class="control-label col-md-12" style="text-align:center" >Origen</label>

                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Unidad Administrativa</label>
                                        <div id="cbxLugarAdminOr" class=" col-md-12">
                                            <select id="ideOrgaAdminUniAmtOr" name="ideOrgaAdminUniAmtOr" class="form-control" onchange="recargarOfiProducOrigen(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Oficina Productora</label>
                                        <div id="cbxUniProductoraOr" class=" col-md-12">
                                            <select id="ideOrgaAdminOfProdOr" name="ideOrgaAdminOfProdOr" class="form-control" onchange="cargarCombosSeriesOrigen(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Serie</label>
                                        <div id="cbxSeriesOr" class=" col-md-12">
                                            <select id="ideSerieOr" name="ideSerieOr" class="form-control" onchange="recargarCombosSubSeriesOrigen(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Subserie</label>
                                        <div id="cbxSubSeriesOr" class=" col-md-12">
                                            <select id="ideSubserieOr" name="ideSubserieOr" class="form-control"  data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>


                        <div class="">
                            <div class="row col-sm-6" >
                                <label class="control-label col-md-12" style="text-align:center" >Destino</label>

                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Unidad Administrativa</label>
                                        <div id="cbxLugarAdminDes" class=" col-md-12">
                                            <select id="ideOrgaAdminUniAmtDes" name="ideOrgaAdminUniAmtDes" class="form-control" onchange="recargarOfiProducDestino(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Oficina Productora</label>
                                        <div id="cbxUniProductoraDes" class=" col-md-12">
                                            <select id="ideOrgaAdminOfProdDes" name="ideOrgaAdminOfProdDes" class="form-control" onchange="cargarCombosSeriesDestino(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Serie</label>
                                        <div id="cbxSeriesDes" class=" col-md-12">
                                            <select id="ideSerieDe" name="ideSerieDe" class="form-control" onchange="recargarCombosSubSeriesDestino(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Subserie</label>
                                        <div id="cbxSubSeriesDes" class=" col-md-12">
                                            <select id="ideSubserieDe" name="ideSubserieDe" class="form-control"  data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>

                        <div class="modal-footer">
                            <button class="btn btn-default" id="guardarRelEqui" tabindex="11">Guardar</button>
                            <button class="btn btn-primary" data-dismiss="modal" tabindex="12" aria-hidden="true"
                                    id="cerrarModalAddEditRelEqui">Cerrar
                            </button>
                        </div>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
        </form>
        <!-- /.modal-dialog -->
    </div>
</div>

<div class="modal fade" id="digDetailRelEqui" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content ">
            <div class="modal-header ">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Detalles Relacion de Equivalencia</h4>
            </div>

            <div class="modal-body">


                <div class="row col-sm-6" >
                    <label class="control-label col-md-12" style="text-align:center" >Origen</label>

                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-12" style="text-align:left" >Lugar Administrativo</label>
                            <div id="cbxLugarAdminOr" class=" col-md-12">
                                <input type="text" class="form-control" id="ideOrgaAdminUniAmtOrDetail" name="ideOrgaAdminUniAmtOrDetail"
                                       style="width:100%;" disabled=""/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-12" style="text-align:left" >Unidad Productora</label>
                            <div id="cbxUniProductoraOr" class=" col-md-12">
                                <input type="text" class="form-control" id="labelCodNomOProOrDetail" name="labelCodNomOProOrDetail"
                                       style="width:100%;" disabled=""/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-12" style="text-align:left" >Series</label>
                            <div id="cbxSeriesOr" class=" col-md-12">
                                <input type="text" class="form-control" id="labelCodNomSerieOrDetail" name="labelCodNomSerieOrDetail"
                                       style="width:100%;" disabled=""/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-12" style="text-align:left" >SubSeries</label>
                            <div id="cbxSubSeriesOr" class=" col-md-12">
                                <input type="text" class="form-control" id="labelCodNomSubSerieOrDetail" name="labelCodNomSubSerieOrDetail"
                                       style="width:100%;" disabled=""/>
                            </div>
                        </div>
                    </div>

                </div>


                <div class="row col-sm-6" >
                    <label class="control-label col-md-12" style="text-align:center" >Destino</label>

                    <div class="col-sm-12">
                        <div class="form-group">
                            <label class="control-label col-md-12" style="text-align:left" >Lugar Administrativo</label>
                            <div id="cbxLugarAdminDes" class=" col-md-12">
                                <input type="text" class="form-control" id="ideOrgaAdminUniAmtDeDetail" name="ideOrgaAdminUniAmtDeDetail"
                                       style="width:100%;" disabled=""/>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <div class="form-group">
                            <label class="control-label col-md-12" style="text-align:left" >Unidad Productora</label>
                            <div id="cbxUniProductoraDes" class=" col-md-12">
                                <input type="text" class="form-control" id="labelCodNomOProDeDetail" name="labelCodNomOProDeDetail"
                                       style="width:100%;" disabled=""/>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <div class="form-group">
                            <label class="control-label col-md-12" style="text-align:left" >Series</label>
                            <div id="cbxSeriesDes" class=" col-md-12">
                                <input type="text" class="form-control" id="labelCodNomSerieDeDetail" name="labelCodNomSerieDeDetail"
                                       style="width:100%;" disabled=""/>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <div class="form-group">
                            <label class="control-label col-md-12" style="text-align:left" >SubSeries</label>
                            <div id="cbxSubSeriesDes" class=" col-md-12">
                                <input type="text" class="form-control" id="labelCodNomSubSerieDeDetail" name="labelCodNomSubSerieDeDetail"
                                       style="width:100%;" disabled=""/>
                            </div>
                        </div>
                    </div>

                </div>
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>



