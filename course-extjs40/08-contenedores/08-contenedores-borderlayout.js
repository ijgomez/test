Ext.onReady(function(){

	Ext.create("Ext.panel.Panel",{
		renderTo: Ext.get('center'),
		width: 800,
    	height: 600,
		title: 'Titulo del panel',
		layout: 'border',
		items: [
			Ext.create("Ext.panel.Panel", {
				title:'Izquierda',
				html: "Este texto se ñade al cuerpo del panel",
				collapsible: true,
				region: 'west'
			}),
			Ext.create("Ext.panel.Panel", {
				title:'Derecha', 
				html: "Este texto se ñade al cuerpo del panel",
				region: 'east'
			}),
			Ext.create("Ext.panel.Panel", {
				title:'Arriba', 
				html: "Este texto se ñade al cuerpo del panel",
				region: 'north',
				height: 100
			}),
			Ext.create("Ext.panel.Panel", {
				title:'Abajo', 
				html: "Este texto se ñade al cuerpo del panel",
				region: "south",
				height: 100
			}),
			Ext.create("Ext.panel.Panel", {
				title:'Centro', 
				html: "Este texto se ñade al cuerpo del panel",
				region: 'center'
			})
		]
	});

});