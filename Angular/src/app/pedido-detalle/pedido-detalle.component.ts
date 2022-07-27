import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Pedido } from '../pedido';
import { PedidoService } from '../pedido.service';

import { Producto } from '../producto';
import { ProductoService } from '../producto.service'; 

@Component({
  selector: 'app-pedido-detalle',
  templateUrl: './pedido-detalle.component.html',
  styleUrls: ['./pedido-detalle.component.css']
})
export class PedidoDetalleComponent implements OnInit {

  productos:Producto[]=[];
  
  @Input() pedido!: Pedido

  constructor(private route: ActivatedRoute,private location: Location,private pedidoService: PedidoService,private productoService: ProductoService) { }

  ngOnInit(): void {
    //this.getPedido();
    this.getPedidoJusta();
    //this.getProductos();
  }

  getPedidoJusta(): void {
    this.pedidoService.getToken()
      .subscribe();

    setTimeout(() => {
      const id = +this.route.snapshot.paramMap.get('id')!;
      this.pedidoService.getPedidoJusta(id)
      .subscribe(pedido => this.pedido = pedido);
    }, 1500);
    
  }

  getPedido(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.pedidoService.getPedido(id)
      .subscribe(pedido => this.pedido = pedido);
  }

  getProductos(): void {
    this.productoService.getProductos()
        .subscribe(productos => this.productos = productos);
  }
  
  goBack(): void {
    this.location.back();
  }

  cerrarSesion(){
    localStorage.clear();
  }
}
