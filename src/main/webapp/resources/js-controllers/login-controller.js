

$(document).ready(function () {

    // action setup  ----------------------

    $("#loginForm").bootstrapValidator({
        live: 'disabled',
        fields: {
            usuario: {
                validators: {
                    notEmpty: {
                        message: "El campo usuario es requerido"
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: 'El campo usuario debe ser menor a 30 caracteres'
                    }
                }
            },
            clave: {
                validators: {
                    notEmpty: {
                        message: "El campo contrase&ntilde;a es requerido"
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: 'El campo contrase&ntilde;a debe ser menor a 30 caracteres'
                    }
                }
            }
        },
        submitButtons: 'button#btnLogin',
        submitHandler: function (validator, form) {
            processForm('loginForm', '/Document-Manager/security/processLogin');
        }
    });

});

function formSucess() {
    window.location.href = '/Document-Manager/';
}

function formError() {

}