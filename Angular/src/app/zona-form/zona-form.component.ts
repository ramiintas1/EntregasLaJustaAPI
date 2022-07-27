import { Component, OnInit, Input } from '@angular/core';
import { ZonaService } from '../zona.service';
import { Zona } from '../zona';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-zona-form',
  templateUrl: './zona-form.component.html',
  styleUrls: ['./zona-form.component.css']
})
export class ZonaFormComponent implements OnInit {

  constructor(private location: Location,private zonaService: ZonaService) { }

  zonas: Zona[] = [];
  @Input() zona: Zona = {id:0,barrio:"defalut",desdeLat:0,hastaLat:0,desdeLong:0,hastaLong:0};

  ngOnInit(): void {
  }

  
  onSubmit(f:NgForm){
    if(f.valid){
      this.zona.barrio=f.value.barrio;
      this.zona.desdeLat=f.value.desdeLat;
      this.zona.hastaLat=f.value.hastaLat;
      this.zona.desdeLong=f.value.desdeLong;
      this.zona.hastaLong=f.value.hastaLong;
    }

    if (!f) { return; }
      this.zonaService.addZona(this.zona)
      .subscribe(zona => {
      this.zonas.push(zona);
    });
  }

  goBack(): void {
    this.location.back();
  }
  
  cerrarSesion(){
    localStorage.clear();
  }

}
