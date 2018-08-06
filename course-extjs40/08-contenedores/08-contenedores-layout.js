Ext.onReady(function(){

	Ext.create("Ext.panel.Panel",{
		renderTo: Ext.get('center'),
		width: 300,
    	height: 300,
		title: 'Titulo del panel',
		layout: 'accordion',
		items: [
			Ext.create("Ext.panel.Panel", {title:'Primero', html: "Este texto se ñade al cuerpo del panel"}),
			Ext.create("Ext.panel.Panel", {title:'Segundo', html: "Este texto se ñade al cuerpo del panel"})
		]
	});

});