import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import { FormsModule } from "@angular/forms"
import { InsertarComponent } from "./insertar.component";
import { ListadoComponent } from "./listado.component";
import { PersonasService } from "./personas.service";
import { RouterModule, Routes } from "@angular/router";
import { NoEncontradaComponent } from "./no-encontrada.component";
import { DetalleComponent } from "./detalle.component";

const routerConfig:Routes=[
  {path:"insertar",component:InsertarComponent},
  {path:"listado",component:ListadoComponent},
  {path:"detalle/:dni",component:DetalleComponent},
  {path:"",redirectTo:"/listado",pathMatch:"full"},
  {path:"**",component:NoEncontradaComponent}
];

// Declarar un módulo
@NgModule({
  // depende de estos otros módulos.
  imports:      [ BrowserModule,
                FormsModule,
                RouterModule.forRoot(routerConfig) ],
  // Componentes que pertenecen a este módulo
  declarations: [ AppComponent,
                  InsertarComponent, 
                  ListadoComponent,
                  DetalleComponent,
                  NoEncontradaComponent ],
  // -------- SERVICIO SIGLETON PARA EL MÓDULO -----
  // Compartido con todos los componentes del módulo.
  providers:[PersonasService],
  // Componente principal del módulo.
  // Componente que arranca cuando arranca el módulo.
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
