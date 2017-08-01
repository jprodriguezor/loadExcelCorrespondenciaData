/**
 * Created by sarias on 19/10/2016.
 */

$(function () {

    //Widget paara carga listas
    comboboxJs('codUniAmt', '/Document-Manager/util/unidadAdministrativoRetencionList', true);
    comboboxJs('idSerie', '/Document-Manager/util/seriesList', true);
    comboboxJs('ideDisFinal', '/Document-Manager/util/disposicionFinalList', true);

    $('#cancelarDisRet').click(function () {
        window.location.reload(true);
    });

    //Validaciónes del formulario
    $("#formRetencionDisposicion").bootstrapValidator({
        live: 'disabled',
        fields: {
            codUniAmt: {
                validators: {
                    notEmpty: {
                        message: "El campo de unidad administrativa es requerido"
                    }
                }
            },
            codOfcProd: {
                validators: {
                    notEmpty: {
                        message: "El campo de oficina productora es requerido"
                    }
                }
            },
            idSerie: {
                validators: {
                    notEmpty: {
                        message: "El campo series es requerido"
                    }
                }
            },
            agTrd: {
                validators: {
                    notEmpty: {
                        message: "El campo retención archivo gestión es requerido"
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: 'El campo solo admite 6 caracteres'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: "El campo solo admite d&iacute;gitos"
                    }
                }
            },
            acTrd: {
                validators: {
                    notEmpty: {
                        message: "El campo retención archivo central es requerido"
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: 'El campo solo admite 6 caracteres'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: "El campo solo admite d&iacute;gitos"
                    }
                }
            },
            ideDisFinal: {
                validators: {
                    notEmpty: {
                        message: "El campo disposición final es requerido"
                    }
                }
            },
            proTrd: {
                validators: {
                    notEmpty: {
                        message: "El campo procedimientos es requerido"
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: 'Los procedimientos deben contener menos de 500 caract&eacute;res.'
                    }
                }
            }
        },
        submitButtons: 'button#guardarContDisRet, button#guardarDisRet',
        submitHandler: function (validator, form) {
            if($('#idSubserie option').length > 1 && $("#idSubserie option:selected" ).val() == ""){
                showGenericAlert("Debe seleccionar una subserie.", "info");
            }else{
                processForm('formRetencionDisposicion', '/Document-Manager/retencionDisposicion');
                limpiarForm();
            }
        }


    });

    function limpiarForm() {
        $('#formRetencionDisposicion').data('bootstrapValidator').resetForm(true);
        $('#ideTabRetDoc').val("");
        $('#codUniAmt').val("");
        $('#codOfcProd').val("");
        $('#idSerie').val("");
        $('#idSubserie').val("");
        $('#ideDisFinal').val("");
        $('#agTrd').val("");
        $('#acTrd').val("");
        $('#proTrd').val("");
        $("select#codUniAmt").trigger("liszt:updated");
        $("select#codOfcProd").trigger("liszt:updated");
        $("select#idSerie").trigger("liszt:updated");
        $("select#idSubserie").trigger("liszt:updated");
        $("select#ideDisFinal").trigger("liszt:updated");
    }

});

function recargarCombosOrganigrama(response) {
    var valor = response.value;
    var id = response.id;
    var valor = valor.split("-", 1);

    if ( id == 'codUniAmt' && valor == 'TODAS') {
        $('#codOfcProd').find('option:not(:first)').remove();
        $("select#codOfcProd").empty().append($('<option></option>').attr('value', 'TODAS').text('- N/A -')).prop('disabled', true);
        $("select#codOfcProd").trigger("liszt:updated");
    } else if (valor != ""  &&  valor != 'TODAS' && id == 'codUniAmt') {
        $('#codOfcProd').find('option').remove();
        $('#codOfcProd').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#codOfcProd').prop('disabled', false);
        comboboxJs('codOfcProd', '/Document-Manager/util/oficinaProductoraRetencionList/' + valor, true);
    }
}


function recargarCombosSerie(response) {
    var valor = response.value;
    var id = response.id;
    var valor = valor.split("-", 1);

    if (valor != "" && id == "idSerie") {
        $('#idSubserie').find('option').remove();
        $('#idSubserie').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#idSubserie').prop('disabled', false);
        comboboxJs('idSubserie', '/Document-Manager/util/subSeriesByIdSerieList/' + valor, true);
    } else if (valor == "" && id == "idSerie") {
        $('#idSubserie').find('option:not(:first)').remove();
        $("select#idSubserie").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);
        $("select#idSubserie").trigger("liszt:updated");
    }
    getTRDBySerieAndSubserieCargarFrmEdit();
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
        async: false,
        success: function (response) {
            loadData(response);
        }
    });

    function loadData(data) {
        if(data.length == '0'){
            $('#idSubserie').find('option:not(:first)').remove();
            $("select#idSubserie").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);
            showGenericAlert("No existen subseries asociadas a la Serie.", "info");
            $("select#idSubserie").trigger("liszt:updated");
        }
        for (var i = 0; i < data.length; i++) {
            var choice = data[i];
            $('<option value="' + choice.value + '">' + choice.label + '</option>').appendTo($('#' + id));
        }
        $("select#"+id).trigger("liszt:updated");
    }
}

function getTRDBySerieAndSubserieCargarFrmEdit(){

    var idSerie = $("#idSerie").val();
    var idSubserie = $("#idSubserie").val();
    var codUniAmt = $("#codUniAmt").val();
    var arr = codUniAmt.split('-');
    codUniAmt =arr[1];

    var codOfcProd = $("#codOfcProd").val();
    arr = codOfcProd.split('-');
    codOfcProd =arr[1];
    var request = '{"idSerie":"' + idSerie + '","idSubserie":"'+idSubserie+'","codOfcProd":"' + codOfcProd+'","codUniAmt":"'+codUniAmt+'"}';

    $.ajax({
        url: "/Document-Manager/retencionDisposicion/getTRDBySerieAndSubserieCargarFrmEdit",
        type: "POST",
        data: request,
        async: true,
        contentType: "application/json",
        success: function (data, textStatus, xhr) {
            if (data.ideTabRetDoc != "") {
                $('#ideTabRetDoc').val(data.ideTabRetDoc);
                $('#codUniAmt').val(data.codUniAmt);
                $('#codOfcProd').val(data.codOfcProd);
                $('#idSerie').val(data.idSerie);
                $('#idSubserie').val(data.idSubserie);
                $('#ideDisFinal').val(data.ideDisFinal);
                $('#agTrd').val(data.agTrd);
                $('#acTrd').val(data.acTrd);
                $('#proTrd').val(data.proTrd);

                $("select#codUniAmt").trigger("liszt:updated");
                $("select#codOfcProd").trigger("liszt:updated");
                $("select#idSerie").trigger("liszt:updated");
                $("select#idSubserie").trigger("liszt:updated");
                $("select#ideDisFinal").trigger("liszt:updated");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#ideDisFinal').val("");
            $('#agTrd').val("");
            $('#acTrd').val("");
            $('#proTrd').val("");
            $('#ideTabRetDoc').val("");
            $("select#codUniAmt").trigger("liszt:updated");
            $("select#codOfcProd").trigger("liszt:updated");
            $("select#idSerie").trigger("liszt:updated");
            $("select#idSubserie").trigger("liszt:updated");
            $("select#ideDisFinal").trigger("liszt:updated");

        }
    });

}

// --- always use this name
function formSucess() {

}

// --- always use this name
function formError() {
}