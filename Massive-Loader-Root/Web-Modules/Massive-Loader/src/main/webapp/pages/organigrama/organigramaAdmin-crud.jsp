<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>
<!-- Model add and edit organigrama -->

<div class="modal fade bs-modal-lg" id="modalAddEditOrganigrama" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <form id="formOrganigrama">

            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="modalAddEditOrganigraTittle"> xxxx </h4>
                </div>

                <div class="modal-body">
                    <div class="well bs-component">


                        <div class="">
                            <input type="hidden" id="ideOrgaAdmin" name="ideOrgaAdmin"/>
                            <input type="hidden" id="botonSelected" name="botonSelected"/>

                            <div class="row" style="width:100%;">

                                <div class="col-sm-12">
                                    <ui:richComboBox id="cbxPadreOrga" label="Dependencia Padre Organigrama "/>
                                </div>

                                <div class="col-sm-12">
                                    <ui:richInput id="nomOrg" label="Nombre Dependencia" tabindex="2"/>
                                </div>

                                <div class="col-sm-6">
                                    <ui:richInput id="codOrg" label="C&oacute;digo" tabindex="1"/>
                                </div>

                                <div class="col-sm-4">
                                    <ui:richBooleanStatus id="indEsActivo" label="Estado" tabindex="6"/>
                                </div>

                                <div class="col-sm-12">
                                    <ui:richTextarea id="descOrg" label="Descripci&oacute;n" tabindex="5"/>
                                </div>

                            </div>
                            <!-- final row -->
                        </div>

                        <div class="modal-footer">
                            <button class="btn btn-default" id="guardarOrga" tabindex="11">Guardar</button>
                            <button class="btn btn-primary" data-dismiss="modal" tabindex="12" aria-hidden="true"
                                    id="cerrarModalAddEditOrga">Cerrar
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
