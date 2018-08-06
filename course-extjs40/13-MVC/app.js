Ext.Loader.setConfig({enabled: true});

//Ext.require('Biblioteca.controller.Books');

Ext.application({
	name: 'Biblioteca',
	appFolder: 'app',
	controllers: ['Books'],
	launch: function(){
		Ext.create("Ext.container.Viewport",{
			layout: 'border',
			items: [
				{
					xtype: 'gridPanel',
					region:'center'
				},
				{
					xtype: 'detailPanel',
					region: 'south',
					height: 200
				}
			]
		});
	}
});