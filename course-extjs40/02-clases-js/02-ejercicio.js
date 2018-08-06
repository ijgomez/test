var Cliente = function(id, nombre){
	this.id = id;
	this.nombre = nombre;
};

Cliente.prototype.getId = function(){
	return this.id;
};

Cliente.prototype.getnombre = function(){
	return this.nombre;
};

Cliente.prototype.setNombre = function(nombre){
	this.nombre = nombre;
};

Cliente.prototype.toString = function(){
	return "{'id': " + this.id 
		+ ", 'nombre': " + this.nombre +"}";
};

//-------------------------------------------------

var Pedido = function(id, cliente, productos){
	this.id = id;
	this.cliente = cliente;
	this.productos = productos;

};

Pedido.prototype.getId = function(){
	return this.id;
};

Pedido.prototype.getCliente = function(){
	return this.cliente;
};

Pedido.prototype.addProducto = function(producto){
	this.productos.push(producto);
};

Pedido.prototype.removeProducto = function(posicion){
	this.productos.remove(this.productos[posicion]);
};

Pedido.prototype.toString = function(){
	var resultado = "{'id':" + this.id + ",'cliente': " + this.cliente.toString();

	resultado += ",'productos': [";

	for (var i = 0; i < this.productos.length; i++) {
		resultado += this.productos[i].toString() + ", ";
	};

	resultado += "]}";

	return resultado;
};

//-------------------------------------------------

var Producto = function(id, nombre, descricpion){
	this.id = id;
	this.nombre = nombre;
	this.descricpion = descricpion;
};

Producto.prototype.getId = function(){
	return this.id;
};

Producto.prototype.getNombre = function(){
	return this.nombre;
};

Producto.prototype.setNombre = function(nombre){
	this.nombre = nombre;
};

Producto.prototype.getDescripcion = function(){
	return this.descricpion;
};

Producto.prototype.setDescripcion = function(descricpion){
	this.descricpion = descricpion;
};

Producto.prototype.toString = function(){
	return "{'id': " + this.id 
		+ ", 'nombre': " + this.nombre
		 + ", 'descricpion': " + this.descricpion +"}";
};

//-------------------------------------------------------------

Ext.onReady(function(){
	var c = new Cliente(1, "Victor");

	var p =  new Producto(1, "Telefono", "Telefono de ultima generacion");

	var pedido = new Pedido(1, c, [p]);

	alert(pedido.toString());
});