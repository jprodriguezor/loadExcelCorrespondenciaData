<%@ tag description="input text - bootstrap" pageEncoding="UTF-8" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="true" %>
<div class="form-group">
    <label class="control-label col-md-12">
        ${label}
    </label>

    <div id="${id}" class="col-md-12">
    </div>
</div>