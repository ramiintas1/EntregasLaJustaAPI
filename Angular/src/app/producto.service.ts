import { Injectable } from '@angular/core';
import { Producto } from './producto';

import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(private messageService: MessageService, private http: HttpClient) { }

  private productosUrl = 'api/productos';

  /** GET productos from the server */
  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.productosUrl)
    .pipe(
      catchError(this.handleError<Producto[]>('getProducto', []))
    );
  }

  /** GET producto by id. Will 404 if id not found */
  getProducto(id: number): Observable<Producto> {
    const url = `${this.productosUrl}/${id}`;
    return this.http.get<Producto>(url).pipe(
      tap(_ => this.log(`fetched producto id=${id}`)),
      catchError(this.handleError<Producto>(`getProducto id=${id}`))
    );
  }

  private log(message: string) {
    this.messageService.add(`EntregaService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
