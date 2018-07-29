import { NgModule, LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }  from './app.component';
import { ElevadoPipe } from './elevado.pipe';
import { FiltrarPipe } from './filtar.pipe';

@NgModule({
  providers: [ {provide: LOCALE_ID, useValue: 'es-ES'}],
  imports:      [ BrowserModule ],
  declarations: [ AppComponent, ElevadoPipe, FiltrarPipe ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
