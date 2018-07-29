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

    procesando: boolean;

    // Los argumentos que del constructor del componente
    // serán los objetos (servicios) que queremos
    // que Angular nos inyecte.
    constructor(private personasService: PersonasService) {
    }
    ngOnInit(): void {
        this.procesando = true;
        this.personasService.personas.subscribe(
            datos => {
                this.personas = datos;
                this.procesando = false;
            }
        );
       this.nuevo = new Persona();
       
    }

    insertar(event: any): void {
        this.procesando = true;
         this.personasService.insertar(this.nuevo).subscribe(
            persona => {
                //Persona insertada correctamente.
               this.personasService.personas.subscribe(
                    datos => {
                        this.personas = datos;
                         this.nuevo = new Persona();
                        this.procesando = false;
                    }
                );
            }
        );
        this.nuevo = new Persona();
    }

    borrar(persona: Persona): void {
        this.procesando = true;
      this.personasService.borrar(persona.dni).subscribe(
           p => {
                //Persona  borrada correctamente.
               this.personasService.personas.subscribe(
                    datos => {
                        this.personas = datos;
                          this.mensaje = `${persona.nombre} borrado correctamente` ;
                this.procesando = false;
                setTimeout(() => this.mensaje = undefined, 2000);
                    }
                );
            }
           );
     
    }
}
