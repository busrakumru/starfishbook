import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notes } from '../models/notes.model';



@Injectable({
  providedIn: 'root'
})

export class NotesService {

  private baseUrl = 'https://localhost:8443/auth/users/notes';


  constructor( private http: HttpClient ){}

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

    updateNote(id: any, note: Notes): Observable<Notes> {
    return this.http.put<Notes>(`${this.baseUrl}/${id}`, note);
    }

    /*patchNote(id: any, note: Notes): Observable<any>{
      return this.http.patch<Notes[]>(`${this.baseUrl}/${id}`, note);
    }*/
}