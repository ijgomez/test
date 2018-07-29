import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'elevado' })
export class ElevadoPipe implements PipeTransform {

    transform(value: number, exponente: number = 1) {
        return Math.pow(value, exponente);
    }

}
