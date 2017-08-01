/**
 * Created by dsanchez on 02/09/2016.
 */

var version;
$(document).ready(function () {

    cargarVersiones('versionCcd', '/Document-Manager/util/versionCcdList', true);

    // ui setup - tables ----------------------
    var tableRelEqui = $("#tableRelEqui").dataTable({
        info: false,
        select: true,
        "ajax": {
            url: "/Document-Manager/relEquivalencia/relEquiList",
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
            {"data": "nombreUAdminO"},
            {"data": "labelCodNomOProOr"},
            {"data": "labelCodNomSerieOr"},
            {"data": "labelCodNomSubSerieOr"},
            {"data": "nombreUAdminD"},
            {"data": "labelCodNomOProD"},
            {"data": "labelCodNomSerieD"},
            {"data": "labelCodNomSubSerieD"}

        ]

    });
    version = 'TOP';
    $('#valVersionCCD').val(version);







    //Agregar
    $('#addItemOrg').click(function () {
        $('#ideOrgaAdminUniAmtOr').prop("disabled", false);
        $('#ideOrgaAdminOfProdOr').prop("disabled", false);
        $('#ideSerieOr').prop("disabled", false);
        $('#ideSubserieOr').prop("disabled", false);
        limpiarForm();
        $('#modalAddEditRelEquiTittle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Crear Relacion de Equivalencia");
        $('#ideOrgaAdminUniAmtOr').prop("disabled", false);
        $('#ideOrgaAdminOfProdOr').prop("disabled", false);
        $('#ideSerieOr').prop("disabled", false);
        $('#ideSubserieOr').prop("disabled", false);

        $('#modalAddEditRelEqui').modal('show');

    });



    // guardar
    //Validaciónes del formulario
    // Bootstrap Validator Form
    $("#formRelEqui").bootstrapValidator({
        live: 'disabled',
        fields: {
            ideOrgaAdminUniAmtOr: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            ideOrgaAdminOfProdOr: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            ideSerieOr: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            ideOrgaAdminUniAmtDes: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            ideOrgaAdminOfProdDes: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            },
            ideSerieDe: {
                validators: {
                    notEmpty: {
                        message: "El campo es requerido"
                    }
                }
            }

        },
        submitButtons: 'button#guardarRelEqui',
        submitHandler: function (validator, form) {
            processForm('formRelEqui', '/Document-Manager/relEquivalencia');
            $('#tableRelEqui').dataTable().fnReloadAjax();
        }

    });



    function limpiarForm() {

        $('#formRelEqui').data('bootstrapValidator').resetForm(true);
        $('#ideOrgaAdminUniAmtOr').val("");
        $('#ideOrgaAdminOfProdOr').val("");
        $('#ideSerieOr').val("");
        $('#ideSubserieOr').val("");
        $('#ideorgaadminUniAmtDes').val("");
        $('#ideorgaadminOfProdDes').val("");
        $('#ideSerieDe').val("");
        $('#ideSubserieDe').val("");

    };


    $('#detailsRelacion').click(function () {
        var tableRelEqui = $('#tableRelEqui').DataTable();
        var rowselected = tableRelEqui.row('.selected');
        if (rowselected.length != 0) {
            var data = tableRelEqui.row('.selected').data();
            $('#ideOrgaAdminUniAmtOrDetail').val(data.labelCodNomUAdminOr);
            $('#labelCodNomOProOrDetail').val(data.labelCodNomOProOr);
            $('#labelCodNomSerieOrDetail').val(data.labelCodNomSerieOr);
            $('#labelCodNomSubSerieOrDetail').val(data.labelCodNomSubSerieOr);

            $('#ideOrgaAdminUniAmtDeDetail').val(data.labelCodNomUAdminD);
            $('#labelCodNomOProDeDetail').val(data.labelCodNomOProD);
            $('#labelCodNomSerieDeDetail').val(data.labelCodNomSerieD);
            $('#labelCodNomSubSerieDeDetail').val(data.labelCodNomSubSerieD);



            $('#digDetailRelEqui').modal('show');
        }
        else {

            showAlert('errorEdit');
        }
    });

    $('#btnremove').confirmation({
        title: "¿Esta seguro de realizar esta acción?",
        singleton: true,
        popout: true,
        placement: "left",
        btnOkLabel: "Eliminar",
        btnCancelLabel: "Cancelar",
        onConfirm: function (e) {
            e.preventDefault();
            var tableRelEqui = $('#tableRelEqui').DataTable();
            var data = tableRelEqui.row('.selected').data();
            var rowselected = tableRelEqui.row('.selected');

            if (rowselected.length != 0) {
                sendDeleteRequest('/Document-Manager/relEquivalencia/removeRelEqui/' + data.ideRelOrigen);
                $('#tableRelEqui').dataTable().fnReloadAjax();
                return false;
            } else {
                showAlert('errorEdit');
            }
            $('#tableRelEqui').dataTable().fnReloadAjax();
        }


    });

});

// --- always use this name
function formSucess() {
    $('#modalAddEditRelEqui').modal('hide');
    $('#tableRelEqui').dataTable().fnReloadAjax();
}

// --- always use this name
function formError() {

}

function recargarOfiProducOrigen(response) {
    var idvalor = response.value.split("-");
    var valor = idvalor[1];
    var id = response.id;

    if (valor != "" && id == "ideOrgaAdminUniAmtOr") {
        $('#ideOrgaAdminOfProdOr').find('option').remove();
        $('#ideOrgaAdminOfProdOr').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#ideOrgaAdminOfProdOr').prop('disabled', false);
        version = $('#versionCcd').val();
        if(version == null){
            version = 'TOP'
        }
        version = formatVersion(version);
        comboboxJsGet('ideOrgaAdminOfProdOr', '/Document-Manager/util/oficinaProductoraListExistInCcdOr/' + valor+"/"+version);
    } else if (valor == "" && id == "ideOrgaAdminUniAmtOr") {
        $('#ideOrgaAdminOfProdOr').find('option:not(:first)').remove();
        $("select#ideOrgaAdminOfProdOr").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);

    }

}

function recargarOfiProducDestino(response) {
    var idvalor = response.value.split("-");
    var valor = idvalor[1];
    var id = response.id;


    if (valor != "" && id == "ideOrgaAdminUniAmtDes") {
        $('#ideOrgaAdminOfProdDes').find('option').remove();
        $('#ideOrgaAdminOfProdDes').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#ideOrgaAdminOfProdDes').prop('disabled', false);
        comboboxJsGet('ideOrgaAdminOfProdDes', '/Document-Manager/util/oficinaProductoraListExistInCcdDe/' + valor);
    } else if (valor == "" && id == "ideOrgaAdminUniAmtDes") {
        $('#ideOrgaAdminOfProdDes').find('option:not(:first)').remove();
        $("select#ideOrgaAdminOfProdDes").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);

    }
}


function cargarCombosSeriesOrigen(response) {

    var valor = response.value;
    var id = response.id;

    var idUniAmd = $("#ideOrgaAdminUniAmtOr").val();
    var idUniAmd = idUniAmd.split("-");
    var idUniAmd = idUniAmd[1];

    var idOfiProd = $("#ideOrgaAdminOfProdOr").val();

    if (valor != "" && id == "ideOrgaAdminOfProdOr") {
        $('#ideSerieOr').find('option').remove();
        $('#ideSerieOr').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#ideSerieOr').prop('disabled', false);
        version = $('#versionCcd').val();
        if(version == null){
            version = 'TOP'
        }
        version = formatVersion(version);
        comboboxJsGet('ideSerieOr', '/Document-Manager/util/seriesListExistInCcdOr/'+idUniAmd+","+idOfiProd+","+version);
    } else if (valor == "" && id == "ideOrgaAdminOfProdOr") {
        $('#ideSerieOr').find('option:not(:first)').remove();
        $("select#ideSerieOr").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);

    }

}


function cargarCombosSeriesDestino(response) {

    var valor = response.value;
    var id = response.id;
    var idUniAmd = $("#ideOrgaAdminUniAmtDes").val();
    var idUniAmd = idUniAmd.split("-");
    var idUniAmd = idUniAmd[1];
    var idOfiProd = $("#ideOrgaAdminOfProdDes").val();
    if (valor != "" && id == "ideOrgaAdminOfProdDes") {
        $('#ideSerieDe').find('option').remove();
        $('#ideSerieDe').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#ideSerieDe').prop('disabled', false);
        comboboxJsGet('ideSerieDe', '/Document-Manager/util/seriesListExistInCcdDe/'+idUniAmd+","+idOfiProd);
    } else if (valor == "" && id == "ideOrgaAdminOfProdDes") {
        $('#ideSerieDe').find('option:not(:first)').remove();
        $("select#ideSerieDe").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);

    }

}


function recargarCombosSubSeriesOrigen(response) {
    var valor = response.value;
    var id = response.id;

    var idUniAmd = $("#ideOrgaAdminUniAmtOr").val();
    var idUniAmd = idUniAmd.split("-");
    var idUniAmd = idUniAmd[1];
    var idOfiProd = $("#ideOrgaAdminOfProdOr").val();
    if (valor != "" && id == "ideSerieOr") {
        $('#ideSubserieOr').find('option').remove();
        $('#ideSubserieOr').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#ideSubserieOr').prop('disabled', false);
        version = $('#versionCcd').val();
        if(version == null){
            version = 'TOP'
        }
        version = formatVersion(version);
        comboboxJsGet('ideSubserieOr', '/Document-Manager/util/subSeriesListExistInCcdOr/' + idUniAmd + ","+idOfiProd +","+ valor+","+version);
    } else if (valor == "" && id == "ideSerieOr") {
        $('#ideSubserieOr').find('option:not(:first)').remove();
        $("select#ideSubserieOr").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);

    }

}

function recargarCombosSubSeriesDestino(response) {

    var valor = response.value;
    var id = response.id;

    var idUniAmd = $("#ideOrgaAdminUniAmtDes").val();
    var idUniAmd = idUniAmd.split("-");
    var idUniAmd = idUniAmd[1];
    var idOfiProd = $("#ideOrgaAdminOfProdDes").val();
    if (valor != "" && id == "ideSerieDe") {
        $('#ideSubserieDe').find('option').remove();
        $('#ideSubserieDe').append('<option value=' + "" + '>' + "--- Seleccione ---" +'</option>');
        $('#ideSubserieDe').prop('disabled', false);
        comboboxJsGet('ideSubserieDe', '/Document-Manager/util/subSeriesListExistInCcdDe/' + idUniAmd + ","+idOfiProd +","+ valor);
    } else if (valor == "" && id == "ideSerieDe") {
        $('#ideSubserieDe').find('option:not(:first)').remove();
        $("select#ideSubserieDe").empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);

    }


}


function comboboxJs(id, data, version) {
    $("#"+id).chosen();
    var dataURL = data;
    var $this = this;

    $.ajax({
        type: 'POST',
        url: dataURL,
        data: "valVersion="+version,
        dataType: "json",
        context: $this,
        success: function (response) {
            loadData(response);
        }
    });

    function loadData(data) {
        if(data.length == '0' && id=='ideSubserieDe' || id == 'ideSubserieOr' ){
            $('#'+id).find('option:not(:first)').remove();
            $("select#"+id).empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);
            showGenericAlert("No existen subseries asociadas a la Serie.", "info");
        }
        for (var i = 0; i < data.length; i++) {
            var choice = data[i];
            $("#" + id).append('<option value="' + choice.value + '">' + choice.label + '</option>');
        }
        $("select#"+id).trigger("liszt:updated");
    }

}

function comboboxJsGet(id, data ) {
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
        if(data.length == '0' && id=='ideSubserieDe'){
            $('#'+id).find('option:not(:first)').remove();
            $("select#"+id).empty().append($('<option></option>').attr('value', '').text('- N/A -')).prop('disabled', true);
            showGenericAlert("No existen subseries asociadas a la Serie.", "info");
        }
        for (var i = 0; i < data.length; i++) {
            var choice = data[i];
            $("#" + id).append('<option value="' + choice.value + '">' + choice.label + '</option>');
        }
        $("select#"+id).trigger("liszt:updated");
    }

}

function cargarVersiones(id, data, required) {


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
        for (var i = 0; i < data.length; i++) {
            var choice = data[i];
            $('<option value="' + choice.value + '">' + choice.label + '</option>').appendTo($('#' + id));
            if(i == 0){
                $('#versionCcd').val(choice.value);
            }
        }
        $("select#"+id).trigger("liszt:updated");
        version = $('#versionCcd').val();
        comboboxJs('ideOrgaAdminUniAmtOr', '/Document-Manager/util/unidadAdministrativoRetencionListExistInCcdOr', version);
        comboboxJs('ideOrgaAdminUniAmtDes', '/Document-Manager/util/unidadAdministrativoRetencionListExistInCcdDe');
    }
}

function recargarVersion(response){
    version = response.value;
    $('#valVersionCCD').val(version);
    var version = $('#versionCcd').val();
    $('#ideOrgaAdminUniAmtOr').find('option:not(:first)').remove();
    $('#ideOrgaAdminUniAmtDes').find('option:not(:first)').remove();
    $('#ideOrgaAdminOfProdOr').find('option:not(:first)').remove();
    $("select#ideOrgaAdminOfProdOr").trigger("liszt:updated");
    $('#ideSerieOr').find('option:not(:first)').remove();
    $("select#ideSerieOr").trigger("liszt:updated");
    $('#ideSubserieOr').find('option:not(:first)').remove();
    $("select#ideSubserieOr").trigger("liszt:updated");
    version = formatVersion(version);
    comboboxJs('ideOrgaAdminUniAmtOr', '/Document-Manager/util/unidadAdministrativoRetencionListExistInCcdOr', version);
    comboboxJs('ideOrgaAdminUniAmtDes', '/Document-Manager/util/unidadAdministrativoRetencionListExistInCcdDe');
}

function formatVersion(version){
    if(version != 'TOP'){
        var aux = version.split("");
        version = "";
        for(var i = 0; i<aux.length; i++){
            if(i >= 2){
                version += aux[i];
            }
        }
    }
    return version;
}


