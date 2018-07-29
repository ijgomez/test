let numero = [1, 2, 3, 4, 5, 6, 7, 8];

console.log("------------------- Numeros ----------------------");
numero.forEach(num => console.log(num));

console.log("------------------- pares ----------------------");
let pares = numero.filter( num => num%2==0);
pares.forEach(num => console.log(num));

console.log("------------------- suma ----------------------");
let suma = numero.reduce( (acumulado, actual) => acumulado + actual);
console.log("Suma: " + suma);