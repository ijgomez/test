//--------Modelo-----------------

Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',    type: 'int'},
        {name: 'name',  type: 'string'},
        {name: 'phone', type: 'string', mapping: 'phoneNumber'}
    ]
});

var data = {
    users: [
        {
            id: 1,
            name: 'Ed Spencer',
            phoneNumber: '555 1234'
        },
        {
            id: 2,
            name: 'Abe Elias',
            phoneNumber: '666 1234'
        }
    ],
    success: true
};

Ext.onReady(function(){

	var store = Ext.create('Ext.data.Store', {
	    autoLoad: true,
	    data : data,
//--------------Ordenacion--------------------
		sorters: [
			{
				property : 'name',
    			direction: 'ASC'
			}
		],
//--------------Ordenacion--------------------
		/*filters: [
			{
				property: "id", 
				value: 2
			}
		],*/
//---------Parametros Obligatorios--------------

	    model: 'User',
	    proxy: {
	        type: 'memory',
	        reader: {
	            type: 'json',
	            root: 'users'
	        }
	    }

//---------Fin Parametros Obligatorios--------------

	});

	var usuario = Ext.create("User",{
		id: '3',
		name: 'Victor',
		phone: '123456789'
	})

	var primerUsuario = store.first();

	console.info("Antes de la insercion el primero es: " + primerUsuario.get('name'));

	var ultimoUsuario = store.last();

	console.info("Antes de la insercion el ultimo es: " + ultimoUsuario.get('name'));

	store.add(usuario);

	ultimoUsuario = store.last();

	console.info("Despues de la insercion el ultimo es: " + ultimoUsuario.get('name'));

	var i = store.find('phone', '123456789');

	var usuarioBuscado = store.getAt(i);

	console.info("El usuario buscado es: " + usuarioBuscado.get('name'));

	store.each(function(item){
		console.info(item.get('name'));
	});

});