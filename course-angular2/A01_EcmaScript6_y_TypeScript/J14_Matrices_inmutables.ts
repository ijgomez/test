
//Así mismo existe el tipo ReadonlyArray<> que permite definir una matriz inmutable:
let mm: ReadonlyArray<number> = [1,2,3,4];
for (let dato of mm) {
    console.log(dato);
}
//mm[0] = 10; // Error.
//mm.push(5); // Error.
//m = mm; // Error.

let mmm:Array<number> = Object.assign([], mm);
mmm[3] = 55;
for (let dato of mmm) {
    console.log(dato);
}