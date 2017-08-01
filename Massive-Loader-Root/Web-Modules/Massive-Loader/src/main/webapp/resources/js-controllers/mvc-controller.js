/**
 * Created by administrador_1 on 03/09/2016.
 */


function processForm(formId, endpoint) {

    var formData = JSON.stringify(jQuery("#" + formId).serializeArray(),escape);

    var jsonForm = JSON.parse(formData);

    var request = "{";
    for (var i = 0; i < jsonForm.length; i++) {
        var item = jsonForm[i];
        var extraSegment = (i == (jsonForm.length - 1)) ? "" : ",";
        request = request + '"' + item.name + '":"' + item.value + '"' + extraSegment;
    }

    request = request + "}"
	sendPostRequest(endpoint, request, formId);

}


function escape (key, val) {
    if (typeof(val)!="string") return val;
    return val
        .replace(/[\\]/g, '\\\\')
        .replace(/[\/]/g, '\\/')
        .replace(/[\b]/g, '\\b')
        .replace(/[\f]/g, '\\f')
        .replace(/[\n]/g, '\\n')
        .replace(/[\r]/g, '')
        .replace(/[\t]/g, '\\t')
        .replace(/[\"]/g, '\\"')
        .replace(/\\'/g, "\\'");
}

// communication services

function sendPostRequest(endpoint, request, formId) {

    $.ajax({
        url: endpoint,
        type: "POST",
        data: request,
        contentType: "application/json",
        success: function (data, textStatus, xhr) {
            if (data.success === true) {
                if( data.message ){
                    addSuccess(data.message);
                }
                formSucess();
            } else {
                addDanger(data.message);
                formError();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            var data = jqXHR.responseJSON;
            addDanger("Internal system error");
        }
    });

}


function sendDeleteRequest(endpoint) {

    $.ajax({
        url: endpoint,
        type: "DELETE",
        contentType: "application/json",
        success: function (data, textStatus, xhr) {
            if (data.success === true) {
                addSuccess(data.message);
                formSucess();
            } else {
                addDanger(data.message);
                formError();

            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            var data = jqXHR.responseJSON;
            addDanger("Internal system error");
        }
    });

}

function sendGetRequest(endpoint) {
    $.ajax({
        url: endpoint,
        type: "GET",
        async : true,
        contentType: "application/json",

        success: function (data, textStatus, xhr) {
            if (data.success === true) {
                formSucess();
            } else {
                addDanger(data.message);
                formError();

            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            var data = jqXHR.responseJSON;
            addDanger("Internal system error");
        }
    });

}




// messaging functions -----------------------

function addError(detail) {
    showGenericAlert("<strong>Informaci&oacute;n;</strong><br/>" + detail, 'danger');
}

function addDanger(detail) {
    showGenericAlert("<strong>Informaci&oacute;n</strong><br/>" + detail, 'danger');
}

function addInfo(detail) {
    showGenericAlert("<strong>Informaci&oacute;n</strong><br/>" + detail, 'info');
}

function addSuccess(detail) {
    showGenericAlert("<strong>&iexcl;Informaci&oacute;n&excl;</strong><br/>" + detail, 'success');
}


function showGenericAlert(message, type) {
    $.bootstrapGrowl(message, {
        type: type, // (null, 'info', 'error', 'success')
        align: 'center', // ('left', 'right', or 'center')
        offset: {from: 'top', amount: 180}, // 'top', or 'bottom'
        width: 650, // (integer, or 'auto')
        delay: 4000, // Time while the message will be displayed. It's not equivalent to the *demo* timeOut!
        allow_dismiss: true, // If true then will display a cross to close the popup.
        stackup_spacing: 10 // spacing between consecutively stacked growls.
    });
}




//[soaint js framework] ---------------------

$.ajaxSetup({cache: false});

$(document).ready(function () {

    //For mark selected row
    $('table tbody').on('click', 'tr', function () {
        var table = $(this).parent().parent().DataTable();
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    var month = new Array();
    month[0] = "01";
    month[1] = "02";
    month[2] = "03";
    month[3] = "04";
    month[4] = "05";
    month[5] = "06";
    month[6] = "07";
    month[7] = "08";
    month[8] = "09";
    month[9] = "10";
    month[10] = "11";
    month[11] = "12";

    var fecha = new Date();
    $("#fecha").append(fecha.getDate() + "/" + month[fecha.getMonth()] + "/" + fecha.getFullYear() + " - " + fecha.getHours()
    + ":" + fecha.getMinutes());



});

$(document).ajaxStart(function () {

    $.blockUI({
        message: "Por favor espere...", css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff',
            'z-index': 999999
        }
    });
}).ajaxStop($.unblockUI);