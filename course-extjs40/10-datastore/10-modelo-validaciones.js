//--------Modelo-----------------

Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',    type: 'int'},
	    {name: 'fullname',  type: 'string', convert: function(v, record){
	    	record.set('firtName', v.split(' ')[0]);
	    	record.set('lastName', v.split(' ')[1]);
        }},
        {name: 'lastName',    type: 'string'},
        {name: 'firtName',    type: 'string'},
        {name: 'phone', type: 'string', mapping: 'phoneNumber'},
        {name: 'fecha', type: 'date', format: 'd-m-Y'},
        {name: 'genero', type: 'string'},
        {name: 'mail', type: 'string'}
    ],
    validations:[
    	{type: 'presence',  field: 'fullname'},
        {type: 'length',    field: 'phone',     min: 8},
        {type: 'inclusion', field: 'genero',   list: ['Male', 'Female']},
        {type: 'format',    field: 'mail', matcher: /^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})/}
    ]
});

var data = {
    users: [
        {
            id: 1,
            fullname: 'Ed Spencer',
            phoneNumber: '555 1234',
            genero: 'Hombre'
        },
        {
            id: 2,
            fullname: 'Abe Elias',
            phoneNumber: '666 1234',
            mail: 'victor@gmail.com',
            genero: 'Hombre'
        }
    ],
    success: true
};

Ext.onReady(function(){

	var store = Ext.create('Ext.data.Store', {
	    autoLoad: true,
	    data : data,
	    model: 'User',
	    proxy: {
	        type: 'memory',
	        reader: {
	            type: 'json',
	            root: 'users'
	        }
	    }
	});

	var usuario = Ext.create("User",{
		id: '3',
		fullname: 'Victor',
		phone: '123456789'
	});

	store.add(usuario);

	store.each(function(item){
		
		item.validate();

		console.info(item.get('firtName'));
	});

});