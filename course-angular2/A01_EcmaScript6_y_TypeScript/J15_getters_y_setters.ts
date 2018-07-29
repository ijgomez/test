class Circulo {

    public x : number;

    public y : number;

    public r : number;

    private _nombre : string;

    constructor (x, y, r) {
        this.x = x;
        this.y = x;
        this.r = r;
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


let cir = new Circulo(1, 5, 2);
console.log(cir.nombre);
console.log(cir.area());
cir.nombre = 'Otro nombre';
console.log(cir.nombre);