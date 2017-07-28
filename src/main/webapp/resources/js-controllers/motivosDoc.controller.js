/**
 * Created by jrodriguez on 02/09/2016.
 */

$(document).ready(function() {

    // ui setup - tables ----------------------
    $("#tableMotivos").dataTable({
        info: false,
        select: true,
        "ajax": {
            url: "/Document-Manager/motivosDoc/motivosCreacionList",
            type: "GET"
        },
        "columnDefs": [
            {
                "visible": false,
                "searchable": false
            }
        ],
        "order": [[0, "asc"]],
        "columns": [
            {"data": "nomMotCreacion"}
        ]
    });

    function limpiarForm() {
        $('#nomMotCreacion').val("");

    }
    ;

    $('#btmAgrMotivos').click(function () {
        limpiarForm();
        $('#modalAddEditMotivoDocTitle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Crear Motivos Documentales");

    });

    $('#btmEditMotivos').click(function () {
        var tableMotivos = $('#tableMotivos').DataTable();
        var rowselected = tableMotivos.row('.selected');
        if (rowselected.length != 0) {
            var data = tableMotivos.row('.selected').data();
            $('#ideMotCreacion').val(data.ideMotCreacion);
            $('#modalAddEditMotivoDocTitle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Editar Motivos Documentales");
            $('#nomMotCreacion').val(data.nomMotCreacion);
            $('#modalAddEditMotivoDoc').modal('show');
            /*  }
             }
             });*/
        } else {
            showAlert('errorEdit');
        }
    });

    // action setup - save tipologias ----------------------
    $("#formMotivosDoc").bootstrapValidator({
        live: 'disabled',
        fields: {
            nomMotCreacion: {
                validators: {
                     stringLength: {
                        min: 1,
                        max: 100,
                        message: 'El nombre debe ser menor que 100 caracteres'
                    },
                    
                    notEmpty: {
                        message: "El campo es requerido"
                    }, remote: {
                        url: '/Document-Manager/motivosDoc/validateNomMotCreacion',
                        message: 'El nombre insertado ya existe',
                        data: getValidatNomSerie
                    }
                }
            },
        },
        submitButtons: 'button#guardarMotivosDoc',
        submitHandler: function (validator, form) {
            processForm('formMotivosDoc', '/Document-Manager/motivosDoc')
            $('#tableMotivos').dataTable().fnReloadAjax();


        }
    });


});


//funcion para validar que el nombre no este repetido en bd
    getValidatNomSerie = function () {
        var nomMotCreacion = $('#nomMotCreacion').val();
        var ideMotCreacion =  $('#ideMotCreacion').val();
        return {"nomMotCreacion": nomMotCreacion,"ideMotCreacion": ideMotCreacion};
    };
    
function formSucess() {

    $('#modalAddEditMotivoDoc').modal('hide');
    $('#tableMotivos').dataTable().fnReloadAjax();
    $("#formMotivosDoc").data('bootstrapValidator').resetForm(true);
    

}

// --- always use this name
function formError() {
}
