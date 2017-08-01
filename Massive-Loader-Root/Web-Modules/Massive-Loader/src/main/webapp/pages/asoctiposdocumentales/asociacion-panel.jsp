<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<!-- Model add and edit serie -->

<div class="modal fade bs-modal-lg" id="modalAddEditAsociacion" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <form id="formSerieAsoc">

            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="modalAddEditSerieTittle">  </h4>
                </div>

                <div class="modal-body">
                    <div class="well bs-component">


                        <div class="">

                            <div class="row" style="width:100%;">
                                <div class="col-md-12">
                                    <ui:richComboBox id="cbxSeries" label="C&oacute;digo - Series documentales"/>
                                </div>

                                <div class="col-md-12">
                                    <ui:richComboBox id="cbxSubseries" label="C&oacute;digo - Subseries documentales"/>
                                </div>
                                <!-- ui:richComboBox id="cbxTipologiasDoc" label="C&oacute;digo - Tipos documentales"/-->

                                <div class="col-md-12">
                                    <div class="col-md-12">
                                        <label for="idTipDocu" class="control-label">Tipos Documentales</label>
                                        <style>
                                            .modal-body .btn{
                                                text-align: left;
                                                overflow: auto;
                                            }
                                            ul.multiselect-container.dropdown-menu{
                                                width: 100%;
                                                clear: both;
                                                position: relative;
                                                max-height: 100%;
                                                overflow-x: auto !important;
                                            }
                                        </style>
                                        <select class="form-control" id="ideTpgDoc" name="ideTpgDoc" multiple="multiple">
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button class="btn btn-default" id="guardarSerie" tabindex="11">Guardar</button>
                            <button class="btn btn-primary" data-dismiss="modal" tabindex="12" aria-hidden="true"
                                    id="cerrarModalAddEditSerie">Cerrar
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


<!-- Model detail serie -->

<div class="modal fade" id="digDetailSerie" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content ">
            <div class="modal-header ">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Detalles
                    Serie Documental </h4>
            </div>

            <div class="modal-body">


                <div class="row">


                    <div class="col-md-12">
                        <label class="control-label">Nombre:</label>
                        <span id="wrapperInputnombreDetail" class="form-group">
                            <textarea class="form-control" rows="1" id="nombreDetail" name="nombreDetail"
                                      style="width:100%;" disabled="">
                            </textarea>
                        </span>
                    </div>

                    <div class="col-md-6">
                        <label class="control-label">C&oacutedigo:</label>
                        <span id="wrapperInputcodigoDetail" class="form-group">
                            <input type="text" class="form-control" id="codigoDetail" name="codigoDetail"
                                   style="width:100%;" disabled=""/>
                        </span>
                    </div>

                    <div class="col-md-6">
                        <label class="control-label">Estado:</label>
                        <span id="wrapperInputestSerieDetail" class="form-group">
                            <input type="text" class="form-control" id="estSerieDetail" name="estSerieDetail"
                                   style="width:100%;" disabled=""/>
                        </span>
                    </div>


                    <div class="col-md-6">
                        <label class="control-label">Acto Administrativo de Creaci&oacute;n:</label>
                        <span id="wrapperInputActoAdministrativoDetail"
                              class="form-group">
                            <input type="text" class="form-control" id="actoAdministrativoDetail"
                                   name="actoAdministrativoDetail"
                                   style="width:100%;" disabled=""/>
                        </span>
                    </div>

                    <div class="col-md-6">
                        <label class="control-label">Motivo de Creaci&oacute;n:</label>
                        <span id="wrapperInputMotivoDetail" class="form-groupp">
                            <input type="text" class="form-control" id="motivoDetail" name="motivoDetail"
                                   style="width:100%;" disabled=""/>
                        </span>
                    </div>

                    <div class="col-md-6">
                        <label class="control-label">Nota de alcance:</label>
                        <span id="wrapperInputnotaDetail" class="form-group">
                            <textarea class="form-control" rows="1" id="notaDetail" name="notaDetail"
                                      style="width:100%;" disabled="">
                            </textarea>
                        </span>
                    </div>

                    <div class="col-md-6">
                        <label class="control-label">Fuente bibliogr&aacute;fica:</label>
                        <span id="wrapperInputfuenteDetail" class="form-group">
                            <textarea class="form-control" rows="1" id="fuenteDetail" name="fuenteDetail"
                                      style="width:100%;" disabled="">
                            </textarea>
                        </span>
                    </div>

                    <div class="col-md-12">
                        <label class="control-label">Caracter&iacute;sticas:</label>
                    </div>

                    <div class="col-md-12">
                        <table class="table table-striped table-hover ">

                            <tr>

                                <th><label class="control-label"> Legal</label>
                                    <label class="control-label">
                                        &nbsp;<i style="color: #999999;" id="legalDetail"
                                                 class=" glyphicon glyphicon-check"></i>
                                    </label></th>
                                <th><label class="control-label">
                                    T&eacute;cnica</label>
                                    <label class="control-label">
                                        &nbsp;<i style="color: #999999;" id="tecnicaDetail"
                                                 class=" glyphicon glyphicon-check"></i>
                                    </label></th>
                                <th><label class="control-label">
                                    Administrativa</label>
                                    <label class="control-label">
                                        &nbsp;<i style="color: #999999;" id="administrativaDetail"
                                                 class=" glyphicon glyphicon-check"></i>
                                    </label></th>
                            </tr>
                        </table>

                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>