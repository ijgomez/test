Ext.onReady(function(){
	var divCenter = Ext.get("center");

	divCenter.createChild({
		tag: 'div',
		id: 'pepito',
		children:[
			{
				tag: 'p',
				html: "Este texto se ñade de forma dinamica"
			}
		]
	});
});