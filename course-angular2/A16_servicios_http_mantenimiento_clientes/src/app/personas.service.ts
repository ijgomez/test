import { Persona } from './Persona';
import { MATRIZ_PERSONAS } from './matriz_personas';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable() // Declara esta clase como servicio
export class PersonasService{
    private _personas: Persona[] = MATRIZ_PERSONAS;

    constructor (private http: Http) {

    }

    get personas(): Observable<Persona[]> {
        let url = 'http://localhost:3001/personas';

        return this.http.get(url).map(
            (respuesta: Response) =>  respuesta.json() //Personas[]
         );
        /*
        return Observable.create((observer: Observer<Persona[]>) => {
            setTimeout(() => {
                observer.next(Object.assign([], this._personas));
                observer.complete();
            }, 2000);
        });
         */
    }

    insertar(p: Persona): Observable<Persona> {
        let url = 'http://localhost:3001/personas';

        let cabeceras = new Headers({'Content-type': 'application/json'});
        let opciones = new RequestOptions({ headers: cabeceras});

        return this.http.post(url, p, opciones).map(
            (respuesta: Response) => {
                console.log('====================INSERTADO======================');
                console.log(respuesta.json());
                // return respuesta.json(); // Persona Insertada.
                return this.personas;
            }
        ).catch(
            (respuesta : Response | any) => {
                console.log('====================ERROR======================');
                console.error(respuesta);
                return respuesta;
            }
        );



        //this._personas.push(p);
        //return this.personas;S
    }
    borrar(dni: number): Observable<Persona> {
        /*
        this._personas = this._personas.filter(
            p => p.dni !== dni
        );
        return this.personas;
        */

        let url = 'http://localhost:3001/personas/' + dni;

        return this.http.delete(url).map(
            (respuesta: Response) => {
                console.log('====================BORRADO======================');
                console.log(respuesta.json());
                // return respuesta.json(); // Persona Insertada.
                return respuesta.json();
            }
        ).catch(
            (respuesta: Response | any) => {
                console.log('====================ERROR======================');
                console.error(respuesta);
                return respuesta;
            }
        );
    }
}
