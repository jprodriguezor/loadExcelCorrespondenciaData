/**
 * Created by jrodriguez on 11/10/2016.
 */


$(document).ready(function () {

    $("#tableRegistroCargaMasiva").dataTable({
        info: false,
        select: true,
        "autoWidth": false,
        "ajax": {
            url: "/Document-Manager/cargaMasiva/listCargaMasivaDetail",
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
            {"data": "estado", "width": "20%"},
            {"data": "contenido", "width": "20%"},
            {"data": "mensajes", "width": "60%"}
        ]
    });

    $('#adminMasiveLoad').click(function () {
        document.location.href = '/Document-Manager/cargaMasiva'
    });

});
