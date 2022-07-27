import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Sistema de Reparto';

  logueado :any = localStorage.getItem("token");

  cerrarSesion(){
    localStorage.clear();
  }
   

}
