Ext.define('Biblioteca.store.Book',{
	extend: 'Ext.data.Store',
	model: 'Biblioteca.model.Book',
	id: 'bookStore',
	autoSync: true,
	autoLoad: true,
	proxy: {
		type: 'ajax',
		url: 'datos.json',
		reader: {
			root: 'data',
			successProperty : 'meta.success'
		}
	}
});