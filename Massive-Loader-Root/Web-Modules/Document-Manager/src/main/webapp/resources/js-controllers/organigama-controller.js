/**
 * Created by jrodriguez on 24/10/2016.
 */
var current_item = undefined;

$(document).ready(function () {


    // cargar estado del organigrama
    $.ajax({
        url: "/Document-Manager/organigrama/getEstadotOrganigrama",
        type: "GET",
        async: true,
        contentType: "application/json",

        success: function (data, textStatus, xhr) {
            $('#statusInstrumentLabel').text(data.label);
            $("#statusInstrument").val(data.value);

            var enableButtons = true;
            if (data.value == "1") {
                enableButtons = false;
            }

            $("#addItemOrg").prop('disabled', enableButtons);
            $("#editItemOrg").prop('disabled', enableButtons);
            $("#publicarOrganigrama").prop('disabled', enableButtons);

        },
        error: function (jqXHR, textStatus, errorThrown) {
            var data = jqXHR.responseJSON;
            addDanger("Internal system error");
        }
    });

    configurarOrganigrama();


    $('#search-input').keyup(function (e) {
        $("#organigrama").jstree("search", $(this).val());
    });


    $('#addItemOrg').click(function () {
        limpiarForm();
        cargarDependenciasPadres();
        $('#modalAddEditOrganigraTittle').html("<span class='glyphicon glyphicon-pencil ' aria-hidden='true'></span> Crear Nodo Org&aacute;nico Funcional ");
        $('#ideOrgaAdmin').val("");
        $('#ideOrgaAdminPadre').val("");


        $('#ideOrgaAdminPadre').prop('disabled', false);
        $('#indEsActivocheckboxActivoFalse').attr("disabled", true);
        $('#indEsActivocheckboxActivo').prop('checked', 'checked');
        $('#codOrg').prop("readonly", false);

        $('#ideOrgaAdminPadre option:not(:selected)').prop('disabled', false);
        $('#ideOrgaAdminPadre').trigger("liszt:updated");
        $('#botonSelected').val("Crear");



    });

    //Función para  enviar mas de un campo al validar el codigo unico en el formulario
    getValidateCodOrganigrama = function () {
        var codOrg = $('#codOrg').val();
        var ideOrgaAdmin = $('#ideOrgaAdmin').val();
        return {"codOrg": codOrg, "ideOrgaAdmin": ideOrgaAdmin};
    };
    //Función para validar que el nombre no este repetido en bd
    getValidateNomOrganigrama = function () {
        var nomOrg = $('#nomOrg').val();
        var ideOrgaAdmin = $('#ideOrgaAdmin').val();
        return {"nomOrg": nomOrg, "ideOrgaAdmin": ideOrgaAdmin};
    };


    // action setup - save series ----------------------

    $("#formOrganigrama").bootstrapValidator({
        live: 'disabled',
        fields: {
            ideOrgaAdminPadre: {
                validators: {
                    notEmpty: {
                        message: "El campo dependencia padre organigrama es requerido"
                    }
                }
            },
            codOrg: {
                validators: {
                    notEmpty: {
                        message: "El campo c&oacute;digo es requerido"
                    },
                    remote: {
                        url: '/Document-Manager/organigrama/validateCodOrganigrama',
                        message: 'El c&oacute;digo ingresado ya existe',
                        data: getValidateCodOrganigrama
                    },
                  /*  regexp: {
                        regexp: /^[0-9]+$/,
                        message: "El campo solo admite d&iacute;gitos"
                    },*/
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: 'El c&oacute;digo debe ser menor que 20 caracteres'
                    }
                }
            },
            nomOrg: {
                validators: {
                    notEmpty: {
                        message: "El campo nombre es requerido"
                    }, remote: {
                        url: '/Document-Manager/organigrama/validateNomOrganigrama',
                        message: 'El nombre ingresado ya existe',
                        data: getValidateNomOrganigrama
                    }, stringLength: {
                        min: 1,
                        max: 200,
                        message: 'El nombre dependencia debe ser menor que 200 caracteres'
                    }
                }
            },
            descOrg: {
                validators: {
                    notEmpty: {
                        message: "El campo descripción es requerido"
                    }, stringLength: {
                        min: 1,
                        max: 200,
                        message: 'La descripción debe ser menor que 200 caracteres'
                    }
                }
            }

        },
        submitButtons: 'button#guardarOrga',
        submitHandler: function (validator, form) {
            processForm('formOrganigrama', '/Document-Manager/organigrama');
        }
    });

    $('#editItemOrg').click(function (e) {

        cargarDependenciasPadres();

        if (current_item == undefined) {
            showAlert('errorEdit');
        }
        else {
            $('#modalAddEditOrganigraTittle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Editar Nodo Org&aacute;nico Funcional");
            //Usado para cargar el valor que tiene la lista organigrama padre

            if (current_item.idePadre === "#") {
                $('#ideOrgaAdminPadre').val("N/A");
            } else {
                $('#ideOrgaAdminPadre').val(current_item.idePadre + '-' + current_item.nivelOrgPadre);
            }
            $('#ideOrgaAdminPadre option:not(:selected)').prop('disabled', true);
            $('#ideOrgaAdminPadre').trigger("liszt:updated");


            $('#indEsActivocheckboxActivoFalse').prop("disabled", false);
            $('#ideOrgaAdmin').val(current_item.ideOrganigrama);
            $('#codOrg').prop("readonly", true);
            $('#codOrg').val(current_item.codOrganigrama);
            $('#nomOrg').val(current_item.nomOrganigrama);
            $('#descOrg').val(current_item.descOrganigrama);

            $('#botonSelected').val("Editar");

            if (current_item.estOrganigrama === '1') {
                $("#indEsActivocheckboxActivo").prop('checked', 'checked');
            } else {
                $("#indEsActivocheckboxActivoFalse").prop('checked', 'checked');
            }
            $('#modalAddEditOrganigrama').modal('show');
        }

    });

    //Resetear el modal de Adicionar / Editar
    function limpiarForm() {
        $('#formOrganigrama').data('bootstrapValidator').resetForm(true);
        $('#ideOrgaAdmin').val("");
        $('#codOrg').val("");
        $('#nomOrg').val("");
        $('#descOrg').val("");
        $('#ideOrgaAdminPadre').val("");
    };

    $('#publicarOrganigrama').confirmation({
        title: "¿Está seguro de realizar esta acción?",
        singleton: true,
        popout: true,
        placement: "left",
        btnOkLabel: "Publicar versión",
        btnCancelLabel: "Cancelar",
        onConfirm: function (e) {
            e.preventDefault();
            $.ajax({
                url: "/Document-Manager/organigrama/publicarVersionOrganigrama/",
                type: "GET",
                async: true,
                contentType: "application/json",
                success: function (data, textStatus, xhr) {
                    if (data.success === true) {
                        $("#statusInstrumentLabel").text("PUBLICADO");
                        $("#statusInstrument").val("2");
                        addSuccess(data.message);
                        $("#addItemOrg").prop('disabled', true);
                        $("#editItemOrg").prop('disabled', true);
                        $("#publicarOrganigrama").prop('disabled', true);
                    } else {
                        addDanger(data.message);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    var data = jqXHR.responseJSON;
                    addDanger("Internal system error");
                }
            });
        }
    });
});

// --- always use this name
function formSucess() {

    $('#modalAddEditOrganigrama').modal('hide');
    $("#organigrama").jstree("destroy");
    configurarOrganigrama();
    $('#detailsOrganigrama').hide();
}

// --- always use this name
function formError() {

}

function cargarDependenciasPadres() {

    try {
        $("#cbxPadreOrga").empty();
        $("#cbxPadreOrga").combobox("destroy");
    } catch (err) {
    }

    $("#cbxPadreOrga").combobox({
        id: 'ideOrgaAdminPadre',
        data: '/Document-Manager/organigrama/listOrganigrama',
        tabindex: 6
    });

    $("#ideOrgaAdminPadre").chosen().change(function (e) {
        $("#formOrganigrama").data('bootstrapValidator').updateStatus('Dependencia Padre Organigrama', 'NOT_VALIDATED');
    });
}


function configurarOrganigrama() {

    $.ajax({
            url: "/Document-Manager/organigrama/listOrganigramaTree",
        type: "GET",
        async: true,
        contentType: "application/json",

        success: function (data, textStatus, xhr) {
            $('#organigrama').jstree(data).bind("select_node.jstree", function (e, data) {
                $('#placeholder-wrapper').html("<h4><span class='glyphicon glyphicon-folder-open' aria-hidden='true'></span> Estructura Org&aacute;nico Funcional  </h4>");

                current_node = data.node.original;

                var dataOrganigrama = {
                    ideOrganigrama: current_node.id,
                    codOrganigrama: current_node.code,
                    nomOrganigrama: current_node.text,
                    estOrganigrama: current_node.status,
                    descOrganigrama: current_node.desc,
                    nivelOrg: current_node.level,
                    idePadre: current_node.parent,
                    nomOrgPadre: current_node.textParent,
                    nivelOrgPadre: current_node.levelParent
                };


                $('#detailsChild').html(dataOrganigrama.nomOrganigrama);
                $('#detailsChild-status').html(dataOrganigrama.estOrganigrama == '1' ? "Activo" : "Inactivo");
                if (dataOrganigrama.idePadre == '#') {
                    $('label[for="detailsChild"]').html("Entidad (Fondo)");
                    $('#detailsParent-wrapper').hide();
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }
                else if (dataOrganigrama.idePadre != '#' && dataOrganigrama.nivelOrg == '1') {
                    $('label[for="detailsChild"]').html("Sede Administrativa (Subfondo)");
                    $('#detailsParent-wrapper').show();
                    $('label[for="detailsParent"]').html("Entidad (Fondo)");
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }
                else if (dataOrganigrama.idePadre != '#' && dataOrganigrama.nivelOrg == '2') {
                    $('label[for="detailsChild"]').html("Oficina Productora (Subsección)");
                    $('#detailsParent-wrapper').show();
                    $('label[for="detailsParent"]').html("Sede Administrativa (Subfondo)");
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }
                else if (dataOrganigrama.idePadre != '#' && dataOrganigrama.nivelOrg == '2') {
                    $('label[for="detailsChild"]').html("Oficina Productora (Subsección)");
                    $('#detailsParent-wrapper').show();
                    $('label[for="detailsParent"]').html("Sede Administrativa (Subfondo)");
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }
                else {
                    $('label[for="detailsChild"]').html("Oficina Productora (Subsección)");
                    $('#detailsParent-wrapper').show();
                    $('label[for="detailsParent"]').html("Unidad Administrativa (Sección)");
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }

                if (dataOrganigrama.idePadre != '#') {
                    $('#detailsParent').html(dataOrganigrama.nomOrgPadre);
                }
                else {
                    $('#detailsParent').html("-N/A-");
                }

                $('#detailsChild-status-wrapper').show();
                current_item = dataOrganigrama;
                $('#detailsOrganigrama').show();

            })

        },
        error: function (jqXHR, textStatus, errorThrown) {
            var data = jqXHR.responseJSON;
            addDanger("Internal system error");
        }
    });
}
