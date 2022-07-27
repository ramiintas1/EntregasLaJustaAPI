import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RepartidoresComponent } from './repartidores/repartidores.component';
import { PedidosComponent } from './pedidos/pedidos.component';
import { RepartidorDetalleComponent } from './repartidor-detalle/repartidor-detalle.component';
import { EntregaDetalleComponent } from './entrega-detalle/entrega-detalle.component';
import { PedidoDetalleComponent } from './pedido-detalle/pedido-detalle.component';
import { RepartidorFormComponent } from './repartidor-form/repartidor-form.component';
import { EntregaFormComponent } from './entrega-form/entrega-form.component';
import { EntregasComponent } from './entregas/entregas.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { VigilanteGuard } from './vigilante.guard';
import { EntregaPutComponent } from './entrega-put/entrega-put.component';
import { ZonaFormComponent } from './zona-form/zona-form.component';
import { ZonasComponent } from './zonas/zonas.component';
import { VehiculoFormComponent } from './vehiculo-form/vehiculo-form.component';
import { VehiculosComponent } from './vehiculos/vehiculos.component';

const routes: Routes = [
  {path:'', redirectTo:'login', pathMatch:'full'},
  {path:'dashboard', component: DashboardComponent, canActivate: [VigilanteGuard]},
  {path:'repartidores', component: RepartidoresComponent, canActivate: [VigilanteGuard]},
  {path:'pedidos', component: PedidosComponent, canActivate: [VigilanteGuard]},
  {path:'detalle/:id', component: RepartidorDetalleComponent},
  {path: 'agregarRepartidor', component: RepartidorFormComponent, canActivate: [VigilanteGuard]},
  {path: 'asignarEntrega', component: EntregaFormComponent, canActivate: [VigilanteGuard]},
  {path: 'entregas', component: EntregasComponent, canActivate: [VigilanteGuard]},
  {path: 'detalleEntrega/:id', component: EntregaDetalleComponent},
  {path: 'detallePedido/:id', component: PedidoDetalleComponent},
  {path: 'login', component: LoginFormComponent},
  {path: 'updateEntrega/:id', component: EntregaPutComponent},
  {path: 'agregarZona', component: ZonaFormComponent, canActivate: [VigilanteGuard]},
  {path: 'zonas', component: ZonasComponent, canActivate: [VigilanteGuard]},
  {path: 'agregarVehiculo', component: VehiculoFormComponent, canActivate: [VigilanteGuard]},
  {path: 'vehiculos', component: VehiculosComponent, canActivate: [VigilanteGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
