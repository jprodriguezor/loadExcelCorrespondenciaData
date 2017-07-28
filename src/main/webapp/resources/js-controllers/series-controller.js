/**
 * Created by jrodriguez on 02/09/2016.
 */

$(document).ready(function () {

    // ui setup - tables ----------------------

    var tableSeries = $("#tableSeries").dataTable({
        info: false,
        select: true,
        "ajax": {
            url: "/Document-Manager/series/seriesList",
            type: "GET"
        },
        "columnDefs": [
            {
                "visible": false,
                "searchable": false
            }
        ],
        "order": [[2, "asc"]],
        "columns": [
            {"data": "codSerie"},
            {"data": "nomSerie"},
            {"data": "actAdministrativo"},
            {"data": "nombreMotCreacion"},
            {"data": "estSerie"}
        ]
    });


    // ui setup ----------------------
    $("#cbxMotivos").combobox({id: 'idMotivo', data: '/Document-Manager/util/motivosCreacionList', tabindex: 6});
    $("#idMotivo").chosen();
    $("#chboxCaracteristicas").checkbox({data: '/Document-Manager/util/caracteristicasList'});

    // action setup - masive load ----------------------

    $('#masiveLoadSerie').click(function () {
        document.location.href = '/Document-Manager/series-ml'
    });

    // action setup - open modal to add series ----------------------

    $('#addSerie').click(function () {
        limpiarForm();
        $('#modalAddEditSerieTittle').html("<span class='glyphicon glyphicon-pencil ' aria-hidden='true'></span> Crear Serie Documental");
        $('#ideSerie').val("");
        $('#estSeriecheckboxActivoFalse').attr("disabled", true);
        $("#estSeriecheckboxActivo").prop('checked', 'checked');
        $('#codSerie').prop("readonly", false);
        $('#nomSerie').prop("readonly", false);

    });

    // action setup - show detail series ----------------------

    $('#detailsSerie').click(function () {
        var tableSeries = $('#tableSeries').DataTable();
        var rowselected = tableSeries.row('.selected');
        if (rowselected.length != 0) {
            var data = tableSeries.row('.selected').data();
            $('#codigoDetail').val(data.codSerie);
            $('#nombreDetail').val(data.nomSerie);
            $('#actoAdministrativoDetail').val(data.actAdministrativo);
            $('#motivoDetail').val(data.nombreMotCreacion);
            $('#notaDetail').val(data.notAlcance);
            $('#fuenteDetail').val(data.fueBibliografica);


            if ($($("#tableSeries tr.selected input").get(0)).prop('checked')) {
                $('#estSerieDetail').val("Activo");
            } else {
                $('#estSerieDetail').val("Inactivo");
            }

            data.carLegal ? $('#legalDetail').removeClass("hidden") : $('#legalDetail').addClass("hidden");
            data.carAdministrativa ? $('#administrativaDetail').removeClass("hidden") : $('#administrativaDetail').addClass("hidden");
            data.carTecnica ? $('#tecnicaDetail').removeClass("hidden") : $('#tecnicaDetail').addClass("hidden");
            $('#tableSeries').DataTable().draw();
            $('#digDetailSerie').modal('show');
        }
        else {

            showAlert('errorEdit');
        }
    });

    // action setup - edit series ----------------------

    $('#editSerie').click(function () {
        limpiarForm();

        var tableSeries = $('#tableSeries').DataTable();
        var rowselected = tableSeries.row('.selected');
        if (rowselected.length != 0) {
            var data = tableSeries.row('.selected').data();
            $('#modalAddEditSerieTittle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Editar Serie Documental");
            $('#estSeriecheckboxActivoFalse').prop("disabled", false);
            $('#ideSerie').val(data.ideSerie);
            $('#codSerie').val(data.codSerie);
            $('#codSerie').prop("readonly", true);
            $('#nomSerie').val(data.nomSerie);
            $('#nomSerie').prop("readonly", true);
            $('#notAlcance').val(data.notAlcance);
            $('#fueBibliografica').val(data.fueBibliografica);
            $('#actAdministrativo').val(data.actAdministrativo);
            $('#idMotivo').val(data.idMotivo);
            $("select#idMotivo").trigger("liszt:updated");

            if (data.estSerieValue === 1) {
                $("#estSeriecheckboxActivo").prop('checked', 'checked');
            } else {
                $("#estSeriecheckboxActivoFalse").prop('checked', 'checked');
            }
            $('#carLegal').prop('checked', data.carLegal ? true : false);
            $('#carAdministrativa').prop('checked', data.carAdministrativa ? true : false);
            $('#carTecnica').prop('checked', data.carTecnica ? true : false);
            $('#modalAddEditSerieDocumental').modal('show');

            /*  }
             }
             });*/
        } else {
            showAlert('errorEdit');
        }
    });

    // action setup - remove series ----------------------

    $('#btnremove').confirmation({
        title: "Recuerde que al eliminar la serie todas las subseries y sus relaciones se eliminaran también. ¿Esta seguro de realizar esta acción?",
        singleton: true,
        popout: true,
        placement: "left",
        btnOkLabel: "Eliminar",
        btnCancelLabel: "Cancelar",
        onConfirm: function (e) {
            e.preventDefault();
            var tableserie = $('#tableSeries').DataTable();
            var data = tableserie.row('.selected').data();
            var rowselected = tableserie.row('.selected');

            if (rowselected.length != 0) {
                sendDeleteRequest('/Document-Manager/series/removeSerie/' + data.ideSerie);
                $('#tableSeries').dataTable().fnReloadAjax();
                return false;
            } else {
                showAlert('errorEdit');
            }
            $('#tableSeries').dataTable().fnReloadAjax();
        }


    });

    //Función para  enviar mas de un campo al validar el codigo unico en el formulario
    getValidateCod = function () {
        var codSerie = $('#codSerie').val();
        var ideSerie = $('#ideSerie').val();
        return {"codSerie": codSerie, "ideSerie": ideSerie};
    };
    //Función para validar que el nombre no este repetido en bd
    getValidateNomSerie = function () {
        var nomSerie = $('#nomSerie').val();
        var ideSerie = $('#ideSerie').val();
        return {"nomSerie": nomSerie, "ideSerie": ideSerie};
    };

    // action setup - save series ----------------------

    $("#formSerieAsoc").bootstrapValidator({
        live: 'disabled',
        fields: {
            codSerie: {
                validators: {
                    notEmpty: {
                        message: "El campo c&oacute;digo es requerido"
                    },
                    remote: {
                        url: '/Document-Manager/series/validateCodSerie',
                        message: 'El c&oacute;digo ingresado ya existe',
                        data: getValidateCod
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: "El campo solo admite d&iacute;gitos"
                    },
                    stringLength: {
                        min: 1,
                        max: 100,
                        message: 'El c&oacute;digo debe ser menor que 100 caracteres'
                    }
                }
            },
            nomSerie: {
                validators: {
                    notEmpty: {
                        message: "El campo nombre es requerido"
                    }, remote: {
                        url: '/Document-Manager/series/validateNomSerie',
                        message: 'El nombre ingresado ya existe',
                        data: getValidateNomSerie
                    },
                    regexp: {
                        regexp: /^[^<>]+$/,
                        message: "El campo no admite caracteres especiales"
                    }
                }
            },
           /* fueBibliografica: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            notAlcance: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },*/
            actAdministrativo: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    },
                    regexp: {
                        regexp: /^[^<>]+$/,
                        message: "El campo no admite caracteres especiales"
                    }
                }
            },
            idMotivo: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            }
        },
        submitButtons: 'button#guardarSerie',
        submitHandler: function (validator, form) {

            if ($("#carTecnica").is(':checked') || $("#carLegal").is(':checked') || $("#carAdministrativa").is(':checked')) {
                // Code in the case checkbox is checked.
                processForm('formSerieAsoc', '/Document-Manager/series');

            } else {
                showAlert('caracteristicaSelect');
            }

        }

    });


    //Resetear el modal de Adicionar / Editar
    function limpiarForm() {
        $('#formSerieAsoc').data('bootstrapValidator').resetForm(true);
        $('#codSerie').val("");
        $('#nomSerie').val("");
        $('#actAdministrativo').html("");
        $('#fueBibliografica').html("");
        $('#carLegal').prop('checked', false);
        $('#carTecnica').prop('checked', false);
        $('#carAdministrativa').prop('checked', false);
        $('#filtroSeccion').val("");
        $('#idMotivo').val(null);
        $("select#idMotivo").trigger("liszt:updated");
        $('#filtroSubseccion').val("");
        $('#asignacionTable').DataTable().rows().remove().draw(false);
        $('#ideSerie').val("");
        $("#filtrosTabla").find("option").removeAttr("disabled");
        $("#filtrosTabla").find("option").removeAttr("style");
        $("#filtrosTabla").find("option").removeClass("hidden");
        $("#filtrosTablaSub").find("option").removeAttr("disabled");
        $("#filtrosTablaSub").find("option").removeAttr("style");
        $("#filtrosTablaSub").find("option").removeClass("hidden");
        $("#usado").html("");
    }
    ;

});

// --- always use this name
function formSucess() {

    $('#modalAddEditSerieDocumental').modal('hide');
    $('#tableSeries').dataTable().fnReloadAjax();

}

// --- always use this name
function formError() {

}