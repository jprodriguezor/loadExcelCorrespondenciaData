/**
 * Created by jrodriguez on 20/11/2016.
 * **/

var trdgroup ;

$(document).ready(function () {

    comboboxJs('codUniAmt', '/Document-Manager/util/unidadAdministrativaTrdList', true);

    $("#codOfcProd").empty();
    $("#codOfcProd").append('<option value=' + "" + '>' + "" + '</option>');
    $("#codOfcProd").prop('disabled', true);
    $("select#codOfcProd").trigger("liszt:updated");
    $('#data').addClass('hidden');

    $('select[name="codUniAmt"]').chosen().change(function (event) {
        $('#publicarTRD').addClass('hidden');
    });

    $('select[name="codOfcProd"]').chosen().change(function (event) {
    });


    $('#publicarTRD').confirmation({
        title: "¿Está seguro de realizar esta acción?",
        singleton: true,
        popout: true,
        placement: "left",
        btnOkLabel: "Publicar versión",
        btnCancelLabel: "Cancelar",
        onConfirm: function (e) {
            processForm('formAddTRD', '/Document-Manager/trd');
        }
    });

});

// --- always use this name
function formSucess() {
}

// --- always use this name
function formError() {
}

function recargarCombosOfcProduc(response) {
    var valor = response.value;
    var id = response.id;

    if (valor != "" && id == 'codUniAmt') {
        $('#codOfcProd').find('option').remove();
        $('#codOfcProd').append('<option value=' + "" + '>' + "--- Seleccione ---" + '</option>');
        $('#codOfcProd').prop('disabled', false);
        comboboxJs('codOfcProd', '/Document-Manager/util/oficinaProductoraTrdList/' + valor, true);
        $("select#codOfcProd").trigger("liszt:updated");
    }
}

function definirTRD(response) {
    var valor = response.value;
    var id = response.id;
    $('#data').removeClass('hidden');

    if (valor != "") {

        $.ajax({
            type: "GET",
            dataType: "json",
            async: true,
            url: '/Document-Manager/trd/generarTableTRD/' + valor,
            success: function (response) {

                if(trdgroup != undefined){
                    trdgroup.destroy();
                }

                trdgroup = $('#trdAdicionarTable').removeAttr('width').DataTable( {
                    "autoWidth": false
                } );;


                if (response.valida === true) {

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
        for (var i = 0; i < data.length; i++) {
            var choice = data[i];
            $('<option value="' + choice.value + '">' + choice.label + '</option>').appendTo($('#' + id));
        }
    }


}

