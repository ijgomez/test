Ext.onReady(function(){

	Ext.create("Ext.panel.Panel",{
		renderTo: Ext.get('center'),
		title: 'Titulo del panel',
		items: [
			Ext.create("Ext.Button", {text: 'Pulsame'}),
			Ext.create("Ext.panel.Panel", {title:'sub panel', html: "Este texto se ñade al cuerpo del panel"})
		]
	});

});