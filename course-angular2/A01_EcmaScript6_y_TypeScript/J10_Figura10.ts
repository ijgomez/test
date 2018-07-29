// Modulo que expone la clase Figura 10

// Mediante export indicamos que esta visible fuera del modulo.
export class Figura10 {

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