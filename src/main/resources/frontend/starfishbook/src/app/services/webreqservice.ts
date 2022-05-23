import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notes } from '../models/notes.model';

//const baseUrl = 'http://localhost:8080';


@Injectable({
  providedIn: 'root'
})

export class WebReqService {

  readonly ROOT_URL;


  constructor(private http: HttpClient) { 
    this.ROOT_URL = 'http://localhost:8080';
  }

  get(uri:string){
    return this.http.get(`${this.ROOT_URL}/${uri}`);
  }

  post(uri:string, payload:Object){
    return this.http.post(`${this.ROOT_URL}/${uri}`, payload);
  }

  patch(uri:string, payload:Object){
    return this.http.post(`${this.ROOT_URL}/${uri}`, payload);
  }

  delete(uri:string){
    return this.http.delete(`${this.ROOT_URL}/${uri}`);
  }


  /*getAll(): Observable<any> {
    return this.http.get(baseUrl);
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
  }*/
}