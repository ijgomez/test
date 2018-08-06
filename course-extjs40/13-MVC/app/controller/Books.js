Ext.define('Biblioteca.controller.Books',{
	extend: 'Ext.app.Controller',
	stores: ['Book'],
	models: ['Book'],
	views: ['book.Grid', 'book.DetailPanel'],
	init: function(){
		
		this.getBookStore().load();
		
		this.control({
			'viewport > gridpanel': {
				itemclick: this.onGridPanelClick
			}
		});
	},
	onGridPanelClick: function(view, record){
		this.getPanel().update(record.data);
	},
	//Define metodos (getPanel()) que referencian a Componentes (detailPanel) 
	refs: [
		{
			ref: 'panel',
			selector: 'detailPanel' //xtype
		},
		{
			ref: 'grid',
			selector: 'gridPanel' //xtype
		}
	]
});