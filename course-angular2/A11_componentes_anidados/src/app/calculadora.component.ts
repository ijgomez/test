import {CalculadoraServicio} from './CalculadoraServicio';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.css']
})
export class CalculadoraComponent implements OnInit {

  calc: CalculadoraServicio;

  isNaN = isNaN;

  ngOnInit(): void {
    this.calc = new CalculadoraServicio();
  }


}
