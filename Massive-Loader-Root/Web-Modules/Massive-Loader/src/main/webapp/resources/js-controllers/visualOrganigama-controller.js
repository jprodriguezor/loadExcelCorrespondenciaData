/**
 * Created by sarias on 15/11/2016.
 */

$(document).ready(function () {

    //Servicio que llena la lista de versiones
    $("#cbxVersiones").combobox({id: 'idVersiones', data: '/Document-Manager/util/versionesOrganigramaList', tabindex: 1});
    $("#idVersiones").chosen();
    $("#cbxVersiones option[value='']").remove();
    $("select#idVersiones").trigger("liszt:updated");


    $('#search-input').keyup(function (e) {
        $("#organigrama").jstree("search", $(this).val());
    });
    ;

    $('#idVersiones').on('change', function() {
        $("#organigrama").jstree("destroy");
        configurarOrganigrama($(this).val());
    });


    configurarOrganigrama($('#idVersiones').val());

});

function configurarOrganigrama(version) {

    $.ajax({
        url: "/Document-Manager/visualOrganigrama/listOrganigramaTreeVersion",
        type: "POST",
        data: "valVersion="+version,
        async: true,
        success: function (data, textStatus, xhr) {
            var opcionSeleccionada = $("#idVersiones option:selected" ).text();
            var cadenaDividida = opcionSeleccionada.split("-");
            $("#fecha").html(cadenaDividida[1]);
            $("#version").html(cadenaDividida[0]);
            $('#organigrama').jstree(data).bind("select_node.jstree", function (e, data) {
                $('#placeholder-wrapper').html("<h4><span class='glyphicon glyphicon-folder-open' aria-hidden='true'></span> Estructura Org&aacute;nico Funcional  </h4>")
                current_node = data.node.original;
                var dataOrganigrama = {
                    ideOrganigrama: current_node.id,
                    codOrganigrama: current_node.code,
                    nomOrganigrama: current_node.text,
                    estOrganigrama: current_node.status,
                    descOrganigrama: current_node.desc,
                    nivelOrg: current_node.level,
                    idePadre: current_node.parent,
                    nomOrgPadre: current_node.textParent,
                    nivelOrgPadre: current_node.levelParent
                };

                $('#detailsChild').html(dataOrganigrama.nomOrganigrama);
                $('#detailsChild-status').html(dataOrganigrama.estOrganigrama == '1' ? "Activo" : "Inactivo");
                if (dataOrganigrama.idePadre == '#') {
                    $('label[for="detailsChild"]').html("Entidad (Fondo)");
                    $('#detailsParent-wrapper').hide();
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }
                else if (dataOrganigrama.idePadre != '#' && dataOrganigrama.nivelOrg == '1') {
                    $('label[for="detailsChild"]').html("Sede Administrativa (Subfondo)");
                    $('#detailsParent-wrapper').show();
                    $('label[for="detailsParent"]').html("Entidad (Fondo)");
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }
                else if (dataOrganigrama.idePadre != '#' && dataOrganigrama.nivelOrg == '2') {
                    $('label[for="detailsChild"]').html("Oficina Productora (Subsección)");
                    $('#detailsParent-wrapper').show();
                    $('label[for="detailsParent"]').html("Sede Administrativa (Subfondo)");
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }
                else if (dataOrganigrama.idePadre != '#' && dataOrganigrama.nivelOrg == '2') {
                    $('label[for="detailsChild"]').html("Oficina Productora (Subsección)");
                    $('#detailsParent-wrapper').show();
                    $('label[for="detailsParent"]').html("Sede Administrativa (Subfondo)");
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }
                else {
                    $('label[for="detailsChild"]').html("Oficina Productora (Subsección)");
                    $('#detailsParent-wrapper').show();
                    $('label[for="detailsParent"]').html("Unidad Administrativa (Sección)");
                    $('#detailsChild-wrapper').show();
                    $('#detailsActions-wrapper').show();
                }

                if (dataOrganigrama.idePadre != '#') {
                    $('#detailsParent').html(dataOrganigrama.nomOrgPadre);
                }
                else {
                    $('#detailsParent').html("-N/A-");
                }

                $('#detailsChild-status-wrapper').show();
                current_item = dataOrganigrama;
                $('#detailsOrganigrama').show();

            })

        },
        error: function (jqXHR, textStatus, errorThrown) {
            var data = jqXHR.responseJSON;
            addDanger("Internal system error");
        }
    });
}



