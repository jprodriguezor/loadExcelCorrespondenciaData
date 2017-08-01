/**
 * Created by dsanchez on 02/09/2016.
 */
var tableCCD;
var versionCcd;

$(document).ready(function () {


    versionCcd = 'TOP';
    comboboxJs('idVerCcd', '/Document-Manager/util/versionCcdList', true);
    comboboxJs('idUniAmd', '/Document-Manager/util/unidadAdministrativaCcdOrgList/'+versionCcd, true);
    cargarCcd();

    $('#btReportPDF').mousedown(function () {
        $('#reportType').val('pdf');
    });

    $('#btReportEXCEL').mousedown(function () {
        $('#reportType').val('xls');
    });

});


// --- always use this name
function formSucess() {

}

// --- always use this name
function formError() {

}


function recargarCombosList(response) {
    var idvalor = response.value.split("-");
    var valor = idvalor[0];

    var id = response.id;

    if (valor != "0" && id == "idUniAmd") {
        $('#idOfcProd').find('option:not(:first)').remove();
        comboboxJs('idOfcProd', '/Document-Manager/util/oficinaProductoraCcdOrgList/' + versionCcd + ',' + valor, true);
    } else if (valor == 0 && id == "idUniAmd") {
        $('#idOfcProd').find('option:not(:first)').remove();
    }
    cargarCcd();

}


function recargarCombosUnidadAmd(response) {

    versionCcd = response.value;
    var id = response.id;

    if (versionCcd != "" && id == 'idVerCcd') {
        $('#idUniAmd').find('option').remove();
        $('#idUniAmd').append('<option value=' + "" + '>' + "--- Seleccione ---" + '</option>');
        $('#idUniAmd').prop('disabled', false);

        comboboxJs('idUniAmd', '/Document-Manager/util/unidadAdministrativaCcdOrgList/' + versionCcd, true);
        $("select#idUniAmd").trigger("liszt:updated");
    }
    cargarCcd();

}

function asignarMetadatos(id) {
    var idVersion;
    var valor = $("#" + id + " option:selected").text();

    var idvalor = valor.split("-");

    var fecha = idvalor[1];
    var version = idvalor[0];

    $("#fecha").html(fecha);
    $("#version").html(version);

    if (version != "TOP") {
        version = version.split(".");
        idVersion = version[1];
    }

}

function recargarCombos(response) {

    asignarMetadatos(response.id);

    $("#idUniAmd").val(0);
    $("#idOfcProd").val(0);

    cargarCcd();
}


function cargarCcd() {

    var idVerCcd = $("#idVerCcd").val();

    var idUniAmd = $("#idUniAmd").val();
    if (idUniAmd == 0) {
        idUniAmd = '';
    }
    var idOfcProd = $("#idOfcProd").val();
    if (idOfcProd == 0) {
        idOfcProd = '';
    }


    if (tableCCD != undefined) {
        tableCCD.fnDestroy();
    }

    tableCCD = $("#tableCCD").dataTable({
        ajax: {
            url: "/Document-Manager/versionCcd/getCcdByIdVersionAndUniAmtAndOfiProd",
            type: "POST",
            data: function () {
                return {idVerCcd: idVerCcd, idUniAmd: idUniAmd, idOfcProd: idOfcProd};
            }

        },
        info: false,
        columnDefs: [
            {
                visible: false,
                searchable: false
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

}

function comboboxJs(id, data, required) {
    $("#" + id).chosen();

    var dataURL = data;
    var $this = this;

    $.ajax({
        type: 'GET',
        url: dataURL,
        dataType: "json",
        context: $this,
        success: function (response) {
            loadData(response);
            if (id == "idVerCcd") {
                asignarMetadatos(id);
            }
        }
    });

    function loadData(data) {
        for (var i = 0; i < data.length; i++) {
            var choice = data[i];

            $('<option value="' + choice.value + '">' + choice.label + '</option>').appendTo($('#' + id));
            $("select#" + id).trigger("liszt:updated");
        }
    }

}
