class Figura2 {

    //atributos
    x; //en ES6 no se declara los atributos, pero en typescript si es obligatorio.
    y;

    // Contructor.....
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }

    // Metodos....

    distancia(otraFigura) { //Raiz de los cuadrados de la diferencia de cada figura.

        let dx = this.x - otraFigura.x;
        let dy = this.y - otraFigura.y;

        return Math.sqrt(dx*dx + dy*dy);
    }
}

class Rectangulo extends Figura2 {

    l1;
    l2;

    constructor (x, y, l1, l2) {
        super(x, y);
        this.l1 = l1;
        this.l2 = l2;
    }

    area () {
        return this.l1*this.l2;
    }

    toString() {
        return "(" + this.x + ", " + this.y + ") - [" + this.l1 +", " + this.l2 + "]";
    }
}

let r1 = new Rectangulo(2, 2, 3, 4);
let r2 = new Rectangulo(3, 3, 1, 1);

console.log("distancia de ambas figuras es " + r1.distancia(r2));
console.log("Area de " + r1 + " es " + r1.area());
console.log("Area de " + r2 + " es " + r2.area());