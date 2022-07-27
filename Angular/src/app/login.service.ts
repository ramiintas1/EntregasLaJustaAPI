import { Injectable } from '@angular/core';

import { LoginI } from './login.interface';
import { ResponseI } from './response.interface';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'
import { catchError, map, tap } from 'rxjs/operators';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginsUrl = 'api/auth';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private messageService: MessageService, private http: HttpClient) { }

  loginByEmail(form:LoginI):Observable<ResponseI>{
    let direccion = this.loginsUrl;
    return this.http.post<ResponseI>(direccion,form);
  }

}
