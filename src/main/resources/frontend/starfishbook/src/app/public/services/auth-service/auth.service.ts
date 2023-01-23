import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
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
export class AuthService {

  constructor(private http: HttpClient, private tokenStorageService: TokenService, private router: Router ) { }

  private baseUrl = 'https://localhost:8443/auth';

  login(user: User): Observable<any> {
    return this.http.post<User>(`${this.baseUrl}/login`, user, httpOptions);
  }

  register(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user, httpOptions).pipe(catchError(this.handleError));
  }

  findByEmail(email: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/find-by-email?email=${email}`);
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['../../login']);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/users/${id}`);
  }
  
  private user: User;

  isAuthorized() {
      return !!this.user;
  }

  hasRole(role: Roles) {
      return this.isAuthorized() && this.user.roles === role;
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