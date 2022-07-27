import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule , FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RepartidoresComponent } from './repartidores/repartidores.component';
import { RepartidorDetalleComponent } from './repartidor-detalle/repartidor-detalle.component';
import { RepartidorFormComponent } from './repartidor-form/repartidor-form.component';
import { PedidosComponent } from './pedidos/pedidos.component';
import { EntregaFormComponent } from './entrega-form/entrega-form.component';
import { RepartidorService } from './repartidor.service';
import { EntregasComponent } from './entregas/entregas.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MessagesComponent } from './messages/messages.component';
import { EntregaDetalleComponent } from './entrega-detalle/entrega-detalle.component';
import { RepartidoresSearchComponent } from './repartidores-search/repartidores-search.component';
import { PedidoDetalleComponent } from './pedido-detalle/pedido-detalle.component';
import { ProductosComponent } from './productos/productos.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { InterceptorService } from './interceptor.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FilterPipe } from './filter.pipe';
import { FilterPedidosPipe } from './filter-pedidos.pipe';
import { EntregaPutComponent } from './entrega-put/entrega-put.component';
import { VehiculoFormComponent } from './vehiculo-form/vehiculo-form.component';
import { ZonaFormComponent } from './zona-form/zona-form.component';
import { ZonasComponent } from './zonas/zonas.component';
import { VehiculosComponent } from './vehiculos/vehiculos.component';

@NgModule({
  declarations: [
    AppComponent,
    RepartidoresComponent,
    RepartidorDetalleComponent,
    RepartidorFormComponent,
    PedidosComponent,
    EntregaFormComponent,
    EntregasComponent,
    MessagesComponent,
    EntregaDetalleComponent,
    RepartidoresSearchComponent,
    PedidoDetalleComponent,
    ProductosComponent,
    LoginFormComponent,
    DashboardComponent,
    FilterPipe,
    FilterPedidosPipe,
    EntregaPutComponent,
    VehiculoFormComponent,
    ZonaFormComponent,
    ZonasComponent,
    VehiculosComponent
  ],
  imports: [
    //
    HttpClientModule,
    //
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule 
  ],
  providers: [
    //RepartidorService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
