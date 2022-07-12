import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
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

/*
  @Input() id:any;

  constructor(
    private fb: FormBuilder, public modalController: ModalController, private todolistService: TodolistService, private todoService: TodoService
  ) { }

 // todos: Todo[] = [];
todolist: Todolist[];


  newTodolist: FormGroup = new FormGroup({
    text: new FormControl(''),
    finished: new FormControl(false)
  });

  newTodo: FormGroup = new FormGroup({
    title: new FormControl(''),
    todolists: new FormArray([this.newTodolist])

  });

  get todolists(){
    return this.newTodo.get('todolists') as FormArray  }

    onAddSpecialRequest () {
      (this.newTodo.controls['todolists'] as FormArray).push(this.newTodolist);
    
    }
    
  ngOnInit(): void {
  }

  saveTodo(): void {
    if (this.id) {
      this.updateTodo();
    } else {
      this.createTodo();
    }
   // this.modalController.dismiss();
  }
  
      //Todos mit Titel und Todolist erstellen
      createTodo(): void {
  
        this.todoService.createTodo(this.newTodo.value)
          .subscribe(response => {

            console.log(response);
           // console.log(this.todos);
          });
          //this.modalController.dismiss();
        
      }
  
      toggleCompleted(todoData: Todolist): void {
        todoData.finished = !todoData.finished;
        this.todolistService.updateTodolist(todoData)
          .subscribe(updatedTodo => {
            let existingTodo = this.todolist.find(todolist => todolist.id === updatedTodo.id);
            Object.assign(existingTodo, updatedTodo);
          });
      }
  
      
      createTodolist(): void {
        this.todolistService.createTodolist(this.newTodolist.value)
          .subscribe(createTodolist => {
          
            console.log(createTodolist);
          });
      }
  
      updateTodo(): void {
  
        this.todoService.updateTodo(this.id,this.newTodo.value).subscribe(updatedTodo => {
         this.todolist.find(todolist => this.newTodo.value.id === updatedTodo.id);
      });
    }
  
    abbrechen(){
      this.modalController.dismiss();
    }
  */

  
    @Input() id:any;
    @Input() title:string;
  
    todos: Todo[] = [];
    newTodo: Todo = new Todo();
  
   /* newTodolist: FormGroup = new FormGroup({
      text: new FormControl(''),
      finished: new FormControl(false)
    });
  
    newTodo: FormGroup = new FormGroup({
      title: new FormControl(''),
      todolists: new FormArray([this.newTodolist])
  
    });
  
    get todolists(){
      return this.newTodo.get('todolists') as FormArray  }
  
      onAddSpecialRequest () {
        (this.newTodo.controls['todolists'] as FormArray).push(this.newTodolist);
      
      }*/

    todolist: Todolist[] = [];
    newTodolist: Todolist = new Todolist();
  
    editing: boolean = false;
    editingTodolist: Todolist = new Todolist();
    editingTodo: Todo = new Todo();
  
    isLoggedIn = false;
  
    constructor(
      public modalController: ModalController,private todolistService: TodolistService,private todoService: TodoService
    ) { }
  
  
    ngOnInit(): void {
      //console.log(this.id);
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

            this.newTodolist = new Todolist();
            this.todolist.unshift(createTodolist);
          console.log(this.todolist);
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
   // this.modalController.dismiss();
  }
  
      //Todos mit Titel und Todolist erstellen
      createTodo(): void {
  
        this.todoService.createTodo(this.newTodo)
          .subscribe(createTodo => {
            this.newTodo= new Todo();
            this.todos.unshift(createTodo);
          
            console.log(this.todos);
            
          });     
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


