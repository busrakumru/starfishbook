import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Todo } from '../models/todo.model';
import { Todolist } from '../models/todolist.model';

@Injectable({
  providedIn: 'root'
})
export class TodolistService {
  private baseUrl = 'https://localhost:8443';

  constructor(private http: HttpClient) { }


  getTodolist(): Observable<Todolist[]> {
    return this.http.get<Todolist[]>(this.baseUrl + '/auth/users/todolist')
      .pipe(
        catchError(this.handleError)
      );
  }

  getTodolistById(todolistData: Todolist): Observable<Todolist[]> {
    return this.http.get<Todolist[]>(this.baseUrl + '/auth/users/todolist/'+ todolistData.id)
      .pipe(
        catchError(this.handleError)
      );
  }
  
  getTodolistByTodo(todoId:Todo): Observable<Todolist[]> {
    return this.http.get<Todolist[]>(this.baseUrl + '/auth/users/todolist/'+ todoId.id +'/todolist')
      .pipe(
        catchError(this.handleError)
      );
  }
  
  createTodolist(todolistData: Todolist): Observable<Todolist> {
    return this.http.post<Todolist>(this.baseUrl + '/auth/users/todolist', todolistData)
      .pipe(
        catchError(this.handleError)
      );
  }

  updateTodolist(todolistData: Todolist): Observable<Todolist> {
    return this.http.put<Todolist>(this.baseUrl + '/auth/users/todolist/' + todolistData.id, todolistData)
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteTodolist(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + '/auth/users/todolist/' + id)
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
