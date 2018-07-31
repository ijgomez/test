
var capaInterior = null;
var capaCabeceraFila = null;
var capaCabeceraColumna = null;
var capaCabeceraEsquina = null;
var cabeceraFilaPrimeraColumna = null;
var x;
var y;
var horizontal = false;
var vertical = false;
var tabla = null;

function fijarScrollTabla(idCapaInterior, scrollHorizontal, scrollVertical) {
	
	horizontal = scrollHorizontal;
	vertical = scrollVertical;
	
	capaInterior = document.getElementById(idCapaInterior);
	tabla = capaInterior.childNodes[0];
	
	var listaFilasCabecera = tabla.childNodes[0];
	var filaCabecera1 = listaFilasCabecera.childNodes[0];
	var alturaCabecera = 0;
	for (var i = 0; i < listaFilasCabecera.childNodes.length; i++){
		alturaCabecera = alturaCabecera + listaFilasCabecera.childNodes[i].offsetHeight;
	}
	 
	x = tabla.offsetWidth;
	y = tabla.offsetHeight;
	capaCabeceraFila = capaInterior.cloneNode(true);
	if (horizontal) {
		capaCabeceraFila.style.height = alturaCabecera;
		capaCabeceraFila.style.overflow = "hidden";
		capaInterior.parentNode.insertBefore(capaCabeceraFila, capaInterior);
		tabla.style.position = "absolute";
		tabla.style.top = "-" + alturaCabecera;
		y = y - alturaCabecera;
	}
	capaCabeceraEsquina = capaCabeceraFila.cloneNode(true);
	cabeceraFilaPrimeraColumna = filaCabecera1.childNodes[0];
	capaCabeceraColumna = capaInterior.cloneNode(true);
	capaInterior.style.position = "relative";
	if (vertical) {
		capaInterior.parentNode.insertBefore(capaCabeceraColumna, capaInterior);
		capaInterior.style.left = cabeceraFilaPrimeraColumna.offsetWidth;
		tabla.style.position = "absolute";
		tabla.style.left = "-" + cabeceraFilaPrimeraColumna.offsetWidth;
	} else {
		capaInterior.style.left = 0;
	}
	if (vertical) {
		capaCabeceraColumna.style.width = cabeceraFilaPrimeraColumna.offsetWidth;
		capaCabeceraColumna.style.overflow = "hidden";
		capaCabeceraColumna.style.zIndex = "99";
		capaCabeceraColumna.style.position = "absolute";
		capaCabeceraColumna.style.left = "0";
		addScrollSynchronization(capaCabeceraColumna, capaInterior, "vertical");
		x = x - cabeceraFilaPrimeraColumna.offsetWidth;
	}
	if (horizontal) {
		if (vertical) {
			capaInterior.parentNode.insertBefore(capaCabeceraEsquina, capaInterior);
		}
		capaCabeceraEsquina.style.position = "absolute";
		capaCabeceraEsquina.style.left = "0";
		capaCabeceraEsquina.style.top = "0";
		capaCabeceraEsquina.style.width = cabeceraFilaPrimeraColumna.offsetWidth;
		capaCabeceraEsquina.overflow = "hidden";
		capaCabeceraEsquina.style.zIndex = "100";
		capaCabeceraEsquina.style.backgroundColor = "#ffffff";
	}
	if (horizontal) {
		addScrollSynchronization(capaCabeceraFila, capaInterior, "horizontal");
	}
	if (horizontal || vertical) {
		window.onresize = ResizeScrollArea;
		ResizeScrollArea();
	}
}

function ResizeScrollArea() {
	var height = document.body.clientHeight - 120;
	if (!vertical) {
		height -= capaCabeceraFila.offsetHeight;
	}
	var width = document.body.clientWidth - 50;
	if (!horizontal) {
		width -= capaCabeceraColumna.offsetWidth;
	}
	var headerRowsWidth = 0;
	tabla.style.width = x;
	tabla.style.height = y;
	if (capaCabeceraEsquina !== null) {
		headerRowsWidth = capaCabeceraEsquina.offsetWidth;
	}

	if (tabla.offsetWidth > width) {
		capaInterior.style.width = Math.max(width - headerRowsWidth, 0);
		capaInterior.style.overflowX = "scroll";
		capaInterior.style.overflowY = "auto";
	} else {
		capaInterior.style.width = x;
		capaInterior.style.overflowX = "auto";
		capaInterior.style.overflowY = "auto";
	}
	if (capaCabeceraFila !== null) {
		capaCabeceraFila.style.width = capaInterior.offsetWidth + headerRowsWidth;
	}

	if (tabla.offsetHeight > height) {
		capaInterior.style.height = Math.max(height, 80);
		capaInterior.style.overflowY = "scroll";
	} else {
		capaInterior.style.height = y;
		capaInterior.style.overflowY = "hidden";
	}
	if (capaCabeceraColumna !== null) {
		capaCabeceraColumna.style.height = capaInterior.offsetHeight;
	}

	if (capaInterior.style.overflowY == "scroll") {
		capaInterior.style.width = capaInterior.offsetWidth + 17;
	}
	if (capaInterior.style.overflowX == "scroll") {
		capaInterior.style.height = capaInterior.offsetHeight + 17;
	}
	capaInterior.parentElement.style.width = capaInterior.offsetWidth + headerRowsWidth;
}

function getOnScrollFunction(oElement) {
	return function () {
		if (oElement._scrollSyncDirection == "horizontal" || oElement._scrollSyncDirection == "both") {
			oElement.scrollLeft = event.srcElement.scrollLeft;
		}
		if (oElement._scrollSyncDirection == "vertical" || oElement._scrollSyncDirection == "both") {
			oElement.scrollTop = event.srcElement.scrollTop;
		}
	};
}

function addScrollSynchronization(fromElement, toElement, direction) {
	removeScrollSynchronization(fromElement);
	fromElement._syncScroll = getOnScrollFunction(fromElement);
	fromElement._scrollSyncDirection = direction;
	fromElement._syncTo = toElement;
	toElement.attachEvent("onscroll", fromElement._syncScroll);
}

function removeScrollSynchronization(fromElement) {
	if (fromElement._syncTo != null) {
		fromElement._syncTo.detachEvent("onscroll", fromElement._syncScroll);
	}
	fromElement._syncTo = null;
	fromElement._syncScroll = null;
	fromElement._scrollSyncDirection = null;
}

