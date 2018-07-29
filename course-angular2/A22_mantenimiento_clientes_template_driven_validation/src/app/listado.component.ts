import { Component, OnInit } from '@angular/core';
import { PersonasService } from './personas.service';
import { Persona } from './Persona';

@Component({
  selector: 'listado',
  templateUrl:"./listado.component.html"
})
export class ListadoComponent implements OnInit { 
    personas:Persona[];
    constructor(private personasService:PersonasService){
      
    }
    
    ngOnInit(){
        this.personas = this.personasService.personas;//Método get.
    }
  
    borrar(p:Persona){
      this.personasService.borrar(p.dni);
      this.personas = this.personasService.personas;
    }
}
