import { Component, OnInit } from '@angular/core';
import { ZonaService } from '../zona.service';
import { Zona } from '../zona';

@Component({
  selector: 'app-zonas',
  templateUrl: './zonas.component.html',
  styleUrls: ['./zonas.component.css']
})
export class ZonasComponent implements OnInit {

  zonas: Zona[] = [];

  constructor(private zonaService: ZonaService) { }

  ngOnInit(): void {
    this.getZonas();
  }

  getZonas(): void {
    this.zonaService.getZonas().subscribe(zonas => this.zonas = zonas);
  }

  delete(zona: Zona): void {
    this.zonas = this.zonas.filter(r => r !== zona);
    this.zonaService.deleteZona(zona).subscribe();
  }

  cerrarSesion(){
    localStorage.clear();
  }

}
