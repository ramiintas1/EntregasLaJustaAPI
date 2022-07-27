import { Component, OnInit } from '@angular/core';
import { Pedido } from '../pedido';
import { Entrega } from '../entrega';
import { PedidoService } from '../pedido.service';
import { EntregaService } from '../entrega.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit {

  filterPed = "";
  pedidos: Pedido[] = [];
  entregas: Entrega[] = [];

  constructor(private pedidoService: PedidoService,private entregasService: EntregaService) { }

  ngOnInit(): void {
    this.getPedidosJusta();
    this.getEntregas();
  }

  getPedidos(): void {
    this.pedidoService.getPedidos()
        .subscribe(pedidos => this.pedidos = pedidos);
  }

  getPedidosJusta(): void {
    this.pedidoService.getToken()
      .subscribe();

    setTimeout(() => {
      this.pedidoService.getPedidosJusta()
      .subscribe(pedidos => this.pedidos = pedidos);
    }, 1500);
    
  }

  getEntregas(): void {
    this.entregasService.getEntregas()
        .subscribe(entregas => this.entregas = entregas);
  }
  
  cerrarSesion(){
    localStorage.clear();
  }

  asignada(id:number): boolean{
    let entrega = this.entregas.filter(entrega => entrega.pedido === id);
    if(entrega.length >0){
      return false;
    }else{
      return true;
    }
  }
}
