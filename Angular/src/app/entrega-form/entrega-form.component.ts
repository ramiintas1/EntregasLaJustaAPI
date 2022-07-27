import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms';

import { Repartidor } from '../repartidor';
import { Entrega } from '../entrega';
import { Pedido } from '../pedido';
import { EstadoEntrega } from '../estadoEntrega';
import { Zona } from '../zona';

import { RepartidorService } from '../repartidor.service';
import { PedidoService } from '../pedido.service';
import { EntregaService } from '../entrega.service';
import { EstadoentregaService } from '../estadoentrega.service';
import { ZonaService } from '../zona.service';
import { environment } from 'src/environments/environment';

import * as Mapboxgl from 'mapbox-gl';


@Component({
  selector: 'app-entrega-form',
  templateUrl: './entrega-form.component.html',
  styleUrls: ['./entrega-form.component.css']
})
export class EntregaFormComponent implements OnInit {

  mapa!: Mapboxgl.Map;
  marcador!:Mapboxgl.Marker;

  selectedPedido!: Pedido;

  entregas: Entrega[] = [];
  pedidos: Pedido[] = [];
  repartidores: Repartidor[] = [];
  estados: EstadoEntrega[] = [];
  zonas: Zona[]= [];

  @Input() entrega: Entrega = {id:0,zona:"default",dniRepartidor:"default",estado:"default",fecha:"default",motivo:"default",pedido:0};//,pedido:this.pedidos[0]

  constructor(private pedidoService: PedidoService, private entregaService: EntregaService, private repartidorService: RepartidorService, private estadoEntregaService: EstadoentregaService, private zonaService: ZonaService) { }

  ngOnInit(): void {
    this.getEntregas();
    //this.getPedidos();
    this.getPedidosJusta();
    this.getRepartidores();
    this.getEstados();
    this.getZonas();
    
    Mapboxgl!.accessToken =  environment.mapboxKey;
      this.mapa = new Mapboxgl.Map({
      container: 'mapa-mapbox', // container ID
      style: 'mapbox://styles/mapbox/streets-v11', // style URL
      center: [-57.95403, -34.92221], // starting position -34.9205233,-57.9882756,13
      zoom: 12 // starting zoom
    });
    this.crearMarcador (-57.95403, -34.92221);
    this.onSelect(this.selectedPedido);
    
  }

  onSelect(pedido: Pedido): void {
    this.selectedPedido = pedido;
    this.marcador.setLngLat([pedido.longitud,pedido.latitud]);
  }

  crearMarcador(lng:number,lat:number){
    this.marcador = new Mapboxgl.Marker()
    .setLngLat([lng,lat])
    .addTo(this.mapa);
  }

  getEntregas(): void{
    this.entregaService.getEntregas().subscribe(entregas => this.entregas = entregas);
  }

  getPedidos(): void{
    this.pedidoService.getPedidos().subscribe(pedidos => this.pedidos = pedidos);
  }

  getPedidosJusta(): void{
    this.pedidoService.getPedidosJusta().subscribe(pedidos => this.pedidos = pedidos);
  }

  getRepartidores(): void {
    this.repartidorService.getRepartidores().subscribe(repartidores => this.repartidores = repartidores);
  }

  getEstados(): void {
    this.estadoEntregaService.getEstadosR().subscribe(estados => this.estados = estados);
  }

  getZonas(): void {
    this.zonaService.getZonas().subscribe(zonas => this.zonas = zonas);
  }

  onSubmit(f:NgForm){
    if(f.valid){
      this.entrega.zona=f.value.zona;
      this.entrega.dniRepartidor=f.value.dniRepartidor;
      this.entrega.estado=f.value.estado;
      this.entrega.fecha=f.value.fecha;
      this.entrega.motivo=f.value.motivo;
      this.entrega.pedido=f.value.pedido;
      this.entrega.pedido=this.selectedPedido.idJusta;
    }
    if (!f) { return; }
    this.entregaService.addEntregas(this.entrega)
    .subscribe(entrega => {
    this.entregas.push(entrega);
    });
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
