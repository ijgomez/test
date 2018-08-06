var idPersona = 0;

Ext.define(
	'com.ejemplo.Persona',
	{
		id: ++idPersona,
		constructor: function(config){
			this.nombre = config.nombre;
		},
		saludar: function(){
			alert("Hola " + this.nombre + "!!!!");
		}
	}
);

Ext.define(
	'com.ejemplo.Cliente',
	{
		extend: 'com.ejemplo.Persona',
		constructor: function(config){
			this.callParent(arguments);
			this.cuentaCorriente = config.cuentaCorriente;
		},
		pagar: function(cantidad){
			this.cuentaCorriente.cantidad -= cantidad;
			console.info(this.cuentaCorriente.cantidad);
		}
	}
);

Ext.onReady(function(){
	var persona = Ext.create('com.ejemplo.Persona', {nombre: "Victor"});

	persona.saludar();

	var cliente = Ext.create(
			'com.ejemplo.Cliente', 
			{nombre: "Juan", cuentaCorriente: {numero: "12345", cantidad: 123454}});

	cliente.saludar();
	cliente.pagar(123);
});