import { Component, OnInit } from '@angular/core';
import { MATRIZ_PERSONAS } from './matriz_personas';
import { Persona } from './Persona';

@Component({
  selector: 'my-app',
	templateUrl: './app.component.html'
//  template: `<h1>Hello {{propietario}}</h1>
//	<p>La hora actual es {{hora()}}<p>`,
})
export class AppComponent implements OnInit  {

  propietario = 'Player One';

	personas: Persona[];

  texto: string = `En un lugar de la Mancha
                  de cuyo nombre no quiero acordarme
                  no ha mucho tiempo...`;

// Se ejecutara cuando Angular y todos sus elementos esten iniciados.
  ngOnInit(): void {
		this.personas = MATRIZ_PERSONAS;
  }

  ahora(): Date {
    let d = new Date();
    //return d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
    return d;
  }
}
