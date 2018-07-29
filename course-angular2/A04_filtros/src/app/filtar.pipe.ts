import { Pipe, PipeTransform } from '@angular/core';


@Pipe({ name: 'filtrar' })
export class FiltrarPipe implements PipeTransform {

    transform(valor: any[], criterio: string): any[] {

        return valor.filter(persona => persona.apellidos.indexOf(criterio) >= 0);

    }

}
