import { Injectable } from '@angular/core';
import { Repartidor } from './repartidor';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'
import { catchError, map, tap } from 'rxjs/operators';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';
import { usuarioLogueado } from './usuarioLogueado';

@Injectable({
  providedIn: 'root'
})
export class RepartidorService {

  private repartidoresUrl = 'api/repartidores'; 

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private messageService: MessageService, private http: HttpClient) { }

  /** GET: get all repartidores to the server */
  getRepartidores():  Observable<Repartidor[]> {
    //return of (REPARTIDORES);
    return this.http.get<Repartidor[]>(this.repartidoresUrl)
    .pipe(
      catchError(this.handleError<Repartidor[]>('getRepartidores', []))
    );
  }

  /** GET: get a repartidor to the server */
  getRepartidor(id: number): Observable<Repartidor> {
    const url = `${this.repartidoresUrl}/${id}`;
    return this.http.get<Repartidor>(url).pipe(
      tap(_ => this.log(`busca repartidor id=${id}`)),
      catchError(this.handleError<Repartidor>(`getRepartidor id=${id}`))
  );
  }

  /** POST: add a new repartidor to the server */
  addRepartidor(repartidor: Repartidor): Observable<Repartidor> {
    return this.http.post<Repartidor>(this.repartidoresUrl, repartidor, this.httpOptions).pipe(
      tap((newRepartidor: Repartidor) => this.log(`agregar repartidor w/ id=${newRepartidor.id}`)),
      catchError(this.handleError<Repartidor>('addrepartidor'))
    );
  }

  /** DELETE: delete the repartidor from the server */
  deleteRepartidor(repartidor: Repartidor | number): Observable<Repartidor> {
    const id = typeof repartidor === 'number' ? repartidor : repartidor.id;
    const url = `${this.repartidoresUrl}/${id}`;

    return this.http.delete<Repartidor>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted repartidor id=${id}`)),
      catchError(this.handleError<Repartidor>('deleteRepartidor'))
    );
  }

  /** PUT: update the repartidor on the server */
  updateHero(repartidor: Repartidor): Observable<any> {
    return this.http.put(this.repartidoresUrl, repartidor, this.httpOptions).pipe(
      tap(_ => this.log(`updated repartidor id=${repartidor.id}`)),
      catchError(this.handleError<any>('updateRepartidor'))
    );
  }

  /* GET heroes whose name contains search term */
  searchRepartidores(term: string): Observable<Repartidor[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Repartidor[]>(`${this.repartidoresUrl}/?nombre=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found repartidores matching "${term}"`) :
        this.log(`no repartidores matching "${term}"`)),
      catchError(this.handleError<Repartidor[]>('searchRepartidores', []))
    );
  }

  private log(message: string) {
    this.messageService.add(`RepartidorService: ${message}`);
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
