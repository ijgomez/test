import { Component } from '@angular/core';


@Component({
  selector: 'my-app',
	templateUrl: './app.component.html'
})
export class AppComponent  {

  imagenes: string[] = ['tigre', 'oso', 'serpiente'];

  saludo:string = 'Hola';

  foto1: string = 'img/tigre.png';

  foto2: string = undefined;

  foto() {
    let num = Math.floor(Math.random() * this.imagenes.length);
    return this.imagenes[num];
  }
}
