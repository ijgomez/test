import { Component, OnInit } from '@angular/core';
import { Persona } from './Persona';
import { PersonasService } from './personas.service';
import { FormGroup, FormControl, Validators } from "@angular/forms";

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
    personas: Persona[];
    nuevo: Persona;

    mensaje: string;

    // ---- ATRIBUTOS DEL MODEL DE VALIDACION ------
    frm: FormGroup; // Un grupo de controles de un formulario.

    dni: FormControl; // un control, tienen una propiedad errors, dirty, valid, pristine que indican el estado del control.
    nombre: FormControl;  //si nadie ha tocado el formulario, estara en estado pristine y untouch.
    apellidos: FormControl;
    saldo: FormControl;

    constructor(private personasService: PersonasService) {
    }
    ngOnInit(): void {
       this.personas = this.personasService.personas;
       this.nuevo = new Persona();

       //Configuracion de la validacion.
       this.dni = new FormControl('', Validators.required);
       this.nombre = new FormControl('', Validators.required);
       this.apellidos = new FormControl('', [Validators.required, Validators.minLength(2)]);
       this.saldo = new FormControl('', Validators.required);

       this.frm = new FormGroup({
           dni: this.dni,
           nombre: this.nombre,
           apellidos: this.apellidos,
           saldo: this.saldo
       });
    }
    insertar(event: any): void {
        let opcion :string = 'b';
        if (this.frm.valid && opcion === 'a') {
            //Opcion a - leer los datos del ngModule.
            this.personas = this.personasService.insertar(this.nuevo);
            this.nuevo = new Persona();

            // Poner todos los campos del formulario a pristine. (inicializar el formulario)
            this.frm.markAsPristine();
        } else if (this.frm.valid && opcion === 'b') {
            //Opcion b - leer los datos del formulario.
            this.personas = this.personasService.insertar(this.frm.value);
            this.nuevo = new Persona();

            // Poner todos los campos del formulario a pristine. (inicializar el formulario)
            this.frm.markAsPristine();
        }
        
    }

    borrar(persona: Persona): void {
      this.personas = this.personasService.borrar(persona.dni);
      this.mensaje = `${persona.nombre} borrado correctamente` ;
      setTimeout(() => this.mensaje = undefined, 2000);
    }
}
