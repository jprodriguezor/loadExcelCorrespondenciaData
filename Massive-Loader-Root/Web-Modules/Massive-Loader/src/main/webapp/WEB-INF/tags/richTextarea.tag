<%@ tag description="input text - bootstrap" pageEncoding="UTF-8" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="tabindex" required="true" %>
<%@ attribute name="label" required="true" %>
<div class="form-group">
    <label class="control-label col-md-12"
           for="${id}">${label}</label>

    <div class="col-md-12">
		<textarea class="form-control" id="${id}" rows="3" name="${id}"
                  tabindex="${tabindex}"></textarea>
    </div>
</div>