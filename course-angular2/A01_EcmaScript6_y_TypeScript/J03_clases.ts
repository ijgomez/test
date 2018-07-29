class Figura {

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

let f1 = new Figura(2, 2);
let f2 = new Figura(3, 3);

console.log("distancia de ambas figuras es " + f1.distancia(f2));