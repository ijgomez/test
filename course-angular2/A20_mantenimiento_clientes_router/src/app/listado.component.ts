import { Component, OnInit } from '@angular/core';
import { PersonasService } from './personas.service';
import { Persona } from './Persona';
import { Router } from "@angular/router";

@Component({
  selector: 'listado',
  templateUrl:"./listado.component.html"
})
export class ListadoComponent implements OnInit { 
    personas:Persona[];
    constructor(private personasService:PersonasService, 
          private router:Router){
      
    }
    ngOnInit(){
        this.personas = this.personasService.personas;//Método get.
    }
  
    borrar(p:Persona){
      this.personasService.borrar(p.dni);
      this.personas = this.personasService.personas;
    }
    verDetalle(p:Persona){
      this.router.navigate(["/detalle/"+p.dni]);
    }
}
