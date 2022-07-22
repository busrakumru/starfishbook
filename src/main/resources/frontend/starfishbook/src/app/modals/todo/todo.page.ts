import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ModalController } from '@ionic/angular';

import { Todo } from 'src/app/models/todo.model';
import { Todolist } from 'src/app/models/todolist.model';
import { ReloadService } from 'src/app/services/reload.service';
import { TodoService } from 'src/app/services/todo.service';
import { TodolistService } from 'src/app/services/todolist.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.page.html',
  styleUrls: ['./todo.page.scss'],
})
export class TodoPage implements OnInit {


  @Input() id: any;
  @Input() title: string;

  placeholderTitel = "Titel";
  show: boolean;

  minDate = new Date().toISOString();
  maxDate: any = (new Date()).getFullYear() + 10;

  todos: Todo[] = [];
  newTodo: Todo = new Todo();

  todolist: Todolist[] = [];
  newTodolist: Todolist = new Todolist();

  editing: boolean = false;
  editingTodolist: Todolist = new Todolist();
  editingTodo: Todo = new Todo();

  isLoggedIn = false;

  constructor(
    public reloadService: ReloadService, public modalController: ModalController, private todolistService: TodolistService, private todoService: TodoService
  ) { }


  ngOnInit(): void {
    this.getTodolist();

    if (this.id) {
      this.placeholderTitel = this.title;
      this.show = !this.show;
    }
  }

  getTodolist(): void {
    this.todolistService.getTodolist()
      .subscribe(todolist => this.todolist = todolist);
  }

  createTodolist(): void {
    this.todolistService.createTodolist(this.newTodolist)
      .subscribe(createTodolist => {

        this.newTodolist = new Todolist();
        this.todolist.unshift(createTodolist);
        console.log(this.todolist);
      });
  }

  deleteTodolist(id: any): void {
    this.todolistService.deleteTodolist(id)
      .subscribe(() => {
        this.todolist = this.todolist.filter(todolist => todolist.id != id);
      });
  }

  updateTodolist(todoData: Todolist): void {
    console.log(todoData);
    this.todolistService.updateTodolist(todoData)
      .subscribe(updatedTodo => {
        let existingTodo = this.todolist.find(todolist => todolist.id === updatedTodo.id);
        Object.assign(existingTodo, updatedTodo);
        this.clearEditing();
      });
  }

  toggleCompleted(todoData: Todolist): void {
    todoData.finished = !todoData.finished;
    this.todolistService.updateTodolist(todoData)
      .subscribe(updatedTodo => {
        let existingTodo = this.todolist.find(todolist => todolist.id === updatedTodo.id);
        Object.assign(existingTodo, updatedTodo);
      });
  }

  editTodolist(todoData: Todolist): void {
    this.editing = true;
    Object.assign(this.editingTodolist, todoData);
  }

  clearEditing(): void {
    this.editingTodolist = new Todolist();
    this.editing = false;
  }

  saveTodo(): void {
    if (this.id) {
      this.updateTodo();
    } else {
      this.createTodo();
    }
  }

  createTodo(): void {
    this.todoService.createTodo(this.newTodo)
      .subscribe(createTodo => {
        this.newTodo = new Todo();
        this.todos.unshift(createTodo);
      });
    this.modalController.dismiss();
    this.reloadService.reload();
  }

  updateTodo(): void {
    this.editing = true;
    this.todoService.updateTodo(this.id, this.editingTodo).subscribe(updatedTodo => {
      this.todolist.find(todolist => todolist.id === updatedTodo.id);
    });
    this.modalController.dismiss();
    this.reloadService.reload();
  }

  abbrechen() {
    this.modalController.dismiss();
  }
}


