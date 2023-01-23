
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Roles } from 'src/app/models/roles.model';
import { User } from 'src/app/models/user.model';
import { TokenService } from '../token/token.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'https://localhost:8443/auth/admin';

  getuser(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/user`, httpOptions);
  }

  updateuser(id:any, user: Map<Object, Object> ): Observable<User[]> {
    return this.http.patch<User[]>(`${this.baseUrl}/user/${id}`, user, httpOptions).pipe(catchError(this.handleError));
  }

  deleteuser(id: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/users/${id}`);
  }


  
  handleError(error: HttpErrorResponse) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      msg = error.error.message;
    } else {
      // server-side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }
}