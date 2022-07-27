import { Component, OnInit, Input  } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { Repartidor } from '../repartidor';
import { Rol } from '../rol';
import { EstadoRepartidor } from '../estadoRepartidor';
import { Vehiculo } from '../vehiculo';
import { Zona } from '../zona';

import { RepartidoresComponent } from '../repartidores/repartidores.component';

import { RepartidorService } from '../repartidor.service';
import { RolService } from '../rol.service';
import { EstadorepartidorService } from '../estadorepartidor.service';
import { VehiculoService } from '../vehiculo.service';
import { ZonaService } from '../zona.service';


@Component({
  selector: 'app-repartidor-form',
  templateUrl: './repartidor-form.component.html',
  styleUrls: ['./repartidor-form.component.css']
})
export class RepartidorFormComponent implements OnInit {

  repartidores: Repartidor[] = [];
  roles: Rol[] = [];
  estados: EstadoRepartidor[] = [];
  vehiculos: Vehiculo[] = [];
  zonas: Zona[] = [];

  @Input() repartidor: Repartidor  = {id:0, nombre:"default", apellido:"default",rol:"default", vehiculo:"default",fechaN:"default", direccion:"default",email:"default",dni:"default",contrasena:"default",estado:"default",zona:"default"};
  
  constructor(private location: Location, private repartidorService: RepartidorService, private rolService: RolService, private estadoRepartidorService: EstadorepartidorService, private vehiculoService: VehiculoService, private zonaService: ZonaService) { }

  ngOnInit(): void {
    this.getRoles();
    this.getVehiculos();
    this.getEstadosR();
    this.getZonas();
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

  onSubmit(f:NgForm){
    if(f.valid){
      this.repartidor.id=f.value.id;
      this.repartidor.nombre=f.value.nombre;
      this.repartidor.apellido=f.value.apellido;
      this.repartidor.rol=f.value.rol;
      this.repartidor.vehiculo=f.value.vehiculo;
      this.repartidor.fechaN=f.value.fechaN;
      this.repartidor.direccion=f.value.direccion;
      this.repartidor.email=f.value.email;
      this.repartidor.dni=f.value.dni;
      this.repartidor.contrasena=f.value.contrasena;
      this.repartidor.estado=f.value.estado;
      this.repartidor.zona=f.value.zona;
    }

    if (!f) { return; }
      this.repartidorService.addRepartidor(this.repartidor)
      .subscribe(repartidor => {
      this.repartidores.push(repartidor);
    });
  }

  goBack(): void {
    this.location.back();
  }
  
  cerrarSesion(){
    localStorage.clear();
  }
}
