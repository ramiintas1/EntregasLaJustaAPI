import { Component, OnInit } from '@angular/core';
import { Entrega } from '../entrega';
import { EntregaService } from '../entrega.service';

@Component({
  selector: 'app-entregas',
  templateUrl: './entregas.component.html',
  styleUrls: ['./entregas.component.css']
})
export class EntregasComponent implements OnInit {

  entregas: Entrega[] = [];

  constructor(private entregasService: EntregaService) { }

  ngOnInit(): void {
    this.getEntregas();
  }

  getEntregas(): void {
    this.entregasService.getEntregas()
        .subscribe(entregas => this.entregas = entregas);
  }

  cerrarSesion(){
    localStorage.clear();
  }
}
