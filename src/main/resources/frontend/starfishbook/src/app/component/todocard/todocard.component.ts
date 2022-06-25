import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { TodoPage } from 'src/app/modals/todo/todo.page';
import { Todo } from 'src/app/models/todo.model';
import { Todolist } from 'src/app/models/todolist.model';
import { AuthService } from 'src/app/public/services/auth-service/auth.service';
import { TodoService } from 'src/app/services/todo.service';
import { TodolistService } from 'src/app/services/todolist.service';

@Component({
  selector: 'app-todocard',
  templateUrl: './todocard.component.html',
  styleUrls: ['./todocard.component.scss'],
})
export class TodocardComponent implements OnInit {
  

  //todolist: Todolist[] = [];
  todos: Todo[] = [];
  
  isLoggedIn = false;
  editing: boolean = false;
  editingTodo: Todo = new Todo();

  constructor( private todoService: TodoService,private todolistService: TodolistService,public modalController: ModalController) { }
 
  ngOnInit(): void {
    this.getTodo();
  }

  getTodo(): void {
    this.todoService.getTodos()
      .subscribe(todos => this.todos = todos);
  }


  async openCard() {

    const modal = await this.modalController.create({
      component: TodoPage,
      
    });
    return await modal.present();
  }

   //eine Todo aus der Liste löschen
  deleteTodo(id: any): void {
    this.todoService.deleteTodo(id)
      .subscribe(() => {
        this.todos = this.todos.filter(todos => todos.id != id);
      });
  }

  async update(todo) {

    const modal = await this.modalController.create({
      component: TodoPage,
      componentProps: {
        'id': todo.id,
        'title': todo.title,
        'todolist': todo.todoList
      }
     
    });console.log(todo.id);
    console.log(todo.title);
    console.log(todo.todoList);
    return await modal.present();
  }
}
