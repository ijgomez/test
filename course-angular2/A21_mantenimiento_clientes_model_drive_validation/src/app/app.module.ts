import { NgModule, LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PersonasService } from './personas.service';


@NgModule({
  // Delcaramos el servicio como inyectable en este módulo.
  providers: [ {provide: LOCALE_ID, useValue: 'es-ES'}, PersonasService],
  imports:      [ BrowserModule, FormsModule, ReactiveFormsModule ],
  declarations: [ AppComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
