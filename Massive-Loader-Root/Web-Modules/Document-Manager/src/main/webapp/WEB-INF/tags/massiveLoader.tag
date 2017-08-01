<%@ tag description="input text - bootstrap" pageEncoding="UTF-8" %>
<%@ attribute name="uploadUrl" required="true" %>
<form enctype="multipart/form-data">
    <input id="file" name="file" type="file" multiple>
</form>

<script>

    $('#file').fileinput({
        language: 'es',
        showBrowse: false,
        browseClass: "btn btn-primary",
        uploadUrl: '${uploadUrl}',
        allowedFileExtensions : ['csv'],
    });

    $('#file').on('fileloaded', function(event, file, previewId, index, reader) {

        $( "button:contains('Quitar')" ).removeClass();
        $( "a:contains('Subir')" ).removeClass();
        $( "button:contains('Quitar')" ).addClass("btn btn-default btn-sm");
        $( "a:contains('Subir')" ).addClass("btn btn-default btn-sm");

    });

    $('#file').on('fileuploaded', function(event, file, previewId, index, reader) {
        addInfo("La carga masiva ha finalizado satisfactoriamente - Revise su progreso en la bandeja de cargas masivas");
    });

    $('#file').on('fileuploaderror', function(event, data, msg) {
       addDanger(msg)
    });
    
    $('#adminSerie').click(function () {
        document.location.href = '/Document-Manager/series'
    });
    
    $('#adminSubSerie').click(function () {
        document.location.href = '/Document-Manager/subseries'
    });
</script>