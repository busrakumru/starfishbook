import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notes } from '../models/notes.model';

const baseUrl = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})

export class NotesService {
  constructor(private http: HttpClient) { }
  getAll(): Observable<Notes[]> {
    return this.http.get<Notes[]>(baseUrl);
  }
  get(id: any): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }
  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
  findByTitle(title: any): Observable<Notes[]> {
    return this.http.get<Notes[]>(`${baseUrl}?title=${title}`);
  }
}