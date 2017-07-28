<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<!-- Model add and edit serie -->

<div class="modal fade bs-modal-lg" id="modalAddEditCCD" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <form id="formCuadroClasi">

            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="modalAddEditCCDTittle"> xxxx </h4>
                </div>

                <div class="modal-body">
                    <div class="well bs-component">


                        <div class="">
                            <input type="hidden" id="ideCcd" name="ideCcd"/>
                            <div class="row" style="width:100%;">

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Unidad Administrativa</label>
                                        <div id="cbxLugarAdmin" class=" col-md-12">
                                            <select id="ideorgaadminUniAmt" name="ideorgaadminUniAmt" class="form-control" onchange="recargarCombos(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Oficina Productora</label>
                                        <div id="cbxUniProductora" class=" col-md-12">
                                            <select id="ideorgaadminOfProd" name="ideorgaadminOfProd" class="form-control" onchange="recargarCombos(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Serie</label>
                                        <div id="cbxSeries" class=" col-md-12">
                                            <select id="ideSerie" name="ideSerie" class="form-control" onchange="recargarCombosSeries(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label class="control-label col-md-12" style="text-align:left" >Subserie</label>
                                        <div id="cbxSubSeries" class=" col-md-12">
                                            <select id="ideSubserie" name="ideSubserie" class="form-control" onchange="recargarCombosSeries(this)" data-required="true">
                                                <option value=""> --- Seleccione ---</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richBooleanStatus id="estCcd" label="Estado" tabindex="4"/>
                                </div>

                            </div>
                        </div>

                        <div class="modal-footer">
                            <button class="btn btn-default" id="guardarCcd" tabindex="11">Guardar</button>
                            <button class="btn btn-primary" data-dismiss="modal" tabindex="12" aria-hidden="true"
                                    id="cerrarModalAddEditCCD">Cerrar
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


