import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';


@Component({
  selector: 'my-app',
	templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  veces: number = 0;
  tiempo: number = 0;

  @ViewChild('reproductor')
  repro: ElementRef; // Referencia a la etiqueta video.

  ngOnInit(): void {

  }

  incrementar(): void {
    this.veces++;
  }

  cambioTiempo(event: any) {
    this.tiempo = event.target.currentTime;
  }

  parar() {
    this.repro.nativeElement.pause();
  }

  mover(event: any) {
    this.repro.nativeElement.currentTime = event.target.value;
  }
}
