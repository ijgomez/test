import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import { FormsModule } from "@angular/forms"
import { InsertarComponent } from "./insertar.component";
import { ListadoComponent } from "./listado.component";
import { PersonasService } from "./personas.service";

// Declarar un módulo
@NgModule({
  // depende de estos otros módulos.
  imports:      [ BrowserModule,FormsModule ],
  // Componentes que pertenecen a este módulo
  declarations: [ AppComponent,InsertarComponent, ListadoComponent ],
  // -------- SERVICIO SIGLETON PARA EL MÓDULO -----
  // Compartido con todos los componentes del módulo.
  providers:[PersonasService],
  // Componente principal del módulo.
  // Componente que arranca cuando arranca el módulo.
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
