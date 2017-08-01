/**
 * Created by administrador_1 on 03/09/2016.
 */

// CUSTOM COMPONENTS -------------------

$(function () {

    // CUSTOM COMBO BOX -------------------

    $.widget("custom.combobox", {

        options: {
            id: null,
            tabindex: false,
            data: null
        },

        _create: function () {

            this.comboRef = $('<select id="' + this.options.id + '" name="' + this.options.id + '" class="form-control" tabindex="' + this.options.tabindex + '"><option value=""> --- Seleccione --- </option></select>');
            this.comboRef.appendTo(this.element);

            var dataURL = this.options.data;
            var $this = this;

            var result = $.ajax({
                type: 'GET',
                async: false,
                url: dataURL,
                dataType: "json"
            }).responseText;

            result = JSON.parse(result);
            for (var i = 0; i < result.length; i++) {
                var choice = result[i];
                $('<option value="' + choice.value + '">' + choice.label + '</option>').appendTo(this.comboRef);
            }

        }

    });


    // CUSTOM CHECK BOX------------------

    $.widget("custom.checkbox", {

        options: {
            data: null
        },

        _create: function () {

            var dataURL = this.options.data;
            var $this = this;

            $.ajax({
                type: 'GET',
                url: dataURL,
                dataType: "json",
                context: $this,
                success: function (response) {
                    this._loadData(response);
                }
            });

        },

        _loadData: function (data) {
            for (var i = 0; i < data.length; i++) {
                var choice = data[i];
                this.box = $('<label class="checkbox-inline">' + choice.label + '</label>');
                $('<input type="checkbox" id="' + choice.name + '" name="' + choice.name + '" tabindex="' + choice.tabIndex + '">').appendTo(this.box);
                this.box.appendTo(this.element);
            }
        }

    });

    $('#adminSerieLoad').click(function () {
        document.location.href = '/Document-Manager/series'
    });

    $('#adminSubSerieLoad').click(function () {
        document.location.href = '/Document-Manager/subseries'
    });

    $('#adminAsocLoad').click(function () {
        document.location.href = '/Document-Manager/asociacion'
    });

    $('#adminTpgLoad').click(function () {
        document.location.href = '/Document-Manager/tipologiasdoc'
    });




});