import { Persona } from './Persona';
import { MATRIZ_PERSONAS } from './matriz_personas';
import { Injectable } from '@angular/core';

@Injectable() // Declara esta clase como servicio
export class PersonasService{
    private _personas: Persona[] = MATRIZ_PERSONAS;

    get personas(){
        return Object.assign([], this._personas);
    }

    insertar(p: Persona): Persona[] {
        this._personas.push(p);
        return this.personas;
    }
    borrar(dni: number): Persona[] {
        this._personas = this._personas.filter(
            p => p.dni !== dni
        );
        return this.personas;
    }
}
