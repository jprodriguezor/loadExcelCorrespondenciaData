/**
 * Created by jrodriguez on 26/11/2016.
 */

var trdgroup;
var versionOrg;

$(document).ready(function () {

    versionOrg ='TOP';

    $("#btReportPDF").prop('disabled', true);
    $("#btReportExcel").prop('disabled', true);


    comboboxJs('codVersionOrg', '/Document-Manager/util/versionesOrganigramaList', true);
    comboboxJs('codUniAmt', '/Document-Manager/util/unidadAdministrativaTrdOrgList/'+ versionOrg, true);

    $("#codOfcProd").empty();
    $("#codOfcProd").append('<option value=' + "" + '>' + "" + '</option>');
    $("#codOfcProd").prop('disabled', true);
    $("select#codOfcProd").trigger("liszt:updated");
    $('#data').addClass('hidden');


    $('select[name="codVersionOrg"]').chosen().change(function (event) {
    });

    $('select[name="codUniAmt"]').chosen().change(function (event) {
        $('#publicarTRD').addClass('hidden');
    });

    $('select[name="codOfcProd"]').chosen().change(function (event) {
    });

    $('select#idVersionOfcProd').change(function () {

    });

    resetDataVersiones();

    $('#btReportPDF').mousedown(function () {
        $('#reportType').val('pdf');
    });

    $('#btReportExcel').mousedown(function () {
        $('#reportType').val('xls');
    });

});

function resetDataVersiones() {
    $("select#idVersionOfcProd").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);
    $("#btReportPDF").prop('disabled', true);
    $("#btReportExcel").prop('disabled', true);
    $("#currentVersionDate").html("-");
    $("#currentVersionNumber").html("-");
    $('#filtroseccionsubseccion').addClass('hidden');
}

function recargarCombosUniAmt(response){

    versionOrg = response.value;
    var id = response.id;
    if (versionOrg != "" && id == 'codVersionOrg') {
        $('#codUniAmt').find('option').remove();
        $('#codUniAmt').append('<option value=' + "" + '>' + "--- Seleccione ---" + '</option>');
        $('#codUniAmt').prop('disabled', false);
        comboboxJs('codUniAmt', '/Document-Manager/util/unidadAdministrativaTrdOrgList/' + versionOrg, true);
        $("select#codUniAmt").trigger("liszt:updated");
    }
}

function recargarCombosOfcProduc(response) {

    var valor = response.value;
    var id = response.id;

    if (valor != "" && id == 'codUniAmt') {
        $('#codOfcProd').find('option').remove();
        $('#codOfcProd').append('<option value=' + "" + '>' + "--- Seleccione ---" + '</option>');
        $('#codOfcProd').prop('disabled', false);
        comboboxJs('codOfcProd', '/Document-Manager/util/oficinaProductoraTrdOrgList/' + valor + ',' + versionOrg,true);
        $("select#codOfcProd").trigger("liszt:updated");
    }
}

function recargarComboVersionesTrdOfcProd(response) {

    var valor = response.value;
    var id = response.id;

    if (valor != "" && id == 'codOfcProd') {
        $('#idVersionOfcProd').find('option').remove();
        $('#idVersionOfcProd').prop('disabled', false);
        comboboxJs('idVersionOfcProd', '/Document-Manager/versionTrd/versionesTrdOfcProd/' + valor, true);
        $("select#idVersionOfcProd").trigger("liszt:updated");
        definirTRD();
    }

}

function asignarMetadatos(textVersion) {

    var versionText = textVersion.split("-");
    var fecha = versionText[1];
    var version = versionText[0];

    $("#currentVersionDate").html(fecha);
    $("#currentVersionNumber").html(version);

}


function definirTRD() {

    var idOfcProd = $("#codOfcProd").val();
    var version = $("#idVersionOfcProd").val();

    if (idOfcProd != "" && version != "") {
        $.ajax({
            type: "GET",
            dataType: "json",
            async: true,
            url: '/Document-Manager/versionTrd/versionTableTRDPorOfcProd/' + idOfcProd + '/' + version+ '/' + versionOrg,
            success: function (response) {

                if (trdgroup != undefined) {
                    trdgroup.destroy();
                }

                trdgroup = $('#trdAdicionarTable').removeAttr('width').DataTable({
                    "autoWidth": false
                });
                ;

                if (response.valida === true) {

                    asignarMetadatos($("#idVersionOfcProd option:selected").text());
                    $("#btReportPDF").prop('disabled', false);
                    $("#btReportExcel").prop('disabled', false);

                    $('#trdAdicionarTable').DataTable().rows().remove().draw(false);
                    $('#filtroseccionsubseccion').removeClass('hidden');
                    $('#publicarTRD').removeClass('hidden');

                    var codUniAmt = response.unidadAdministrativa;
                    var codOfcProd = response.oficinaProductora;
                    $('#seccionDetgroup').html(codUniAmt);
                    $('#subseccionDetgroup').html(codOfcProd);


                    for (var i = 0; i < response.rows.length; i++) {

                        var ct = "";
                        var e = "";
                        var m = "";
                        var s = "";
                        var d = "";

                        if (response.rows[i].disposicionFinal == "2")
                            ct = "X";

                        if (response.rows[i].disposicionFinal == "4")
                            e = "X";

                        if (response.rows[i].disposicionFinal == "1")
                            m = "X";

                        if (response.rows[i].disposicionFinal == "3")
                            s = "X";

                        if (response.rows[i].disposicionFinal == "5")
                            d = "X";

                        trdgroup.row.add([
                            response.rows[i].codigo,
                            response.rows[i].instrumentos,
                            response.rows[i].archivoGestion,
                            response.rows[i].archivoCentral,
                            ct,
                            e,
                            m,
                            s,
                            d,
                            response.rows[i].procedimientos
                        ]).draw();
                    }

                } else {
                    $('#filtroseccionsubseccion').addClass('hidden');
                    $('#publicarTRD').addClass('hidden');
                    addDanger("No existen Tablas de Retención creadas para esta dependencia.");
                }

            }
        })

    }
}


function comboboxJs(id, data, required) {

    var dataURL = data;
    var $this = this;
    $.ajax({
        type: 'GET',
        url: dataURL,
        dataType: "json",
        context: $this,
        async: false,
        success: function (response) {
            loadData(response);
        }
    });

    function loadData(data) {

        if (data.length == '0') {
            resetDataVersiones();
            showGenericAlert("No existen Tablas de Retención creadas para esta dependencia.", "info");
        }

        for (var i = 0; i < data.length; i++) {
            var choice = data[i];
            $('<option value="' + choice.value + '">' + choice.label + '</option>').appendTo($('#' + id));
        }
    }


}