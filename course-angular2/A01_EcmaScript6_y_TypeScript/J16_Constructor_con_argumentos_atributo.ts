class Circulo2 {

    

    private _nombre : string;

    //si los argumentos del contructor van precedido s de un modificador de acceso, aotomativamente los argumentos qeudan definidos como atribuos de la clase. y tomaran los valors que se pasen en el contructor.
    constructor (public x : number, public y : number, public r : number) {
        this._nombre = 'Nombre del Circulo';
    }

    public area() : number {
        return Math.PI * this.r * this.r;
    }

    public get nombre() : string {
        return "#####" + this._nombre + "#####";
    }

    public set nombre(nom:string) {
        this._nombre = nom;
    }

}


let cir2 = new Circulo2(1, 5, 2);
console.log(cir2.nombre);
console.log(cir2.area());
cir2.nombre = 'Otro nombre';
console.log(cir2.nombre);