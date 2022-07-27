import { Component, OnInit, Input  } from '@angular/core';
import { Entrega } from '../entrega';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { EntregaService } from '../entrega.service';
import { PedidoService } from '../pedido.service'; 
import { Pedido } from '../pedido';
import { TransicionEstado } from '../transicion';
import { TransicionService } from '../transicion.service';
import { Router } from '@angular/router';

import { environment } from 'src/environments/environment.prod';
import * as Mapboxgl from 'mapbox-gl';


@Component({
  selector: 'app-entrega-detalle',
  templateUrl: './entrega-detalle.component.html',
  styleUrls: ['./entrega-detalle.component.css']
})
export class EntregaDetalleComponent implements OnInit {

  constructor(private route: ActivatedRoute,private location: Location, private entregasService: EntregaService, private pedidoService: PedidoService, private transicionService: TransicionService, private router: Router) {
  }

  rol:any = localStorage.getItem("rol");

  mapa2!: Mapboxgl.Map;
  marcador!:Mapboxgl.Marker;

  @Input() entrega!: Entrega;

  @Input() pedido!: Pedido;
  pedidos: Pedido[] = [];
  transiciones: TransicionEstado[] = [];
  @Input() transicion: TransicionEstado = {id:0, actual:"default",fecha:"default",motivo:"default",entrega:0};

  ngOnInit(): void {
    this.getEntrega();
    //this.getPedidos();
    this.getTransiciones();
    this.crearMapa();
    this.crearMarcador (-57.95403, -34.92221);
  }

  crearMapa(){
    Mapboxgl!.accessToken =  environment.mapboxKey;
    this.mapa2 = new Mapboxgl.Map({
      container: 'mapa-mapbox2',
      style: 'mapbox://styles/mapbox/streets-v11', 
      center: [-57.95403, -34.92221], 
      zoom: 12 
    });
  }

  crearMarcador(lng:number,lat:number){
    this.marcador = new Mapboxgl.Marker()
    .setLngLat([lng,lat])
    .addTo(this.mapa2);
  }

  getEntrega(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.entregasService.getEntrega(id)
      .subscribe(entrega => this.entrega = entrega);
  }
  
  getPedidos(): void {
    this.pedidoService.getPedidos()
        .subscribe(pedidos => this.pedidos = pedidos);
  }

  getTransiciones(): void {
    this.transicionService.getTransiciones()
        .subscribe(transiciones => this.transiciones = transiciones);
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit1(){
    this.onEntregado();

    setTimeout(() => {
      this.token();
    }, 1500);

    setTimeout(() => {
    this.obtenerPedido()  
    }, 3000);  
    
    setTimeout(() => {
    this.save();
    }, 4500);

    setTimeout(() => {
    this.goBack();
    }, 5000);
  }

  onEntregado(): void{
    this.transicion.actual = "Entregado";
    this.transicion.fecha = this.entrega.fecha;
    this.transicion.motivo = this.entrega.motivo;
    this.transicion.entrega = this.entrega.id;
    
    this.transicionService.addTransicion(this.transicion)
    .subscribe(transicion => {
    this.transiciones.push(transicion);
    });
  }

  onSubmit2(){
      this.transicion.actual = "Cancelado";
      this.transicion.fecha = this.entrega.fecha;
      this.transicion.motivo = this.entrega.motivo;
      this.transicion.entrega = this.entrega.id;
  
    this.transicionService.addTransicion(this.transicion)
    .subscribe(transicion => {
    this.transiciones.push(transicion);

    //this.delete();
    this.goBack();
    });
  }

  delete(){
    this.entregasService.deleteEntrega(this.entrega).subscribe();
  }

  buscarPedido(): void{
    this.pedidoService.getPedido(+this.entrega.pedido)
    .subscribe(pedido => this.pedido = pedido);
  }
  
  onVerUbi(): void {
    //this.buscarPedido();
    this.obtenerPedido();
    setTimeout(() => {
      this.marcador.setLngLat([this.pedido.longitud,this.pedido.latitud]);
    }, 1500);
  }

  token(): void {   //si ya no se usa borrar
    this.pedidoService.getToken()
    .subscribe();
  }

  obtenerPedido(): void{
    this.pedidoService.getPedidoJusta(this.entrega.pedido)
    .subscribe(pedido => this.pedido = pedido);
  }

  save(): void {
    this.pedidoService.updatePedidoJusta2(this.pedido.idJusta)
      .subscribe();// => this.goBack());
  }

  cerrarSesion(){
    localStorage.clear();
  }

}
