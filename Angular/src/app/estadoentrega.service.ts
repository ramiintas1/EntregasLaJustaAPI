import { Injectable } from '@angular/core';
import { EstadoEntrega } from './estadoEntrega';

import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class EstadoentregaService {

  constructor(private messageService: MessageService, private http: HttpClient) { }

  private estadosentUrl = 'api/estadosentrega';

  
    /** GET estados from the server */
    getEstadosR(): Observable<EstadoEntrega[]> {
      return this.http.get<EstadoEntrega[]>(this.estadosentUrl)
      .pipe(
        catchError(this.handleError<EstadoEntrega[]>('getEstadosE', []))
      );
    }
  
    /** GET estados by id. Will 404 if id not found */
    getEstadoR(id: number): Observable<EstadoEntrega> {
      const url = `${this.estadosentUrl}/${id}`;
      return this.http.get<EstadoEntrega>(url).pipe(
        tap(_ => this.log(`fetched estado id=${id}`)),
        catchError(this.handleError<EstadoEntrega>(`getEstado id=${id}`))
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
