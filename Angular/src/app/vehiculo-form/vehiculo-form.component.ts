import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { Vehiculo } from '../vehiculo';
import { VehiculoService } from '../vehiculo.service';

@Component({
  selector: 'app-vehiculo-form',
  templateUrl: './vehiculo-form.component.html',
  styleUrls: ['./vehiculo-form.component.css']
})
export class VehiculoFormComponent implements OnInit {

  constructor(private location: Location,private vehiculoService: VehiculoService) { }
  
  vehiculos: Vehiculo[] = [];
  @Input() vehiculo: Vehiculo = {id:0,tipo:"default",pesoMax:0,combustible:true};

  ngOnInit(): void {
  }
    
  onSubmit(f:NgForm){
    if(f.valid){
      this.vehiculo.tipo=f.value.tipo;
      this.vehiculo.pesoMax=f.value.pesoMax;
      this.vehiculo.combustible=f.value.combustible;
    }

    if (!f) { return; }
      this.vehiculoService.addZona(this.vehiculo)
      .subscribe(vehiculo => {
      this.vehiculos.push(vehiculo);
    });
  }

  goBack(): void {
    this.location.back();
  }
  
  cerrarSesion(){
    localStorage.clear();
  }

}
