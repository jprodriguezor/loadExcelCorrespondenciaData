<%--
  Created by IntelliJ IDEA.
  User: jrodriguez
  Date: 23/09/2016
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<!-- Model add and edit tipologias documentales -->

<div class="modal fade bs-modal-lg" id="modalAddEditTipologias" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" aria-hidden="true">

    <div class="modal-dialog modal-lg">
        <form id="formTipologias">

            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="modalAddEditTipologiasTittle"> xxxx </h4>
                </div>

                <div class="modal-body">
                    <!--definicion well bs-component -->
                    <div class="well bs-component">
                        <div class="">
                            <input type="hidden" id="ideTpgDoc" name="ideTpgDoc"/>
                            <!-- inicio row-->
                            <div class="row" style="width:100%;">

                                <div class="col-sm-12">
                                    <ui:richInput id="nomTpgDoc" label="Nombre (Termino Espec&iacute;fico No.1)" tabindex=""/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richTextarea id="notAlcance" label="Nota de alcance" tabindex="4"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richTextarea id="fueBibliografica" label="Fuente bibliogr&aacute;fica"
                                                     tabindex="5"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richComboBox id="cbxTradiciones" label="Tradici&oacute;n documental"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richComboBox id="cbxSoportes" label="Soporte"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richBooleanStatus id="estTpgDoc" label="Estado" tabindex="6"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richCheckBox id="chboxCaracteristicas" label="Caracter&iacute;sticas:"/>
                                </div>
                            </div>
                            <!-- final row -->
                        </div>

                        <div class="modal-footer">
                            <button class="btn btn-default" id="guardarTipologias" tabindex="11">Guardar</button>
                            <button class="btn btn-primary" data-dismiss="modal" tabindex="12" aria-hidden="true"
                                    id="cerrarModalAddEditTipologia">Cerrar
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

<div class="modal fade" id="digDetailTipologia" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content ">
            <div class="modal-header ">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Detalles del Tipo Documental </h4>
            </div>

            <div class="modal-body">

                <div class="row">



                    <div class="col-md-12">
                        <label class="control-label">Nombre Tipo Documental:</label>
								<span id="wrapperInputnombreDetail" class="form-group">
								<textarea class="form-control" rows="2" id="nombreDetail" name="nombreDetail"
                                          style="width:100%;" disabled="">
								</textarea>
							</span>
                    </div>


                    <div class="col-md-6">
                        <label class="control-label">Tradici&oacute;n documental:</label>
								<span id="wrapperInputtradicionDocDetail" class="form-groupp">
								<input type="text" class="form-control" id="tradicionDocDetail" name="tradicionDocDetail"
                                       style="width:100%;" disabled=""/>
								</span>
                    </div>

                    <div class="col-md-6">
                        <label class="control-label">Soporte:</label>
								<span id="wrapperInputsoporteDetail" class="form-groupp">
								<input type="text" class="form-control" id="soporteDetail" name="soporteDetail"
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

                    <div class="col-md-6">
                        <label class="control-label">Estado:</label>
								<span id="wrapperInputestTpgDocDetail" class="form-group">
								<input type="text" class="form-control" id="estTpgDocDetail" name="estTpgDocDetail"
                                       style="width:100%;" disabled=""/>
								</span>
                    </div>

                    <div class="col-md-6">
                        <label class="control-label">Caracter&iacute;sticas:</label>
                    </div>

                    <div class="col-md-6">
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
