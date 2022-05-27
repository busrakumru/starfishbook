import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';

import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from 'src/app/model/user.model';
import { TokenService } from '../token/token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {


  constructor(private http: HttpClient, private jwtService: JwtHelperService, private tokenStorageService: TokenService) { }

  ngOnInit(): void {

  }

  private baseUrl = 'http://localhost:8080/auth';

  login(user: User): Observable<any> {
    return this.http.post<User[]>(`${this.baseUrl}/login`, user);
  }

  register(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user).pipe(catchError(this.handleError));
  }

  findByEmail(email: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/find-by-email?email=${email}`);
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  isLoggedIn(): any {
    this.tokenStorageService.getToken();
    window.location.reload();
  }

  /*getLoggedInUser() {
    const decodedToken = this.jwtService.decodeToken();
    return decodedToken.user;
  }*/
  // Error
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