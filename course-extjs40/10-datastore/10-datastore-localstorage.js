//--------Modelo-----------------

Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'name', type: 'string'},
        {name: 'phone', type: 'int', mapping: 'phoneNumber'}
    ]
});

//----Otra forma de definir los datos, pero en el mapping de los fields, hay
//----que usar la posicion en el array con base 1
/*var otro = [
	['Ed Spencer', 555],
	['Abe Elias', 666]
];*/

//-------El campo id no lo esta reconociendo
var data = {
    users: [
        {
            name: 'Ed Spencer',
            phoneNumber: 555
        },
        {
            name: 'Abe Elias',
            phoneNumber: 666
        }
    ],
    success: true
};

var store = Ext.create('Ext.data.Store', {
    model: 'User',
    proxy: {
        type: 'localstorage',
        id: 'misUsuarios'
        // No entiende la configuracion del reader
    }
});

Ext.onReady(function(){

	store.loadData(data.users);

	var usuario = Ext.create("User",{
		//id: '3',
		name: 'Victor',
		phone: '123456789'
	})

	store.add(usuario);

	store.sync();

});