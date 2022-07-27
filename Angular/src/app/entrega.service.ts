import { Injectable } from '@angular/core';
import { Entrega } from './entrega';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class EntregaService {

  constructor(private messageService: MessageService, private http: HttpClient) { }

  private entregasUrl = 'api/entregas';

   /** GET entregas from the server */
   getEntregas(): Observable<Entrega[]> {
    return this.http.get<Entrega[]>(this.entregasUrl)
    .pipe(
      catchError(this.handleError<Entrega[]>('getEntregas', []))
    );
  }

  /** GET entrega by id. Will 404 if id not found */
  getEntrega(id: number): Observable<Entrega> {
    const url = `${this.entregasUrl}/${id}`;
    return this.http.get<Entrega>(url).pipe(
      tap(_ => this.log(`fetched entrega id=${id}`)),
      catchError(this.handleError<Entrega>(`getEntrega id=${id}`))
    );
  }
  
  /** POST: add a new entrega to the server */
  addEntregas(entrega: Entrega): Observable<Entrega> {
    return this.http.post<Entrega>(this.entregasUrl, entrega, this.httpOptions).pipe(
      tap((newEntrega: Entrega) => this.log(`agregar entrega w/ id=${newEntrega.id}`)),
      catchError(this.handleError<Entrega>('addentrega'))
    );
  }

  /** PUT: update the entrega on the server */
  updateEntrega(entrega: Entrega): Observable<any> {
    return this.http.put(this.entregasUrl, entrega, this.httpOptions).pipe(
      tap(_ => this.log(`updated entrega id=${entrega.id}`)),
      catchError(this.handleError<any>('updateEntrega'))
    );
  }

    /** DELETE: delete the repartidor from the server */
  deleteEntrega(entrega: Entrega | number): Observable<Entrega> {
    const id = typeof entrega === 'number' ? entrega : entrega.id;
    const url = `${this.entregasUrl}/${id}`;
  
    return this.http.delete<Entrega>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted entrega id=${id}`)),
      catchError(this.handleError<Entrega>('deleteEntrega'))
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