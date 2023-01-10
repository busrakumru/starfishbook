import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categories } from '../models/categories.model';


@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  private baseUrl = 'https://localhost:8443/auth/users/categories';


  constructor(private http: HttpClient) { }

  getCategories(): Observable<Categories[]>{
    return this.http.get<Categories[]>(`${this.baseUrl}`);
  }

  createCategory(category:Categories): Observable<Categories>{
    return this.http.post<Categories>(`${this.baseUrl}`,category);
  }

  delete(id:any):Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
