import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category.model';


@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  private baseUrl = 'https://localhost:8443/auth/users/categories';


  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]>{
    return this.http.get<Category[]>(`${this.baseUrl}`);
  }

  createCategory(category:Category): Observable<Category[]>{
    return this.http.post<Category[]>(`${this.baseUrl}`,category);
  }
}
