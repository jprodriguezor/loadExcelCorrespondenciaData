/**
 * Created by jose on 4/15/14.
 */
AbstractChosen.default_multiple_text = "Seleccione algunas opciones";

AbstractChosen.default_single_text = "Selecione una opción";

AbstractChosen.default_no_result_text = "No se encontraron coincidencias";

$.ajaxSetup({ cache: false });

function showAlert(index, data) {
    var message = "";
    var type = '';
    switch (index) {
        case 'errorEdit':
            message = "<strong>&iexcl;Error&excl;</strong> Debe seleccionar un elemento.";
            type = 'danger';
            break;
        case 'errorSave':
            message = "<strong>&iexcl;Error&excl;</strong> Se produjo un error al guardar los datos.";
            type = 'danger';
            break;
        case 'infoSave':
            message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> Datos guardados satisfactoriamente.";
            type = 'success';
            break;
        case 'tipDocNota':
            message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> Debe seleccionar un Tipo Documental.";
            type = 'danger';
            break;
        case 'canNotConfigure':
            message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> No se puede configurar. Ya existe otro instrumento en ese estado (<i>" + data.instrument + "</i>).";
            type = 'danger';
            break;
        case 'canNotPublish':
            message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> No se puede publicar una versión o ya esta publicado.";
            type = 'info';
            break;
        case 'canConfigure':
            message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> Instrumento marcado para configuración satisfactoriamente.";
            type = 'success';
            break;
        case 'canNotDoit':
            message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> No puede realizar esta acción. El instrumento no está marcado para configuración.";
            type = 'info';
            break;
        case 'cantNotDelete':
            message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> El elemento no puede ser eliminado.";
            type = 'danger';
            break;
        case 'caracteristicaSelect':
            message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> El campo de característica es requerido, debe seleccionar una opción al menos.";
            type = 'danger';
            break;
        default:
            message = "Mensaje no definido.";
            type = 'info';
            break;
    }
    showGenericAlert(message, type);
}

function showGenericAlert(message, type) {
    $.bootstrapGrowl(message, {
        type: type, // (null, 'info', 'error', 'success')
        align: 'center', // ('left', 'right', or 'center')
        offset: {from: 'top', amount: 180}, // 'top', or 'bottom'
        width: 550, // (integer, or 'auto')
        delay: 3000, // Time while the message will be displayed. It's not equivalent to the *demo* timeOut!
        allow_dismiss: true, // If true then will display a cross to close the popup.
        stackup_spacing: 10 // spacing between consecutively stacked growls.
    });
}

$.fn.addItems = function (data, select) {
    return this.each(function () {
        var list = this;
        $.each(data, function (index, itemData) {
            var option = new Option(itemData.Text, itemData.Value);
            if (select)
                $(option).prop('selected', true);
            for (var key in itemData) {
                //alert(' name=' + key + ' value=' + p[key]);
                if (key != "Text" && key != "Value")
                    $(option).attr(key, itemData[key]);
            }
            list.add(option);
        });
    });
};

$(document).ready(function () {


    $('#setForConfiguration').click(function (e) {
        e.preventDefault();

        var instrument = $(this).attr('data-instrument');

        $.ajax({
            url: "/Document-Manager/util/setEstadoInstrumentoConf/"+instrument,
            type: "GET",
            async: true,
            contentType: "application/json",

            success: function (data, textStatus, xhr) {
                if (data.success === true) {

                    addSuccess(data.message);
                    $("#statusInstrumentLabel").text("CONFIGURADO");
                    $("#statusInstrument").val("1");
                    $("#addItemOrg").prop('disabled', false);
                    $("#editItemOrg").prop('disabled', false);
                    $("#publicarOrganigrama").prop('disabled', false);


                } else {
                    addDanger(data.message);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                var data = jqXHR.responseJSON;
                addDanger("Internal system error");
            }
        });
    });

    $('#cancelConfiguration').click(function (e) {
        e.preventDefault();
        var instrument = $(this).attr('data-instrument');
        var status = $("#statusInstrument").val();
        if (status != "1") {
            var message = "<strong>&iexcl;Informaci&oacute;n&excl;</strong> El instrumento no se encuentra en estado de configuración.";
            var type = 'info';
            showGenericAlert(message, type);
        }
        else {
            $.ajax({
                url: "/Document-Manager/util/setEstadoInstrumentoCancel/"+instrument,
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