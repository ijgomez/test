let m = [
    {nombre : "Pepe", apellidos : "García"},
    {nombre : "Luis", apellidos : "Tojar"},
    {nombre : "Marta", apellidos : "Gomez"},
    {nombre : "Alicia", apellidos : "Gonzalez"},
    {nombre : "Ana", apellidos : "García"}
];

//ES5
console.log("---------------ES5---------------");

m.forEach(function (persona, i, matriz) {
    console.log(i + " .- " + persona.nombre);
});

//ES6
console.log("---------------ES6---------------");

m.forEach((persona, i, matriz) => {
    console.log(i + " .- " + persona.nombre);
});

console.log("---------------ES6 (sin llaves)---------------");

// si la funcion solo contiene una instruccion o comando, no es necesario el uso de llaves
m.forEach((persona, i) => 
    console.log(i + " .- " + persona.nombre)
);

