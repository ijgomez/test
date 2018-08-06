var secuenciaIdCliente = 0;
var secuenciaIdPedido = 0;
var secuenciaIdProducto = 0;

//-------------------------------------------------

Ext.define(
	"Cliente",
	{
		id: ++secuenciaIdCliente,
		constructor: function(config){
			this.nombre = config.nombre;
		},
		getId: function(){
			return this.id;
		},
		getnombre: function(){
			return this.nombre;
		},
		setNombre: function(nombre){
			this.nombre = nombre;
		},
		toString: function(){
			return "{'id': " + this.id + ", 'nombre': " + this.nombre +"}";
		}
	}
);

//-------------------------------------------------

Ext.define(
	"ClienteAvanzado",
	{
		extend: "Cliente",
		constructor: function(config){
			this.callParent(arguments);
			this.direccion = config.direccion;
		},
		realizarNuevoPedido: function(){
			
		}
	}
);

//-------------------------------------------------

Ext.define(
	"Pedido",
	{
		id: ++secuenciaIdPedido,
		constructor: function(config){
			this.cliente = config.cliente;
			this.productos = config.productos;
		},
		getId: function(){
			return this.id;
		},
		getCliente: function(){
			return this.cliente;
		},
		addProducto: function(producto){
			this.productos.push(producto);
		},
		removeProducto: function(posicion){
			this.productos.remove(this.productos[posicion]);
		},
		toString: function(){
			var resultado = "{'id':" + this.id + ",'cliente': " + this.cliente.toString();

			resultado += ",'productos': [";

			for (var i = 0; i < this.productos.length; i++) {
				resultado += this.productos[i].toString() + ", ";
			};

			resultado += "]}";

			return resultado;
		}
	}
);

//-------------------------------------------------

Ext.define(
	"Producto",
	{
		id: ++secuenciaIdProducto,
		constructor: function(config){
			this.nombre = config.nombre;
			this.descricpion = config.descricpion;
		},
		getId: function(){
			return this.id;
		},
		getNombre: function(){
			return this.nombre;
		},
		setNombre: function(nombre){
			this.nombre = nombre;
		},
		getDescripcion: function(){
			return this.descricpion;
		},
		setDescripcion: function(descricpion){
			this.descricpion = descricpion;
		},
		toString: function(){
			return "{'id': " + this.id 
				+ ", 'nombre': " + this.nombre
				 + ", 'descricpion': " + this.descricpion +"}";
		}
	}
);

//-------------------------------------------------

Ext.onReady(function(){
	//var c = new Cliente(1, "Victor");
	var c = Ext.create("Cliente", {nombre: "Victor"});

	//var p =  new Producto(1, "Telefono", "Telefono de ultima generacion");
	var p = Ext.create(
			"Producto", 
			{nombre: "Telefono", descricpion: "Telefono de ultima generacion"});

	//var pedido = new Pedido(1, c, [p]);
	var pedido = Ext.create("Pedido", {cliente: c, productos: [p]});

	alert(pedido.toString());
});