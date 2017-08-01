/**
 * Created by dsanchez on 02/09/2016.
 */

$(document).ready(function () {


    // ui setup - tables ----------------------
    $('#estado').val("Publicado");
    var tableCCD = $("#tableCCD").dataTable({
        info: false,
        select: true,
        "ajax": {
            url: "/Document-Manager/ccd/ccdList",
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
            {"data": "nomOrgUniAmt"},
            {"data": "labelCodNomDependencia"},
            {"data": "labelCodNomSerie"},
            {"data": "labelCodNomSubSerie"},
            {"data": "estCcd"}
        ]

    });

    // cargar estado del organigrama Ccd
    $.ajax({
        url: "/Document-Manager/ccd/getEstadoCcd",
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
            $("#publicVersionCcd").prop('disabled', enableButtons);



        },
        error: function (jqXHR, textStatus, errorThrown) {
            var data = jqXHR.responseJSON;
            addDanger("Internal system error");
        }
    });

    comboboxJs('ideorgaadminUniAmt', '/Document-Manager/util/unidadAdministrativoRetencionList', true);
    comboboxJs('ideSerie', '/Document-Manager/util/seriesList', true);

    //Agregar
    $('#addItemOrg').click(function () {
        $('#ideorgaadminUniAmt').prop("disabled", false);
        $('#ideorgaadminOfProd').prop("disabled", false);
        $('#ideSerie').prop("disabled", false);
        $('#ideSubserie').prop("disabled", false);
        limpiarForm();
        $('#modalAddEditCCDTittle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Crear Cuadro de Clasificacion Documental");
        $('#ideorgaadminUniAmt').prop("disabled", false);
        $('#ideorgaadminOfProd').prop("disabled", false);
        $('#ideSerie').prop("disabled", false);
        $('#ideSubserie').prop("disabled", false);



        $('#modalAddEditCCD').modal('show');

        $('#estCcdcheckboxActivoFalse').attr("disabled", true);
        $("#estCcdcheckboxActivo").prop('checked', 'checked');



    });

    //Editar
    $('#editItemOrg').click(function () {

        limpiarForm();

        var tableCCD = $('#tableCCD').DataTable();
        var rowselected = tableCCD.row('.selected');
        if (rowselected.length != 0) {
            //Usado para cargar el valor que tiene la lista de series
            var data = tableCCD.row('.selected').data();
            $('#ideorgaadminUniAmt').prop("disabled", true);
            $('#ideorgaadminOfProd').prop("disabled", true);
            $('#ideSerie').prop("disabled", true);
            $('#ideSubserie').prop("disabled", true);

            $('#ideCcd').val(rowselected.data().ideCcd);
            $('#ideorgaadminUniAmt').val(rowselected.data().ideorgaadminUniAmt +"-"+ rowselected.data().codOrgUniAmt );

            $('#ideorgaadminOfProd').find('option:not(:first)').remove();

            comboboxJsNotAsync('ideorgaadminOfProd', '/Document-Manager/util/oficinaProductoraRetencionList/' + rowselected.data().ideorgaadminUniAmt, true);
            $('#ideorgaadminOfProd').val(rowselected.data().ideorgaadminOfProd +"-"+ rowselected.data().codOrgOfProd);
            $("select#ideorgaadminUniAmt").trigger("liszt:updated");
            $("select#ideorgaadminOfProd").trigger("liszt:updated");

            $('#ideSerie').val(rowselected.data().ideSerie);
            $('#ideSubserie').find('option:not(:first)').remove();
            comboboxJsNotAsync('ideSubserie', '/Document-Manager/util/subSeriesByIdSerieList/' + $('#ideSerie').val(), true);
            $('#ideSubserie').val(rowselected.data().ideSubserie);
            $("select#ideSerie").trigger("liszt:updated");
            $("select#ideSubserie").trigger("liszt:updated");
            $('#modalAddEditCCDTittle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Editar Cuadro de Clasificacion Documental");


            $('#modalAddEditCCD').modal('show');

            $('#estCcdcheckboxActivoFalse').prop("disabled", false);

            $('#ideorgaadminUniAmt').prop("disabled", true);
            $('#ideorgaadminOfProd').prop("disabled", true);
            $('#ideSerie').prop("disabled", true);
            $('#ideSubserie').prop("disabled", true);


            if (data.estCcdValue === 1) {
                $("#estCcdcheckboxActivo").prop('checked', 'checked');
            } else {
                $("#estCcdcheckboxActivoFalse").prop('checked', 'checked');
            }

        } else {
            showAlert('errorEdit');
        }

    });

    //Función para validar que el nombre no este repetido en bd
    getValidateTabRecDoc = function () {
        var ideSerie = $('#ideSerie').val();
        var ideSubserie = $('#ideSubserie').val();
        var ideorgaadminOfProd = $('#ideorgaadminOfProd').val();
        var ideorgaadminUniAmt = $('#ideorgaadminUniAmt').val();
        return {"ideSerie": ideSerie,
            "ideSubserie": ideSubserie,
            "ideorgaadminOfProd": ideorgaadminOfProd,
            "ideorgaadminUniAmt": ideorgaadminUniAmt};
    };


    // guardar
    //Validaciónes del formulario
    // Bootstrap Validator Form
    $("#formCuadroClasi").bootstrapValidator({
        live: 'disabled',
        fields: {
            ideorgaadminUniAmt: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            ideorgaadminOfProd: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            ideSerie: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            estCcd: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }

                }
            }
        },
        submitButtons: 'button#guardarCcd',
        submitHandler: function (validator, form) {
            processForm('formCuadroClasi', '/Document-Manager/ccd');
            $('#tableCCD').dataTable().fnReloadAjax();
        }

    });

    $('#publicarOrganigrama').confirmation({
        title: "¿Está seguro de realizar esta acción?",
        singleton: true,
        popout: true,
        placement: "left",
        btnOkLabel: "Publicar Versión",
        btnCancelLabel: "Cancelar",
        onConfirm: function (e) {
            e.preventDefault();
            $.ajax({
                url: "/Document-Manager/ccd/publicarVersionCcd/",
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
        //onConfirm: function (e) {
        //         sendPostRequest("/Document-Manager/ccd/publicarVersionCcd", null, null);
        //  sendPostRequest("/Document-Manager/publicarVersion", null, null);

    });

    //Configuración
    $('#configCcd').click(function () {
        $('#estado').val("Configuración");
    });




    $('#cancelCcd').click(function () {
        $('#estado').val("Publicado");

    });


    function limpiarForm() {
        $('#formCuadroClasi').data('bootstrapValidator').resetForm(true);
        $('#ideorgaadminUniAmt').val("");
        $('#ideorgaadminOfProd').val("");
        $('#ideSerie').val("");
        $('#ideSubserie').val("");
        $("select#ideSerie").trigger("liszt:updated");
        $("select#ideSubserie").trigger("liszt:updated");
        $("select#ideorgaadminUniAmt").trigger("liszt:updated");
        $("select#ideorgaadminOfProd").trigger("liszt:updated");

    }
    ;

});

// --- always use this name
function formSucess() {
    $('#modalAddEditCCD').modal('hide');
    $('#tableCCD').dataTable().fnReloadAjax();
}

// --- always use this name
function formError() {

}

function consultarTabRecDoc() {
    sendPostRequest("/Document-Manager/ccd/", null, null);
}

function recargarCombos(response) {
    var idvalor = response.value.split("-");
    var valor = idvalor[0];

    var id = response.id;


    if ( id == 'ideorgaadminUniAmt' && valor == 'TODAS') {
        $('#ideorgaadminOfProd').find('option:not(:first)').remove();
        $("select#ideorgaadminOfProd").empty().append($('<option></option>').attr('value', 'TODAS').text('- N/A -')).prop('disabled', true);
        $("select#ideorgaadminOfProd").trigger("liszt:updated");
    } else if (valor != ""  &&  valor != 'TODAS' && id == 'ideorgaadminUniAmt') {
        $('#ideorgaadminOfProd').find('option').remove();
        $('#ideorgaadminOfProd').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#ideorgaadminOfProd').prop('disabled', false);
        comboboxJs('ideorgaadminOfProd', '/Document-Manager/util/oficinaProductoraRetencionList/' + valor, true);
    }

}



function recargarCombosSeries(response) {
    var valor = response.value;
    var id = response.id;

    $("select#ideSerie").trigger("liszt:updated");
    $("select#ideSubserie").trigger("liszt:updated");


    if (valor != "" && id == "ideSerie") {
        $('#ideSubserie').find('option').remove();
        $('#ideSubserie').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#ideSubserie').prop('disabled', false);
        comboboxJs('ideSubserie', '/Document-Manager/util/subSeriesByIdSerieList/' + valor, true);
    } else if (valor == "" && id == "ideSerie") {
        $('#ideSubserie').find('option:not(:first)').remove();
        $("select#ideSubserie").trigger("liszt:updated");
        $("select#ideSubserie").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);
        $("select#ideSubserie").trigger("liszt:updated");

    }
    $("select#ideSerie").trigger("liszt:updated");
    $("select#ideSubserie").trigger("liszt:updated");
}




function comboboxJs(id, data, required) {
    $("#"+id).chosen();
    var dataURL = data;
    var $this = this;

    $.ajax({
        type: 'GET',
        url: dataURL,
        dataType: "json",
        context: $this,
        success: function (response) {
            loadData(response);
        }
    });

    function loadData(data) {
        if(data.length == '0'){
            $('#ideSubserie').find('option:not(:first)').remove();
            $("select#ideSubserie").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);
            showGenericAlert("No existen subseries asociadas a la Serie.", "info");
        }
        for (var i = 0; i < data.length; i++) {
            var choice = data[i];
            $("#" + id).append('<option value="' + choice.value + '">' + choice.label + '</option>');
        }
        $("select#"+id).trigger("liszt:updated");
    }

}

function comboboxJsNotAsync(id, data, required) {
    $("#"+id).chosen();

    var dataURL = data;
    var $this = this;

    $.ajax({
        type: 'GET',
        url: dataURL,
        dataType: "json",
        context: $this,
        async:false,
        success: function (response) {
            loadData(response);
        }
    });

    function loadData(data) {
        for (var i = 0; i < data.length; i++) {
            var choice = data[i];
            $('<option value="' + choice.value + '">' + choice.label + '</option>').appendTo($('#' + id));
        }
        $("select#"+id).trigger("liszt:updated");
    }
}

