import { Component, OnInit } from '@angular/core';
import { Repartidor } from '../repartidor';
import { RepartidorService } from '../repartidor.service';

@Component({
  selector: 'app-repartidores',
  templateUrl: './repartidores.component.html',
  styleUrls: ['./repartidores.component.css']
})
export class RepartidoresComponent implements OnInit {

  filterRep = "";

  repartidores: Repartidor[] = [];

  constructor(private repartidorService: RepartidorService) { 
    this.repartidorService.getRepartidores().subscribe(repartidores => this.repartidores = repartidores);
  }

  ngOnInit(): void {
    this.getRepartidores();
  }

  getRepartidores(): void {
    this.repartidorService.getRepartidores().subscribe(repartidores => this.repartidores = repartidores);
  }

  delete(repartidor: Repartidor): void {
    this.repartidores = this.repartidores.filter(r => r !== repartidor);
    this.repartidorService.deleteRepartidor(repartidor).subscribe();
  }

  cerrarSesion(){
    localStorage.clear();
  }
}
