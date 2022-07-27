import { Injectable } from '@angular/core';
import { Vehiculo } from './vehiculo';

import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs'

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {

  constructor(private messageService: MessageService, private http: HttpClient) { }

  private vehiculosUrl = 'api/vehiculos';

  /** GET vehiculos from the server */
  getVehiculos(): Observable<Vehiculo[]> {
    return this.http.get<Vehiculo[]>(this.vehiculosUrl)
    .pipe(
      catchError(this.handleError<Vehiculo[]>('getEstadosR', []))
    );
  }

  /** GET vehiculos by id. Will 404 if id not found */
  getVehiculo(id: number): Observable<Vehiculo> {
    const url = `${this.vehiculosUrl}/${id}`;
    return this.http.get<Vehiculo>(url).pipe(
      tap(_ => this.log(`fetched vehiculo id=${id}`)),
      catchError(this.handleError<Vehiculo>(`getVehiculo id=${id}`))
    );
  }

  /** POST: add a new repartidor to the server */
  addZona(vehiculo: Vehiculo): Observable<Vehiculo> {
    return this.http.post<Vehiculo>(this.vehiculosUrl, vehiculo, this.httpOptions).pipe(
      tap((newVehiculo: Vehiculo) => this.log(`agregar vehiculo w/ id=${newVehiculo.id}`)),
      catchError(this.handleError<Vehiculo>('addvehiculo'))
    );
  }
  
  /** DELETE: delete the repartidor from the server */
  deleteVehiculo(vehiculo: Vehiculo | number): Observable<Vehiculo> {
    const id = typeof vehiculo === 'number' ? vehiculo : vehiculo.id;
    const url = `${this.vehiculosUrl}/${id}`;

    return this.http.delete<Vehiculo>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted vehiculo id=${id}`)),
      catchError(this.handleError<Vehiculo>('deleteVehiculo'))
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
