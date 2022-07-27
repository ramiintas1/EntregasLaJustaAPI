import { Injectable } from '@angular/core';
import { Pedido } from './pedido';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';
import { LoginI } from './login.interface';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  constructor(private messageService: MessageService, private http: HttpClient) { }

  private pedidosUrl = 'api/pedidos';
  private pedidosJustaUrl = 'api/pedidos-justa';
  private tokenJustaUrl = 'api/token-justa';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  /** GET pedidos from the server */
  getPedidos(): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(this.pedidosUrl)
    .pipe(
      catchError(this.handleError<Pedido[]>('getPedidos', []))
    );
  }

  /** GET pedidos justa from the server */
  getPedidosJusta(): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(this.pedidosJustaUrl)
    .pipe(
      catchError(this.handleError<Pedido[]>('getPedidosJusta', []))
    );
  }

  /** GET pedido by id. Will 404 if id not found */
  getPedido(id: number): Observable<Pedido> {
    const url = `${this.pedidosUrl}/${id}`;
    return this.http.get<Pedido>(url).pipe(
      tap(_ => this.log(`fetched pedido id=${id}`)),
      catchError(this.handleError<Pedido>(`getPedido id=${id}`))
    );
  }

  //--------------------------
  getToken(){
    return this.http.post<any>(this.tokenJustaUrl,this.httpOptions)
    .pipe(
      catchError(this.handleError('getTokenJusta'))
    );
  }
  /** GET: get a repartidor to the server */
  getPedidoJusta(id: number): Observable<Pedido> {
    const url = `${this.pedidosJustaUrl}/${id}`;
    return this.http.get<Pedido>(url).pipe(
      tap(_ => this.log(`busca pedido id=${id}`)),
      catchError(this.handleError<Pedido>(`getPedido id=${id}`))
    );
  }
  /** PUT: update the repartidor on the server */
  updatePedidoJusta2(id: number): Observable<any> {
    const url = `${this.pedidosJustaUrl}/${id}`;
    return this.http.put(url, id, this.httpOptions).pipe(
      tap(_ => this.log(`updated pedido id=${id}`)),
      catchError(this.handleError<any>('updatePedido'))
    );
  }
  //--------------------------

  private log(message: string) {
    this.messageService.add(`PedidoService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
