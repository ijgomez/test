import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'my-app',
	templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  nombre: string = 'anonimo';

  apellidos: string = 'desconocidos';

  ngOnInit(): void {

  }

}
