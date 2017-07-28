<%@ tag description="input text - bootstrap" pageEncoding="UTF-8" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="tabindex" required="true" %>
<%@ attribute name="label" required="true" %>
<div class="form-group">
    <label class="control-label col-md-12"
           for="${id}">${label}</label>

    <div class="col-md-12">
        <label>
            <input type="radio" name="${id}" id="${id}checkboxActivo"
                   tabindex="${tabindex}"
                   value="1" checked>
            Activo
        </label>
        <label>
            <input type="radio" name="${id}" id="${id}checkboxActivoFalse"
                   value="0">
            Inactivo
        </label>
    </div>
</div>