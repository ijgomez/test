Ext.onReady(function(){
	var elementoDivCenter = Ext.get("center");

	elementoDivCenter.on("click", function(){
		alert("Msnaje");
	});

	Ext.create("Ext.Button",{
		renderTo: elementoDivCenter,
		text: 'Boton',
		listeners:{
			click: function(evt){
				alert("Msnaje del componente");
			},
			mouseover: function(){
				this.setText('sobre'); //invalidate()
			}
		}/*,
		handler: function(){alert("Msnaje del componente");}*/
	});

});