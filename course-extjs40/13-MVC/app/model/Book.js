Ext.define('Biblioteca.model.Book',{
	extend: 'Ext.data.Model',
	fields: [
	         {name: 'id', type: 'int'},
	         {name: 'titulo', type: 'string'},
	         {name: 'autor', type: 'string'}
	]
});