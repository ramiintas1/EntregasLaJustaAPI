import { Injectable } from '@angular/core';
import { TransicionEstado } from './transicion';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'
import { catchError, map, tap } from 'rxjs/operators';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class TransicionService {

  private transicionesUrl = 'api/transicionestado'; 
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private messageService: MessageService, private http: HttpClient) { }

  
  /** GET: get all transiciones to the server */
  getTransiciones():  Observable<TransicionEstado[]> {
    return this.http.get<TransicionEstado[]>(this.transicionesUrl)
    .pipe(
      catchError(this.handleError<TransicionEstado[]>('getTransiciones', []))
    );
  }

  /** GET: get a transiciones to the server */
  getTransicion(id: number): Observable<TransicionEstado> {
    const url = `${this.transicionesUrl}/${id}`;
    return this.http.get<TransicionEstado>(url).pipe(
      tap(_ => this.log(`busca transicion id=${id}`)),
      catchError(this.handleError<TransicionEstado>(`getTransicion id=${id}`))
  );
  }

  /** POST: add a new repartidor to the server */
  addTransicion(transicion: TransicionEstado): Observable<TransicionEstado> {
    return this.http.post<TransicionEstado>(this.transicionesUrl, transicion, this.httpOptions).pipe(
      tap((newTransicion: TransicionEstado) => this.log(`agregar repartidor w/ id=${newTransicion.id}`)),
      catchError(this.handleError<TransicionEstado>('addrepartidor'))
    );
  }
  
  private log(message: string) {
    this.messageService.add(`TransicionService: ${message}`);
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation 
 * @param result 
 */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }
}
