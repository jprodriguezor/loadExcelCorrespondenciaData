<%--
  Created by IntelliJ IDEA.
  User: jrodriguez
  Date: 20/09/2016
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<!-- Model add and edit serie -->

<div class="modal fade bs-modal-lg" id="modalAddEditSubseries" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" aria-hidden="true">

    <div class="modal-dialog modal-lg">
        <form id="formSubserie">

            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="modalAddEditSubserieTittle"> xxxx </h4>
                </div>

                <div class="modal-body">
                    <!--definicion well bs-component -->
                    <div class="well bs-component">
                        <div class="">
                            <input type="hidden" id="ideSubserie" name="ideSubserie"/>
                            <!-- inicio row-->
                            <div class="row" style="width:100%;">

                                <div class="col-sm-12">
                                        <ui:richComboBox id="cbxSeries" label="C&oacute;digo - Series documentales"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richInput id="codSubserie" label="C&oacute;digo" tabindex="1"/>
                                </div>
                                <div class="col-sm-6">
                                    <ui:richInput id="nomSubserie" label="Nombre Subserie" tabindex="2"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richInput id="actAdministrativo" label="Acto administrativo de creaci&oacute;n"
                                                  tabindex="3"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richComboBox id="cbxMotivos" label="Motivo de creaci&oacute;n"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richTextarea id="notAlcance" label="Nota de alcance" tabindex="4"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richTextarea id="fueBibliografica" label="Fuente bibliogr&aacute;fica"
                                                     tabindex="5"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richBooleanStatus id="estSubserie" label="Estado" tabindex="6"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richCheckBox id="chboxCaracteristicas" label="Caracter&iacute;sticas:"/>
                                </div>
                            </div>
                            <!-- final row -->
                        </div>

                        <div class="modal-footer">
                            <button class="btn btn-default" id="guardarSubserie" tabindex="11">Guardar</button>
                            <button class="btn btn-primary" data-dismiss="modal" tabindex="12" aria-hidden="true"
                                    id="cerrarModalAddEditSubserie">Cerrar
                            </button>
                        </div>
                    </div>
                    <!-- final well bs-component -->
                </div>
            </div>
        </form>
    </div>
</div>


<!-- Model detail Subserie -->

<div class="modal fade" id="digDetailSubserie" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content ">
            <div class="modal-header ">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Detalles
                    Subserie Documental </h4>
            </div>

            <div class="modal-body">

                <div class="row">

                    <div class="col-md-12">
                        <label class="control-label">C&oacutedigo Serie:</label>
								<span id="wrapperInputCodigoSerieDetail" class="form-group">
								<textarea class="form-control" rows="1" id="idSerieDetail" name="idSerieDetail"
                                          style="width:100%;" disabled="">
								</textarea>
							</span>
                    </div>


                    <div class="col-md-12">
                        <label class="control-label">Nombre Subserie:</label>
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
								<span id="wrapperInputestSubserieDetail" class="form-group">
								<input type="text" class="form-control" id="estSubserieDetail" name="estSubserieDetail"
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
                <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>

