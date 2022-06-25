import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { Todo } from 'src/app/models/todo.model';
import { Todolist } from 'src/app/models/todolist.model';
import { TodoService } from 'src/app/services/todo.service';
import { TodolistService } from 'src/app/services/todolist.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.page.html',
  styleUrls: ['./todo.page.scss'],
})
export class TodoPage implements OnInit {

  @Input() id:any;
  @Input() title:string;

  todos: Todo[] = [];
  todolist: Todolist[] = [];

  newTodo: Todo = new Todo();
  newTodolist: Todolist = new Todolist();

  editing: boolean = false;
  editingTodolist: Todolist = new Todolist();
  editingTodo: Todo = new Todo();

  isLoggedIn = false;

  constructor(
    public modalController: ModalController,private todolistService: TodolistService,private todoService: TodoService
  ) { }


  ngOnInit(): void {
    console.log(this.id);
    this.getTodolist();
    //this.getTodo();
    
  }


  //Liste aller Todos bekommen
  getTodolist(): void {
    this.todolistService.getTodolist()
      .subscribe(todolist => this.todolist = todolist);
  }

  
  //Liste aller Todos bekommen
  getTodo(): void {
    this.todoService.getTodos()
      .subscribe(todos => this.todos = todos);
  }

  
  //eine Todo für die Liste erstellen
  createTodolist(): void {
    this.todolistService.createTodolist(this.newTodolist)
      .subscribe(createTodolist => {
        //todoForm.reset();
        this.newTodolist = new Todolist();
        this.todolist.unshift(createTodolist);
        console.log(createTodolist);
      });
  }

  //eine Todo aus der Liste löschen
  deleteTodolist(id: any): void {
    this.todolistService.deleteTodolist(id)
      .subscribe(() => {
        this.todolist = this.todolist.filter(todolist => todolist.id != id);
      });
  }

  //eine Todo aus der Liste aktualisieren
  updateTodolist(todoData: Todolist): void {
    console.log(todoData);
    this.todolistService.updateTodolist(todoData)
      .subscribe(updatedTodo => {
        let existingTodo = this.todolist.find(todolist => todolist.id === updatedTodo.id);
        Object.assign(existingTodo, updatedTodo);
        this.clearEditing();
      });
  }

  //Todo aus der Liste abchecken
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

///_----------------------------------------------------------------------------------------------------------------------
saveTodo(): void {
  if (this.id) {
    this.updateTodo();
  } else {
    this.createTodo();
  }
  this.modalController.dismiss();
}

    //Todos mit Titel und Todolist erstellen
    createTodo(): void {

      this.todoService.createTodo(this.newTodo)
        .subscribe(createTodo => {
         // todoForm.reset();
          this.newTodo= new Todo();
          this.todos.unshift(createTodo);
          console.log(createTodo);
        });
        
        this.createTodolist();
        this.newTodolist=new Todolist;
        console.log(this.newTodolist);

        this.modalController.dismiss();
        //this.todolistService.createTodolistByTodosId(this.newTodo,this.newTodolist);
    }


    updateTodo(): void {
      console.log(this.id);
      console.log(this.editingTodo.title)
      this.editing = true;
      this.todoService.updateTodo(this.id,this.editingTodo).subscribe(updatedTodo => {
       this.todolist.find(todolist => todolist.id === updatedTodo.id);
    });
  }
  abbrechen(){
    this.modalController.dismiss();
  }

  
}


 