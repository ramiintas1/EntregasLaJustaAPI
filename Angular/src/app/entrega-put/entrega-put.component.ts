import { Component, OnInit, Input } from '@angular/core';
import { Entrega } from '../entrega';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { EntregaService } from '../entrega.service'; 
import { TransicionEstado } from '../transicion';
import { TransicionService } from '../transicion.service';

@Component({
  selector: 'app-entrega-put',
  templateUrl: './entrega-put.component.html',
  styleUrls: ['./entrega-put.component.css']
})
export class EntregaPutComponent implements OnInit {

  constructor(private route: ActivatedRoute,private location: Location, private entregasService: EntregaService, private transicionService: TransicionService) { }

  @Input() entrega!: Entrega;
  transiciones: TransicionEstado[] = [];
  @Input() transicion: TransicionEstado = {id:0, actual:"default",fecha:"default",motivo:"default",entrega:0};

  ngOnInit(): void {
    this.getEntrega();
  }

  
  onSubmit(){
    this.save();

    setTimeout(() => {
      this.onReprogramado();
    }, 1500);

    setTimeout(() => {
      this.goBack();
    }, 2000);
    
  }
  
  getEntrega(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.entregasService.getEntrega(id)
      .subscribe(entrega => this.entrega = entrega);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.entregasService.updateEntrega(this.entrega)
      .subscribe();
  }
  
  cerrarSesion(){
    localStorage.clear();
  }
  
  onReprogramado(): void{
    this.transicion.actual = "Reprogramado";
    this.transicion.fecha = this.entrega.fecha;
    this.transicion.motivo = this.entrega.motivo;
    this.transicion.entrega = this.entrega.id;

    this.transicionService.addTransicion(this.transicion)
    .subscribe(transicion => {
    this.transiciones.push(transicion);

    //this.delete();
    //this.goBack();
    });
  }
}
