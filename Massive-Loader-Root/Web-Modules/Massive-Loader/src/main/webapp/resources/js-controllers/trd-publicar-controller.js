/**
 * Created by jrodriguez on 08/01/2017.
 */

$(document).ready(function () {

    $('#publicarTRDEcm').confirmation({
        title: "¿Está seguro de realizar esta acción?",
        singleton: true,
        popout: true,
        placement: "left",
        btnOkLabel: "Publicar Estructura ECM",
        btnCancelLabel: "Cancelar",
        onConfirm: function (e) {
            processForm('formPublicarEcmTRD', '/Document-Manager/trdEcm');
        }
    });

});

// --- always use this name
function formSucess() {
}

// --- always use this name
function formError() {
}