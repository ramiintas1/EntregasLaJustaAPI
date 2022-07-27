import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterPedidos'
})
export class FilterPedidosPipe implements PipeTransform {

  transform(value: any, arg: any): any {
    const resultPed = [];
    for(const pedido of value){
      if(pedido.nombreCliente.toLowerCase().indexOf(arg.toLowerCase()) > -1){
        resultPed.push(pedido)
      }
    }
    return resultPed;
  }

}
