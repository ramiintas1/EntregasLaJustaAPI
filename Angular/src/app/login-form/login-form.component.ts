import { Component, OnInit, Input  } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NgForm } from '@angular/forms';

import { LoginService } from '../login.service';
import { LoginI } from '../login.interface';
import { Router } from '@angular/router';
import { ResponseI } from '../response.interface';
import { usuarioLogueado } from '../usuarioLogueado';
import { PedidoService } from '../pedido.service';
import { Pedido } from '../pedido';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  logueado: usuarioLogueado = {id:0,rol:"default"};

  @Input() pedido!: Pedido;

  loginForm = new FormGroup({
    usuario : new FormControl('',Validators.required),
    contrasena : new FormControl('',Validators.required)
  })

  constructor(private loginService: LoginService, private router: Router, private pedidoService: PedidoService) { }

  
  errorStatus: boolean = false;
  errorMsj:any = "";

  ngOnInit(): void {
    this.checkLocalStorage();
    this.token();
  }

  checkLocalStorage(){
    if(localStorage.getItem('token')){
      this.router.navigate(['/dashboard']);
    }
  }

  onLogin(form:LoginI){
    this.loginService.loginByEmail(form).subscribe(data => {
      let dataResponse:ResponseI = data;
      if(dataResponse.JWT != null){   
        localStorage.setItem("token",dataResponse.JWT.string);
        localStorage.setItem("rol",dataResponse.rol.string);
        
        this.logueado.rol = dataResponse.rol.string;
        this.logueado.id = Number(dataResponse.idUsuario.string);
        if(dataResponse.rol.string=="Repartidor"){
          this.router.navigate(["/detalle/" + dataResponse.idUsuario.string]);
        }else{
          this.router.navigate(["/dashboard"]);
        }
      }else{
        this.errorStatus = true;
        this.errorMsj = dataResponse.JWT.error_msg;
      }
    
    })
  }

  buscarPedido(id:number){
    this.pedidoService.getPedido(id)
    .subscribe(pedido => this.pedido = pedido);
  }

  token(): void {
    this.pedidoService.getToken()
    .subscribe();
  }

  obtenerPedido(id:number): void{
    this.pedidoService.getPedidoJusta(id)
        .subscribe(pedido => this.pedido = pedido);
  }
  
  cerrarSesion(){
    localStorage.clear();
  }

}
