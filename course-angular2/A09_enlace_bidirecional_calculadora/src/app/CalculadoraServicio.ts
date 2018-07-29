export class CalculadoraServicio {
  txta: string;
  txtb: string;
  operacion: string;
  resultado: number = undefined;
  operaciones =[
 {valor: '+', texto: "sumar" },{valor:'-',texto: 'restar'},
		{valor:'x', texto:'mult.'}, {valor:'/', texto: 'div'}
	];
	constructor(txta: string= undefined, txtb: string= undefined,
			 operacion: string= undefined){
		this.txta = txta;
		this.txtb = txtb;
		this.operacion = operacion;
	}
	calcular() {
		let a = parseFloat(this.txta);
		let b = parseFloat(this.txtb);
		if (this.operacion == '+') {
			this.resultado = a + b;
		} else if (this.operacion === '-') {
			this.resultado = a - b;
		} else if (this.operacion === 'x') {
			this.resultado = a * b;
		} else if (this.operacion === '/') {
			this.resultado = a / b;
		}
	}
}