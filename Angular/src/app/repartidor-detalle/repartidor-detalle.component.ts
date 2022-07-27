import { noUndefined } from '@angular/compiler/src/util';
import { Component, OnInit, Input } from '@angular/core';
import { Repartidor } from '../repartidor';
import { Entrega } from '../entrega';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { RepartidorService } from '../repartidor.service';
import { EntregaService } from '../entrega.service'; 
import { RolService } from '../rol.service';
import { EstadorepartidorService } from '../estadorepartidor.service';
import { VehiculoService } from '../vehiculo.service';
import { ZonaService } from '../zona.service';

import { Rol } from '../rol';
import { EstadoRepartidor } from '../estadoRepartidor';
import { Vehiculo } from '../vehiculo';
import { Zona } from '../zona';

@Component({
  selector: 'app-repartidor-detalle',
  templateUrl: './repartidor-detalle.component.html',
  styleUrls: ['./repartidor-detalle.component.css']
})
export class RepartidorDetalleComponent implements OnInit {

  rol:any = localStorage.getItem("rol");

  entregas: Entrega[] = [];
  roles: Rol[] = [];
  estados: EstadoRepartidor[] = [];
  vehiculos: Vehiculo[] = [];
  zonas: Zona[] = [];

  @Input() repartidor!: Repartidor;
 
  constructor(private route: ActivatedRoute,private repartidorService: RepartidorService,private location: Location, private entregasService: EntregaService, private rolService: RolService, private estadoRepartidorService: EstadorepartidorService, private vehiculoService: VehiculoService, private zonaService: ZonaService) { }

  ngOnInit(): void {
    this.getRepartidor();
    this.getEntregas();
    this.getRoles();
    this.getVehiculos();
    this.getEstadosR();
    this.getZonas();
  }

  getRepartidor(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.repartidorService.getRepartidor(id)
      .subscribe(repartidor => this.repartidor = repartidor);
  }

  getEntregas(): void {
    this.entregasService.getEntregas()
        .subscribe(entregas => this.entregas = entregas);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.repartidorService.updateHero(this.repartidor)
      .subscribe(() => this.goBack());
  }

  getLogueado(): any{
    return localStorage.getItem('rol');
  }

  getRoles(): void{
    this.rolService.getRoles().subscribe(roles => this.roles = roles);
  }

  getEstadosR(): void{
    this.estadoRepartidorService.getEstadosR().subscribe(estados => this.estados = estados);
  }

  getVehiculos(): void{
    this.vehiculoService.getVehiculos().subscribe(vehiculos => this.vehiculos = vehiculos);
  }

  getZonas(): void{
    this.zonaService.getZonas().subscribe(zonas => this.zonas = zonas);
  }

  cerrarSesion(){
    localStorage.clear();
  }

}
