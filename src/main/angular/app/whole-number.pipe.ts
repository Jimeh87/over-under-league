import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  standalone: true,
  name: 'wholeNumber'
})
export class WholeNumberPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (Number.isNaN(value)) {
      return value;
    }

    return value > 0 ? "+" + value : value;
  }

}
