Ext.define('Biblioteca.view.book.Grid',{
	extend: 'Ext.grid.Panel',
	alias: 'widget.gridPanel',
	title: 'Listado de Libros',
    store: 'Book',
    columns: [
        {text: 'Id',  dataIndex:'id', hidden: true},
        {text: 'Titulo',  dataIndex:'titulo'},
        {text: 'Autor',  dataIndex:'autor'}
    ]
});