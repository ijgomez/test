import { Component, OnInit } from '@angular/core';
import { PersonasService } from "./personas.service";

@Component({
  selector: 'my-app',
  templateUrl:"app/app.component.html"
  // Compartido con todos los componentes anidados
  // providers:[PersonasService]
})
export class AppComponent implements OnInit { 
    ngOnInit(){
       
    }
}
