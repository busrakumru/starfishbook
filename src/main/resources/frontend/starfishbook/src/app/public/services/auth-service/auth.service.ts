import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';

import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from 'src/app/model/user.model';
import { TokenService } from '../token/token.service';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {


  constructor(private http: HttpClient, private tokenStorageService: TokenService) { }

  ngOnInit(): void {

  }

  private baseUrl = 'http://localhost:8080/auth';

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
    window.location.reload();
  }

 /* isLoggedIn(): any {
    this.tokenStorageService.getToken();
    window.location.reload();
  }*/

  get isLoggedIn():boolean {
    let token = this.tokenStorageService.getToken();
    window.location.reload();
    //let authToken = localStorage.getItem('access_token');
    return token !== null ? true : false;
  }

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