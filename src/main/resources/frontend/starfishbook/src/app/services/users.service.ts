import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../shared/user';



@Injectable({
  providedIn: 'root'
})

export class UsersService {

  private baseUrl = 'http://localhost:8080/auth/user';


  constructor(
    private http: HttpClient){}

    getUsers(): Observable<User[]>{
      return this.http.get<User[]>(`${this.baseUrl}`);
    }

    getUser(id:any):Observable<any>{
      return this.http.get(`${this.baseUrl}/${id}`);
    }

    createUser(user:User): Observable<User[]>{
      return this.http.post<User[]>(`${this.baseUrl}`,user);
    }

    deleteUser(id:any):Observable<any> {
      return this.http.delete(`${this.baseUrl}/${id}`);
    }

    updateUser(id: any, user: User): Observable<any> {
    return this.http.put<User[]>(`${this.baseUrl}/${id}`, user);
     //return this.http.patch(`${this.baseUrl}/${id}`,note.text);
    }
}