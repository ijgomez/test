import {CalculadoraServicio} from './CalculadoraServicio';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';


@Component({
  selector: 'calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.css']
})
export class CalculadoraComponent implements OnInit {

  calc: CalculadoraServicio;

  isNaN = isNaN;

  @Input() // Atributo de entrada de la etiqueta calculadora.
  pos: string;
  num: number;

  @Input()
  valoresInciales: any;

  @Output()
  resultadoNuevo: EventEmitter<any> = new EventEmitter(); // Evento que lanza la etiqueta Calculadora y que podra ver otros componentes al ser un dato de salida.

  ngOnInit(): void {
    this.calc = new CalculadoraServicio();
    this.num = parseInt(this.pos, 10); // si esta instruccion se hiciera desde el contructor no funcionaria, ya que todavia no se han inyectado los valores necesarios.

    if (this.valoresInciales) {
      this.calc.txta = this.valoresInciales.txta;
      this.calc.txtb = this.valoresInciales.txtb;

      this.calc.operacion = this.valoresInciales.operacion;
    }
  }

 calcular() {
   this.calc.calcular();
  // Lanzamos el evento.
  // en el argumento del emit se le pasa los datos que queremos enviar a los componentes exteriores.
  this.resultadoNuevo.emit({num: this.num, resultado: this.calc.resultado});
 }

}
