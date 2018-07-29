import { Component, OnInit, ViewChild } from '@angular/core';
import { PersonasService } from './personas.service';
import { Persona } from './Persona';
import { FormGroup } from "@angular/forms";

@Component({
    selector: 'insertar',
    templateUrl: "./insertar.component.html"
})
export class InsertarComponent implements OnInit {
    nuevo: Persona;
    @ViewChild("frm")
    frm: FormGroup;//Grupo de controles, por ejemplo un formulario.

    constructor(private personasService: PersonasService) {

    }
    ngOnInit() {
        this.nuevo = new Persona();
    }
    insertar() {
        let opcion: string = "a"
        try{
            if (this.frm.valid && opcion == "a") {
                // ------- Opcion "a": Leer los datos mediante [(ngModule)] ----
                this.personasService.insertar(this.nuevo);
                //Para evitar insertar siempre el mismo objeto.
                this.nuevo = new Persona();
                // Pone todos los campo a pristine para que no 
                // muestre errores.
                this.frm.reset();
            } else if (this.frm.valid && opcion == "b") {
                // ------ Opcion "b" Leer los datos con frm.value ----------
                this.personasService.insertar(this.frm.value);
                // Borra los campos y los pone a pristine.
                this.frm.reset();
            }
        }catch(e){
            console.log(e);
        }

    }
}
