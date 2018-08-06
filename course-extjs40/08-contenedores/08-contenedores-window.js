Ext.onReady(function(){

	Ext.create("Ext.window.Window",{
		renderTo: Ext.getBody(),
		title: 'Titulo de la ventana',
		items: [
			Ext.create("Ext.Button", {text: 'Pulsame'}),
			Ext.create("Ext.panel.Panel", {title:'sub panel', html: "Este texto se ñade al cuerpo del panel"})
		]
	}).show();

});