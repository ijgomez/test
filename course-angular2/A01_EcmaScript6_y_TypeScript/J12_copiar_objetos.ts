let madre = { dni:22, nombre:"Alicia", apellidos:"Gonzalez"};

let madre2 = madre;

if (madre2 == madre) {
    console.log("1.- Con '=' tenemos dos referencias al mismo objeto, pero no dos objetos");
}

let hija = Object.assign({}, madre);

if (hija != madre) {
    console.log("2.- Con Assign tenemos distintos objetos.");
    console.log("Pero tienen los mismos attributos.");
    console.log(`Apellidos ${hija.apellidos}  ${madre.apellidos}`);
}

let nieta = Object.assign({provincia:'Madrid', dni:33}, madre);
console.log("Attibutos de la nieta");
for (let campo in nieta) {
    console.log(campo + '->' + nieta[campo]);
}
console.log(nieta);