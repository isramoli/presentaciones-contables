//Métodos Públicos
function mensajeSuccess(texto, configuracionUsuario){
		mensajeTipo(texto, 'success', configuracionUsuario);
}

function mensajeSuccessConfirmar(texto, funcionConfirmar, configuracionUsuario){
	var configuracion = configuracionUsuario? configuracionUsuario : {};
	configuracion.icono            = 'success';
	configuracion.textoHtml        = texto            || "";
	configuracion.funcionConfirmar = funcionConfirmar || "";
	crearAlert2(configuracion);
}

function mensajeError(texto, configuracionUsuario){
		mensajeTipo(texto, 'error', configuracionUsuario);	
}

function mensajeInfor(texto, configuracionUsuario){
		mensajeTipo(texto, 'info', configuracionUsuario);
}

function mensajeWarning(texto, configuracionUsuario){
		mensajeTipo(texto, 'warning', configuracionUsuario);
}

function mensajeQuestion(texto, funcionConfirmar, configuracionUsuario){
	var configuracion = configuracionUsuario? configuracionUsuario : {};
	configuracion.icono            = configuracion.icono || 'question';
	configuracion.textoHtml        = texto            || "";
	configuracion.funcionConfirmar = funcionConfirmar || "";
	configuracion.mostrarBotonCancelar = true;
	crearAlert2(configuracion);
}

function mensajeConfirmarCancelar(texto, funcionConfirmar, funcionCancelar, configuracionUsuario){
		var configuracion = configuracionUsuario? configuracionUsuario : {};
		configuracion.icono            = configuracion.icono || 'question';
		configuracion.textoHtml        = texto               || "";
		configuracion.funcionConfirmar = funcionConfirmar    || "";
		configuracion.funcionCancelar  = funcionCancelar     || "";
		crearAlert2(configuracion);
}

function crearAlert2(configuracionUsuario){
    var configuracion = {};
    var estiloPersonalizado = {};
    var atributosInput = {};
    
    if (typeof configuracionUsuario === 'object'){

        configuracionTexto(configuracion, configuracionUsuario);
        configuracionImagen(configuracion, configuracionUsuario);
        configuracionCSS(configuracion, configuracionUsuario, estiloPersonalizado);        
        configuracionBotones(configuracion, configuracionUsuario);
        configuracionFuncionCerrar(configuracion, configuracionUsuario);
        
        if(!configuracion.showCloseButton && !configuracion.showCancelButton && !configuracion.showConfirmButton){
            configuracion.timer = configuracionUsuario.tiempoCerrarAlert || 2500;
        }
        
        if(configuracionUsuario.funcionConfirmar && configuracionUsuario.funcionCancelar){
            alert2ConAccionConfirmarYCancelar(configuracion, configuracionUsuario.funcionConfirmar, configuracionUsuario.funcionCancelar);
        } else if(configuracionUsuario.funcionConfirmar){
            alert2ConAccionConfirmar(configuracion, configuracionUsuario.funcionConfirmar);
        } else {
            alert2(configuracion);
        }
    } else {
        errorConfiguracionUsuario();
    }
}

//Métodos Privados
function mensajeTipo(texto, icono, configuracionUsuario){
		var configuracion = configuracionUsuario? configuracionUsuario : {};
		configuracion.icono = icono;
		configuracion.textoHtml = texto;
		crearAlert2(configuracion);	
}

function configuracionTexto(configuracion, configuracionUsuario){
    configuracion.type     = configuracionUsuario.icono         || ""      ;
    configuracion.title    = configuracionUsuario.titulo        || ""      ;
    configuracion.text     = configuracionUsuario.texto         || ""      ;
    configuracion.html     = configuracionUsuario.textoHtml     || ""      ;
    configuracion.footer   = configuracionUsuario.textoPie      || ""      ;
}

function configuracionImagen(configuracion, configuracionUsuario){
    configuracion.imageUrl    = configuracionUsuario.urlImagen              || "";
    configuracion.imageWidth  = configuracionUsuario.anchoImagen            || "";
    configuracion.imageHeight = configuracionUsuario.alturaImagen           || "";
    configuracion.imageAlt    = configuracionUsuario.textoAlternativoImagen || "";
}

function configuracionCSS(configuracion, configuracionUsuario, estiloPersonalizado){
    configuracion.position   = configuracionUsuario.posicionAlert || "center";
    configuracion.width      = configuracionUsuario.ancho         || "";
    configuracion.padding    = configuracionUsuario.margenInterno || "";
    configuracion.background = configuracionFondoInterno(configuracionUsuario);
    configuracion.backdrop   = configuracionFondoExterno(configuracionUsuario);  
    
    configuracion.animation = ('animacion' in configuracionUsuario) ? configuracionUsuario.animacion : true;
        
    if(typeof configuracionUsuario.estilo === 'object'){
        estiloPersonalizado.container     = configuracionUsuario.estilo.contenedor     || "";
        estiloPersonalizado.popup         = configuracionUsuario.estilo.popup          || "";
        estiloPersonalizado.header        = configuracionUsuario.estilo.cabecera       || "";
        estiloPersonalizado.title         = configuracionUsuario.estilo.titulo         || "";
        estiloPersonalizado.closeButton   = configuracionUsuario.estilo.BotonCerrar    || "";
        estiloPersonalizado.image         = configuracionUsuario.estilo.imagen         || "";
        estiloPersonalizado.content       = configuracionUsuario.estilo.contenido      || "";
        estiloPersonalizado.input         = configuracionUsuario.estilo.entrada        || "";
        estiloPersonalizado.actions       = configuracionUsuario.estilo.acciones       || "";
        estiloPersonalizado.confirmButton = configuracionUsuario.estilo.botonConfirmar || "";
        estiloPersonalizado.cancelButton  = configuracionUsuario.estilo.botonCancelar  || "";
        estiloPersonalizado.footer        = configuracionUsuario.estilo.pie            || "";
        configuracion.customClass         = estiloPersonalizado;
    }
}

function configuracionFondoInterno(configuracionUsuario){
    var cadenaCaracteres = "";
    if(typeof configuracionUsuario.fondoInterno === 'object'){
        cadenaCaracteres += " " + configuracionUsuario.fondoInterno.color;
        cadenaCaracteres += " " + configuracionUsuario.fondoInterno.imagen;
    }
    return cadenaCaracteres;
}

function configuracionFondoExterno(configuracionUsuario){
    var cadenaCaracteres = "";
    if(typeof configuracionUsuario.fondoExterno === 'object'){
        cadenaCaracteres += " " + configuracionUsuario.fondoExterno.color;
        cadenaCaracteres += " " + configuracionUsuario.fondoExterno.imagen;
        cadenaCaracteres += " " + configuracionUsuario.fondoExterno.posicion;
        cadenaCaracteres += " " + configuracionUsuario.fondoExterno.repeticion;
    }
    return cadenaCaracteres;
}

function configuracionFuncionCerrar(configuracion, configuracionUsuario) {
    configuracion.onClose = configuracionUsuario.onClose || false;
    configuracion.allowOutsideClick = configuracionUsuario.permitirClickFuera || false;
}

function configuracionBotones(configuracion, configuracionUsuario){
    configuracion.showCloseButton   = configuracionUsuario.mostrarBotonCerrar    || false;
    configuracion.showCancelButton  = configuracionUsuario.mostrarBotonCancelar  || false;
    configuracion.showConfirmButton = ('mostrarBotonConfirmar' in configuracionUsuario) ? configuracionUsuario.mostrarBotonConfirmar : true;
    configuracion.focusConfirm      = configuracionUsuario.enfocarBotonConfirmar || false;
    configuracion.confirmButtonText = configuracionUsuario.textoBotonConfirmar   || "Aceptar";
    configuracion.cancelButtonText  = configuracionUsuario.textoBotonCancelar    || "Cancelar";
    configuracion.reverseButtons    = ('revertirBotones' in configuracionUsuario) ? configuracionUsuario.revertirBotones : false;
    
    /*Con sólo insertar, por parte del usuario, el texto del botón cancelar o la funcion cancelar,
		  este se activará sin necesidad de tener que indicarlo también*/
    if(configuracionUsuario.textoBotonCancelar || configuracionUsuario.funcionCancelar){
        configuracion.showCancelButton = true;
    }
}

function errorConfiguracionUsuario(){
    Swal.fire(
        'Error de configuración',
        'Por favor, verifica que estás utilizando correctamente sweetalert2',
        'error'
    );
}

function alert2(configuracion){
    Swal.fire(configuracion);
}

function alert2ConAccionConfirmar(configuracion, funcionConfirmar){
    Swal.fire(configuracion).then((result) => {
        if(result.value){
            funcionConfirmar();
        }
    })
}

function alert2ConAccionConfirmarYCancelar(configuracion, funcionConfirmar, funcionCancelar){
    Swal.fire(configuracion).then((result) => {
        if(result.value){
            funcionConfirmar();
        } else if(result.dismiss === Swal.DismissReason.cancel){
            funcionCancelar();
        }
    })
}