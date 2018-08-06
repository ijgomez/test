Ext.onReady(function(){

	Ext.create("Ext.menu.Menu",{
		renderTo: Ext.get("center"),
		autoShow: true,// por defecto false
		floating: false,//por defecto true
		items: [
			{
				text: 'Item normal',
				iconCls: 'add16'
			},
			{
				xtype: 'menucheckitem',
				text: 'Opcion checable'
			},
			{
				text: 'Calendario',
				menu: Ext.create("Ext.menu.DatePicker")
			},
			{
				text: 'Colores',
				menu: Ext.create('Ext.menu.ColorPicker')
			},
			{
				text: 'Con subopciones',
				menu: Ext.create("Ext.menu.Menu",{
					items: [
						{
							text: 'Item normal',
							iconCls: 'add16'
						},
						{
							text: 'Item normal',
							iconCls: 'add16'
						},
						{
							text: 'Item normal',
							iconCls: 'add16'
						}
					]
				})
			}
		]
	});

});