/**
 * Created by jrodriguez on 02/09/2016.
 */

$(document).ready(function() {

    // ui setup - tables ----------------------
    $("#tableDisposiciones").dataTable({
        info: false,
        select: true,
        "ajax": {
            url: "/Document-Manager/disposicionFinal/disposicionFinalList",
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
            {"data": "nomDisFinal"}
        ]
    });

    function limpiarForm() {
        $('#nomDisFinal').val("");
        $('#ideDisFinal').val("");
        

    };

    $('#btmAgrDisposiciones').click(function () {
        limpiarForm();
        $('#modalAddEditDisposicionesTitle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Crear Disposici&oacute;nes Finales");

    });

    $('#btmEditDisposiciones').click(function () {
        var tableDisposiciones = $('#tableDisposiciones').DataTable();
        var rowselected = tableDisposiciones.row('.selected');
        if (rowselected.length != 0) {
            var data = tableDisposiciones.row('.selected').data();
            $('#ideDisFinal').val(data.ideDisFinal);
            $('#modalAddEditDisposicionesTitle').html("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> Editar Disposici&oacute;nes Finales");
            $('#nomDisFinal').val(data.nomDisFinal);
            $('#modalAddEditDisposiciones').modal('show');
            /*  }
             }
             });*/
        } else {
            showAlert('errorEdit');
        }
    });

    // action setup - save tipologias ----------------------
    $("#formDisposiciones").bootstrapValidator({
        live: 'disabled',
        fields: {
            nomDisFinal: {
                validators: {
                     stringLength: {
                        min: 1,
                        max: 100,
                        message: 'El nombre debe ser menor que 100 caracteres'
                    },
                notEmpty: {
                        message: "El campo es requerido"
                    }, remote: {
                        url: '/Document-Manager/disposicionFinal/validateNomDisDisposiciones',
                        message: 'El nombre insertado ya existe',
                        data: getValidatNomSerie
                    }    
                    
                }
            },
        },
        submitButtons: 'button#guardarDisposiciones',
        submitHandler: function (validator, form) {
            processForm('formDisposiciones', '/Document-Manager/disposicionFinal')
            $('#tableDisposiciones').dataTable().fnReloadAjax();
        }
    });
});


//funcion para validar que el nombre no este repetido en bd
    getValidatNomSerie = function () {
        var nomDisFinal = $('#nomDisFinal').val();
        var ideDisFinal =  $('#ideDisFinal').val();
        return {"nomDisFinal": nomDisFinal, "ideDisFinal": ideDisFinal};
    };
    
function formSucess() {
    $('#modalAddEditDisposiciones').modal('hide');
    $('#tableDisposiciones').dataTable().fnReloadAjax();
    $("#formDisposiciones").data('bootstrapValidator').resetForm(true);
    
}

// --- always use this name
function formError() {
}
