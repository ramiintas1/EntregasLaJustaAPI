import { Injectable } from '@angular/core';
import { Rol } from './rol';

import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  constructor(private messageService: MessageService, private http: HttpClient) { }

  private rolesUrl = 'api/roles';
  
  /** GET entregas from the server */
  getRoles(): Observable<Rol[]> {
    return this.http.get<Rol[]>(this.rolesUrl)
    .pipe(
      catchError(this.handleError<Rol[]>('getRoles', []))
    );
  }

  /** GET entrega by id. Will 404 if id not found */
  getRol(id: number): Observable<Rol> {
    const url = `${this.rolesUrl}/${id}`;
    return this.http.get<Rol>(url).pipe(
      tap(_ => this.log(`fetched rol id=${id}`)),
      catchError(this.handleError<Rol>(`getRol id=${id}`))
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
