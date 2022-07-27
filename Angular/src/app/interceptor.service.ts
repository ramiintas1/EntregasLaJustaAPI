import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
//import { networkInterfaces } from 'os'; 
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{

  constructor(private router: Router) { }


  intercept(req: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>>{

    const token :any = localStorage.getItem("token");

    let request = req;

    if(token){
      request = req.clone({
        setHeaders: {
          Authorization: token
        }
      });
    }
    console.log('Paso por el interceptor');
    /*
    const headers = new HttpHeaders({
      'Authorization': localStorage.getItem("token")
    });

    const reqClone = req.clone({
      headers
    });
    
    return next.handle(req);
    */
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => {
          if(err.status == 401){
            this.router.navigateByUrl('/login');
          }
          return throwError(err);

      })
    )


  }
}
