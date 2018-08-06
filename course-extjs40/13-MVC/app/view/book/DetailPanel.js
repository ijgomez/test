Ext.define('Biblioteca.view.book.DetailPanel',{
	extend: 'Ext.panel.Panel',
	alias: 'widget.detailPanel',
	tpl: new Ext.Template([
	            '<div id="libro{id}">',
					'<div id="titulo">{titulo}</div>',
					'<div id="autor">{autor}</div>',
				'</div>'
	])
});