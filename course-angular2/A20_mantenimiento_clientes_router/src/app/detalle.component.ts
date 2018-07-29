import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Subscription } from "rxjs/Subscription";

@Component({
    template: `<h1>Detalle del cliente con dni: {{dni}}</h1>
                <p>{{hora}}</p>`
})
export class DetalleComponent implements OnInit, OnDestroy {
    dni: number = 0;
    hora: Date;
    subscriptor: Subscription;
    constructor(private route: ActivatedRoute) {
        this.hora = new Date();
    }
    ngOnInit() {
        this.subscriptor = this.route.params.subscribe(
            params => this.dni = params["dni"]
        );
    }
    ngOnDestroy(): void {
        this.subscriptor.unsubscribe();
    }
}
