import { Component, OnInit } from '@angular/core';
import { Vehiculo } from '../vehiculo';
import { VehiculoService } from '../vehiculo.service';

@Component({
  selector: 'app-vehiculos',
  templateUrl: './vehiculos.component.html',
  styleUrls: ['./vehiculos.component.css']
})
export class VehiculosComponent implements OnInit {

  vehiculos: Vehiculo[] = [];

  constructor(private vehiculoService: VehiculoService) { }

  ngOnInit(): void {
    this.getVehiculos();
  }

  getVehiculos(): void {
    this.vehiculoService.getVehiculos().subscribe(vehiculos => this.vehiculos = vehiculos);
  }

  delete(vehiculo: Vehiculo): void {
    this.vehiculos = this.vehiculos.filter(r => r !== vehiculo);
    this.vehiculoService.deleteVehiculo(vehiculo).subscribe();
  }

  cerrarSesion(){
    localStorage.clear();
  }

}
