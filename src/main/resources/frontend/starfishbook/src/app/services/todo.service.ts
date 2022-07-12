import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, throwError } from 'rxjs';

import { catchError } from 'rxjs/operators';
import { Todo } from '../models/todo.model';
import { Todolist } from '../models/todolist.model';

@Injectable({
  providedIn: 'root'
})

export class TodoService {
  
  private baseUrl = 'https://localhost:8443';

  constructor(private http: HttpClient) { }

  getTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.baseUrl + '/auth/users/todo')
      .pipe(
        catchError(this.handleError)
      );
  }
  
  createTodo(todoData: Todo): Observable<Todo> {
    return this.http.post<Todo>(this.baseUrl + '/auth/users/todo/' , todoData)
      .pipe(
        catchError(this.handleError)
      );
  }

  updateTodo(id: any,todoData: Todo): Observable<Todo> {
    return this.http.put<Todo>(this.baseUrl  + '/auth/users/todo/'+ id, todoData)
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteTodo(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + '/auth/users/todo/' + id)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }
}
