var tokenAjaxBase = $("meta[name='_csrf']").attr("content");
var headerAjaxBase = $("meta[name='_csrf_header']").attr("content");

function ajaxGET(opt) {
    return $.ajax({
        type: "GET",
        url: urlAjaxBase + opt.url,
        data: opt.datos,
        cache: opt.cache,
        xhrFields: opt.xhrFields || {} 
    }).done(function (data, status, xhr) {
        if (opt.funcionExito) {
            opt.funcionExito(data, status, xhr);
        }
    }).fail(function (xhr, status, err) {
        if (opt.funcionError) {
            opt.funcionError(xhr, status, err);
        }
    }).always(function (dataXhr, status, xhrErr) {
    	if (opt.funcionSiempre) {
    		opt.funcionSiempre(dataXhr, status, xhrErr);
    	}
    });
}

function ajaxPOST(opt) {
    return $.ajax({
        type: "POST",
        beforeSend: opt.beforeSend || function(xhr) {
            xhr.setRequestHeader(headerAjaxBase, tokenAjaxBase);
        },
        url: urlAjaxBase + opt.url,
        data: opt.datos,
        processData: opt.processData || false,
        contentType: opt.contentType || false,
        cache: opt.cache || false,
        xhrFields: opt.xhrFields || {} 
    }).done(function (data, status, xhr) {
        if (opt.funcionExito) {
            opt.funcionExito(data, status, xhr);
        }
    }).fail(function (xhr, status, err) {
        if (opt.funcionError) {
            opt.funcionError(xhr, status, err);
        }
    }).always(function (dataXhr, status, xhrErr) {
    	if (opt.funcionSiempre) {
    		opt.funcionSiempre(dataXhr, status, xhrErr);
    	}
    });
}

function ajaxPUT(opt) {
    return $.ajax({
        type: "PUT",
        beforeSend: opt.beforeSend || function(xhr) {
            xhr.setRequestHeader(headerAjaxBase, tokenAjaxBase);
        },
        url: urlAjaxBase + opt.url,
        data: opt.datos,
        processData: opt.processData || false,
        contentType: opt.contentType || false,
        cache: opt.cache || false,
        xhrFields: opt.xhrFields || {} 
    }).done(function (data, status, xhr) {
        if (opt.funcionExito) {
            opt.funcionExito(data, status, xhr);
        }
    }).fail(function (xhr, status, err) {
        if (opt.funcionError) {
            opt.funcionError(xhr, status, err);
        }
    }).always(function (dataXhr, status, xhrErr) {
    	if (opt.funcionSiempre) {
    		opt.funcionSiempre(dataXhr, status, xhrErr);
    	}
    });
}

function ajaxDELETE(opt) {
    var url = urlAjaxBase + opt.url;
    
    if(opt.params) {
        url += '?' + $.param(opt.params);
    }

    return $.ajax({
        type: "DELETE",
        beforeSend: opt.beforeSend || function(xhr) {
            xhr.setRequestHeader(headerAjaxBase, tokenAjaxBase);
        },
        url: url,
        xhrFields: opt.xhrFields || {} 
    }).done(function (data, status, xhr) {
        if (opt.funcionExito) {
            opt.funcionExito(data, status, xhr);
        }
    }).fail(function (xhr, status, err) {
        if (opt.funcionError) {
            opt.funcionError(xhr, status, err);
        }
    }).always(function (dataXhr, status, xhrErr) {
        if (opt.funcionSiempre) {
            opt.funcionSiempre(dataXhr, status, xhrErr);
        }
    });
}

/**
 * Carga el HTML de respuesta en el elemento con el id dado.
 * @param {Object} opt - Objeto opciones.
 * @param {string} opt.id - ID del elemento de la pagina actual que va a sustituir su contenido.
 * @param {string} opt.url - Ruta que devuelve el HTML a cargar.
 * @param {(Object|string)} opt.datos - Parametros de la llamada REST. Atributo 'data' de Ajax.
 * @param {boolean} opt.autoLogin - Si es false, no carga automaticamente la ventana de login. true por defecto.
 */
function ajaxLOAD(opt) {
    var deferred = new $.Deferred();
    
    deferred.done(function (data, status, xhr) {
        if (opt.funcionExito) {
            opt.funcionExito(data, status, xhr);
        }
    }).fail(function (xhr, status, err) {
        if (opt.funcionError) {
            opt.funcionError(xhr, status, err);
        }
    }).always(function (dataXhr, status, xhrErr) {
        if (opt.funcionSiempre) {
            opt.funcionSiempre(dataXhr, status, xhrErr);
        }
    });

    var autoLogin = ('autoLogin' in opt) ? opt.autoLogin : true;

    ajaxGET({
        url: opt.url,
        datos: opt.datos,
        funcionExito: function (data, status, xhr) {
            if ($(data).find("#formLoginMcp").length) {
                if (autoLogin) {
                    location.reload();
                } else {
                    deferred.reject(xhr, 'error', 'Unauthorized');
                }
            } else {
                $('#'+opt.id).html(data);
                deferred.resolve(data, status, xhr);
            }
        },
        funcionError: function (xhr, status, err) {
            deferred.reject(xhr, status, err);
        }
    });

    return deferred;
}