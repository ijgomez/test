import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  total: number = 0;

  resultados: number[] = [0, 0];

  ngOnInit(): void {

  }

  calcularTotal(datos: any) {
    console.log(datos);
    this.resultados[datos.num] = datos.resultado || 0;
    this.total = this.resultados.reduce((acumulado, actual) => acumulado + actual);
  }

}
