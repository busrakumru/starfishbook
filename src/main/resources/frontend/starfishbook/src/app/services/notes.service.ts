/*import { Injectable } from '@angular/core';
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

     updateNotes(id: any, notesData: Notes): Observable<Notes[]> {
    return this.http.patch<Notes[]>(this.baseUrl + '/auth/users/notes/' + id, notesData)
      .pipe(
        catchError(this.handleError)
      );
  }
*/

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Notes } from '../models/notes.model';


@Injectable({
  providedIn: 'root'
})

export class NotesService {

  private baseUrl = 'https://localhost:8443';

  constructor(private http: HttpClient) { }

  getNotes(): Observable<Notes[]> {
    return this.http.get<Notes[]>(this.baseUrl + '/auth/users/notes')
      .pipe(
        catchError(this.handleError)
      );
  }

  createNotes(notesData: Notes): Observable<Notes[]> {
    return this.http.post<Notes[]>(this.baseUrl + '/auth/users/notes/', notesData)
      .pipe(
        catchError(this.handleError)
      );
  }

  updateNotes(id: any, notesData: Notes): Observable<Notes[]> {
    return this.http.patch<Notes[]>(this.baseUrl + '/auth/users/notes/' + id, notesData)
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteNotes(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + '/auth/users/notes/' + id)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('An error occurred:', error.error);
    } else {
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }
}
