
let personas = [
    {nombre : "Pepe", apellidos : "García"},
    {nombre : "Luis", apellidos : "Tojar"},
    {nombre : "Marta", apellidos : "Gomez"},
    {nombre : "Alicia", apellidos : "Gonzalez"},
    {nombre : "Ana", apellidos : "García"}
];

console.log("ES6 introduce el for let of....");
for (let persona of personas) {
    console.log(persona.nombre);
}

console.log("ES5 introduce el for var in....");
//Recorreer todos los atributos de un objeto.
let persona = {nombre : "Ana", apellidos : "García"};
for (var campo in persona) {
    console.log(campo + "->" +  persona[campo]);
}