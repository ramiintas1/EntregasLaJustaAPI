import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(value: any, arg: any): any {
    const resultRep = [];
    for(const repartidor of value){
      if(repartidor.nombre.toLowerCase().indexOf(arg.toLowerCase()) > -1){
        resultRep.push(repartidor)
      }
    }
    return resultRep;
  }

}
