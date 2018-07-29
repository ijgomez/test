import { Persona } from './Persona';
import { MATRIZ_PERSONAS } from './matriz_personas';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';

@Injectable() // Declara esta clase como servicio
export class PersonasService{
    private _personas: Persona[] = MATRIZ_PERSONAS;

    get personas(): Observable<Persona[]> {
        return Observable.create((observer: Observer<Persona[]>) => {
            setTimeout(() => {
                observer.next(Object.assign([], this._personas));
                observer.complete();
            }, 2000);
        });
    }

    insertar(p: Persona): Observable<Persona[]> {
        this._personas.push(p);
        return this.personas;
    }
    borrar(dni: number): Observable<Persona[]> {
        this._personas = this._personas.filter(
            p => p.dni !== dni
        );
        return this.personas;
    }
}
