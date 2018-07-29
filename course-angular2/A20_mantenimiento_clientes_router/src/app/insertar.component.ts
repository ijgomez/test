import { Component, OnInit } from '@angular/core';
import { PersonasService } from './personas.service';
import { Persona } from './Persona';

@Component({
  selector: 'insertar',
  templateUrl:"./insertar.component.html"
})
export class InsertarComponent implements OnInit { 
    nuevo:Persona;
    constructor(private personasService:PersonasService){
      
    }
    ngOnInit(){
        this.nuevo = new Persona();
    }
    insertar(){
      this.personasService.insertar(this.nuevo);
      this.nuevo = new Persona();//Para evitar insertar siempre el mismo objeto.
    }
}
