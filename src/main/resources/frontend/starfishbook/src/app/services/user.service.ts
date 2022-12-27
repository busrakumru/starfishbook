import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'https://localhost:8443/auth/users';


  constructor(private http: HttpClient) { }

  deleteUser(id:any):Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  getUser(id:any):Observable<any>{
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getUsers(): Observable<User[]>{
    return this.http.get<User[]>(`${this.baseUrl}`);
  }

  getPublicContent(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+ 'user', { responseType: 'text' });
  }


  getAdminBoard(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + 'admin', { responseType: 'text' });
  }
}
