import { Injectable } from '@angular/core';
import { Zona } from './zona';

import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class ZonaService {

  constructor(private messageService: MessageService, private http: HttpClient) { }
  
  private zonasUrl = 'api/zonas';

  /** GET zonas from the server */
  getZonas(): Observable<Zona[]> {
    return this.http.get<Zona[]>(this.zonasUrl)
    .pipe(
      catchError(this.handleError<Zona[]>('getZonas', []))
    );
  }

  /** GET zona by id. Will 404 if id not found */
  getZona(id: number): Observable<Zona> {
    const url = `${this.zonasUrl}/${id}`;
    return this.http.get<Zona>(url).pipe(
      tap(_ => this.log(`fetched zona id=${id}`)),
      catchError(this.handleError<Zona>(`getZona id=${id}`))
    );
  }

  /** POST: add a new repartidor to the server */
  addZona(zona: Zona): Observable<Zona> {
    return this.http.post<Zona>(this.zonasUrl, zona, this.httpOptions).pipe(
      tap((newZona: Zona) => this.log(`agregar zona w/ id=${newZona.id}`)),
      catchError(this.handleError<Zona>('addzona'))
    );
  }

  /** DELETE: delete the repartidor from the server */
  deleteZona(zona: Zona | number): Observable<Zona> {
    const id = typeof zona === 'number' ? zona : zona.id;
    const url = `${this.zonasUrl}/${id}`;

    return this.http.delete<Zona>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted zona id=${id}`)),
      catchError(this.handleError<Zona>('deleteZona'))
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
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
}
