import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Todo } from '../models/todo.model';


@Injectable({
  providedIn: 'root'
})

export class TodoService {

  //private baseUrl = 'https://localhost:8443';

  private baseUrl = 'https://localhost:8443/auth/users';

  constructor(private http: HttpClient) { }

  getTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.baseUrl}/todo`)
      .pipe(
        catchError(this.handleError)
      );
  }

  createTodo(todoData: Todo): Observable<Todo[]> {
    return this.http.post<Todo[]>(`${this.baseUrl}/todo`, todoData)
      .pipe(
        catchError(this.handleError)
      );
  }

  updateTodo(id: any, todoData: Map<Object, Object> ): Observable<Todo[]> {
    return this.http.patch<Todo[]>(`${this.baseUrl}/todo/${id}`, todoData)
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteTodo(id: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/todo/${id}`)
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
