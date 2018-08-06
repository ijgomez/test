//Funcion constructora
var Persona = function(id, nombre){
	//Definir los campos, las caraccteristicas de la tipologia

	this.id = id;
	this.nombre = nombre;

};

//prototype - Definición de metodos
Persona.prototype.saludar = function(){
	alert("Hola " + this.nombre + "!!!!!");
}



Ext.onReady(function(){

	//Crear objetos JS
	var p1 = new Persona(1, "Victor");

	p1.otroMetodo = function(){
		alert(this.id + this.nombre);
	}

	p1.saludar();

	p1.otroMetodo();

	var p2 = new Persona(2, "Juan");

	p2.saludar();

	//p2.otroMetodo(); //Da error

});