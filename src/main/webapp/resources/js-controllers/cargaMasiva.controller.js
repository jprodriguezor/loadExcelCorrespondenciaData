/**
 * Created by jrodriguez on 02/09/2016.
 */

$(document).ready(function () {

    // ui setup - tables ----------------------
    $("#tableCargaMasiva").dataTable({
        info: false,
        select: true,
        "ajax": {
            url: "/Document-Manager/cargaMasiva/cargaMasivaList",
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
            {"data": "nombre"},
            {"data": "fechaText"},
            {"data": "totalRegistros"},
            {"data": "totalRegistrosExitosos"},
            {"data": "totalRegistrosError"},
            {"data": "estado"}
        ]
    });

    $('#detailsCargaMasiva').click(function () {
        var tableCargaMasiva = $('#tableCargaMasiva').DataTable();
        var rowselected = tableCargaMasiva.row('.selected');
        if (rowselected.length != 0) {
            var data = tableCargaMasiva.row('.selected').data();
            $('#id').val(data.id);
            sendGetRequest('/Document-Manager/cargaMasiva/initMassiveLoadDetailById/'+ data.id);
        }
        else {
            showAlert('errorEdit');
        }
    });
});

function formSucess() {
    document.location.href = '/Document-Manager/cargaMasiva/detail';
}
// --- always use this name
function formError() {

}
