interface Sumable {
    valor:number;
    base?:number;//Atributo opcional
    sumar(otro:Sumable):Sumable
}

class NumeroReal implements Sumable {
    valor: number;

    constructor(valor: number) {
        this.valor = valor; 
    }

    sumar(otro: Sumable): Sumable {
        if (!otro.base) {//comprobamos si el atributo opcional 'base' esta presente.
            return new NumeroReal(this.valor + otro.valor);    
        } else { // si tiene el atributo opcional 'base'.
            throw new Error("Falta implementar....");
        }
        
    }

    toString():string {
        return this.valor + "R";
    }
}


let n1:Sumable = new NumeroReal(3);
let n2:Sumable = new NumeroReal(6);
let n3:Sumable = n1.sumar(n2);

console.log(`${n1} + ${n2} = ${n3}`);