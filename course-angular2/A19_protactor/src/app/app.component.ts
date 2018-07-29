import { Component, OnInit } from '@angular/core';
import { Persona } from './Persona';
import { PersonasService } from './personas.service';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
    personas: Persona[];
    nuevo: Persona;

    mensaje: string;

    // Los argumentos que del constructor del componente
    // serán los objetos (servicios) que queremos
    // que Angular nos inyecte.
    constructor(private personasService: PersonasService) {
    }
    ngOnInit(): void {
       this.personas = this.personasService.personas;
       this.nuevo = new Persona();
    }
    insertar(event: any): void {
        this.personas = this.personasService.insertar(this.nuevo);
        this.nuevo = new Persona();
    }

    borrar(persona: Persona): void {
      this.personas = this.personasService.borrar(persona.dni);
      this.mensaje = `${persona.nombre} borrado correctamente` ;
      setTimeout(() => this.mensaje = undefined, 2000);
    }
}
