import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'my-app',
	templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {


  imagenes: string[] = ['tigre', 'oso', 'serpiente'];

  foto1: string;

  foto2: string;

  foto3: string;

  veces: number = 0;

  ngOnInit(): void {
    this.jugar();

    let fun = () => {
      //this.foto3 = this.foto();
      this.foto3 = this.imagenes[Math.floor(Math.random() * this.imagenes.length)];
      console.log(this.foto3);
      setTimeout(fun, 2000);
    };
    setTimeout(fun, 2000);
  }

  foto() {
    let num = Math.floor(Math.random() * this.imagenes.length);
    return this.imagenes[num];
  }

  jugar() {
    this.foto1 = this.foto();
    this.foto2 = this.foto();
    this.foto3 = this.foto();
    this.veces++;
  }

  esPremio(): boolean {
    return this.foto1 === this.foto2 && this.foto1 === this.foto3;
  }
}
