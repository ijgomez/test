import { NgModule, LOCALE_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }  from './app.component';
import { CalculadoraComponent } from './calculadora.component';


@NgModule({
  providers: [ {provide: LOCALE_ID, useValue: 'es-ES'}],
  imports:      [ BrowserModule, FormsModule ],
  declarations: [ AppComponent, CalculadoraComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
