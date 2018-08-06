Ext.onReady(function(){
	var divCenter = document.getElementById("center");

	var divNuevo = document.createElement("div");

	divNuevo.innerHTML = "<p>Este texto se ñade de forma dinamica</p>";

	divCenter.appendChild(divNuevo);
});