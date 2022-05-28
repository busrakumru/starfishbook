import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notes } from '../models/notes.model';
import { WebReqService } from './webreqservice';



@Injectable({
  providedIn: 'root'
})

export class NotesService {

  private baseUrl = 'http://localhost:8080/api/notes';


  constructor(private webReqService: WebReqService,
    private http: HttpClient){}

    getNotes(): Observable<Notes[]>{
      return this.http.get<Notes[]>(`${this.baseUrl}`);
    }

    getNote(id:any):Observable<any>{
      return this.http.get(`${this.baseUrl}/${id}`);
    }

    createNote(note:Notes): Observable<Notes[]>{
      return this.http.post<Notes[]>(`${this.baseUrl}`,note);
    }

    deleteNote(id:any):Observable<any> {
      return this.http.delete(`${this.baseUrl}/${id}`);
    }

    updateNote(id: any, note: Notes): Observable<any> {
    return this.http.put<Notes[]>(`${this.baseUrl}/${id}`, note);
     //return this.http.patch(`${this.baseUrl}/${id}`,note.text);
    }

    patchNote(id: any, note: Notes): Observable<any>{
      return this.http.patch<Notes[]>(`${this.baseUrl}/${id}`, note);
    }


  /*createNote(title:string){
   return this.webReqService.post('notes',{title});
  }*/

  /*readonly ROOT_URL;


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
  }*/


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