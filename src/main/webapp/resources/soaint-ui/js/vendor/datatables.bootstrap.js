/* Set the defaults for DataTables initialisation */
$.extend(true, $.fn.dataTable.defaults, {
    "sPaginationType": "bootstrap",
    "dom": "<'row toolbar'<'col-xs-12'<'filters pull-left'lf><'actions pull-right'>>>" +
    "t" +
    "<'row'<'col-xs-6'i><'col-xs-6'p>>",
    "language": {
        "lengthMenu": "_MENU_",
        "zeroRecords": "Sin elementos",
        "search": "Buscar ",
        "info": "Mostrando p&aacute;gina _PAGE_ de _PAGES_",
        "infoEmpty": "Sin elementos",
        "infoFiltered": "(filtrado de _MAX_ elementos en total)",
        "paginate": {
            "first": "Primero",
            "last": "&Uacute;ltimo",
            "next": "Siguiente",
            "previous": "Anterior"
        },
        "processing": "Procesando...",
        "emptyTable": "Ningún dato disponible en esta tabla",
        "loadingRecords": "Cargando..."
    }
});


/* Default class modification */
$.extend($.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline",
    "sFilterInput": "form-control input-sm",
    "sLengthSelect": "form-control input-sm"
});


/* API method to get paging information */
$.fn.dataTableExt.oApi.fnPagingInfo = function (oSettings) {
    return {
        "iStart": oSettings._iDisplayStart,
        "iEnd": oSettings.fnDisplayEnd(),
        "iLength": oSettings._iDisplayLength,
        "iTotal": oSettings.fnRecordsTotal(),
        "iFilteredTotal": oSettings.fnRecordsDisplay(),
        "iPage": oSettings._iDisplayLength === -1 ?
            0 : Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
        "iTotalPages": oSettings._iDisplayLength === -1 ?
            0 : Math.ceil(oSettings.fnRecordsTotal() / oSettings._iDisplayLength)
    };
};


/* Bootstrap style pagination control */
$.extend($.fn.dataTableExt.oPagination, {
    "bootstrap": {
        "fnInit": function (oSettings, nPaging, fnDraw) {
            var oLang = oSettings.oLanguage.oPaginate;
            var fnClickHandler = function (e) {
                e.preventDefault();
                var oPaging = oSettings.oInstance.fnPagingInfo();
                oSettings._iRecordsDisplay = oPaging.iLength;
                if(oPaging.iFilteredTotal < oPaging.iLength)
                    oSettings._iRecordsDisplay = oPaging.iFilteredTotal;
                oSettings._iRecordsTotal = oPaging.iTotal;
                if(oPaging.iFilteredTotal < oPaging.iTotal)
                    oSettings._iRecordsTotal = oPaging.iFilteredTotal;

                console.log(oPaging);
                console.log(oSettings);
                //if (e.data.action == "next" && oSettings._iDisplayStart + oSettings._iRecordsDisplay < oSettings._iRecordsTotal)
                //    oSettings._iDisplayStart += oSettings._iRecordsDisplay;
                if (oSettings._iDisplayStart < oSettings._iRecordsTotal)
                    oSettings.oApi._fnPageChange(oSettings, e.data.action, true);
            };

            $(nPaging).append(
                '<ul class="pagination">' +
                '<li class="prev disabled"><a href="#">&larr; ' + oLang.sPrevious + '</a></li>' +
                '<li class="next disabled"><a href="#">' + oLang.sNext + ' &rarr; </a></li>' +
                '</ul>'
            );
            var els = $('a', nPaging);
            $(els[0]).bind('click.DT', {action: "previous"}, fnClickHandler);
            $(els[1]).bind('click.DT', {action: "next"}, fnClickHandler);
        },

        "fnUpdate": function (oSettings, fnDraw) {
            var iListLength = 5;
            var oPaging = oSettings.oInstance.fnPagingInfo();

            oSettings._iRecordsTotal = oPaging.iTotal;
            if(oPaging.iFilteredTotal < oPaging.iTotal)
                oSettings._iRecordsTotal = oPaging.iFilteredTotal;

            var minorLength = oPaging._iRecordsTotal < oSettings._iRecordsTotal ? oPaging._iRecordsTotal : oSettings._iRecordsTotal
            oPaging.iTotalPages = Math.floor(minorLength / oPaging.iLength) + 1;

            var an = oSettings.aanFeatures.p;
            var i, ien, j, sClass, iStart, iEnd, iHalf = Math.floor(iListLength / 2);

            if (oPaging.iTotalPages < iListLength) {
                iStart = 1;
                iEnd = oPaging.iTotalPages;
            }
            else if (oPaging.iPage <= iHalf) {
                iStart = 1;
                iEnd = iListLength;
            } else if (oPaging.iPage >= (oPaging.iTotalPages - iHalf)) {
                iStart = oPaging.iTotalPages - iListLength + 1;
                iEnd = oPaging.iTotalPages;
            } else {
                iStart = oPaging.iPage - iHalf + 1;
                iEnd = iStart + iListLength - 1;
            }

            for (i = 0, ien = an.length; i < ien; i++) {
                // Remove the middle elements
                $('li:gt(0)', an[i]).filter(':not(:last)').remove();

                // Add the new list items and their event handlers
                for (j = iStart; j <= iEnd; j++) {
                    sClass = (j == oPaging.iPage + 1) ? 'class="active"' : '';
                    $('<li ' + sClass + '><a href="#">' + j + '</a></li>')
                        .insertBefore($('li:last', an[i])[0])
                        .bind('click', function (e) {
                            e.preventDefault();
                            oSettings._iDisplayStart = (parseInt($('a', this).text(), 10) - 1) * oPaging.iLength;
                            fnDraw(oSettings);
                        });
                }

                // Add / remove disabled classes from the static elements
                if (oPaging.iPage === 0) {
                    $('li:first', an[i]).addClass('disabled');
                } else {
                    $('li:first', an[i]).removeClass('disabled');
                }

                if (oPaging.iPage === oPaging.iTotalPages - 1 || oPaging.iTotalPages === 0) {
                    $('li:last', an[i]).addClass('disabled');
                } else {
                    $('li:last', an[i]).removeClass('disabled');
                }
            }
        }
    }
});


/*
 * TableTools Bootstrap compatibility
 * Required TableTools 2.1+
 */
if ($.fn.DataTable.TableTools) {
    // Set the classes that TableTools uses to something suitable for Bootstrap
    $.extend(true, $.fn.DataTable.TableTools.classes, {
        "container": "DTTT btn-group",
        "buttons": {
            "normal": "btn btn-default",
            "disabled": "disabled"
        },
        "collection": {
            "container": "DTTT_dropdown dropdown-menu",
            "buttons": {
                "normal": "",
                "disabled": "disabled"
            }
        },
        "print": {
            "info": "DTTT_print_info modal"
        },
        "select": {
            "row": "active"
        }
    });

    // Have the collection use a bootstrap compatible dropdown
    $.extend(true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
        "collection": {
            "container": "ul",
            "button": "li",
            "liner": "a"
        }
    });
}



