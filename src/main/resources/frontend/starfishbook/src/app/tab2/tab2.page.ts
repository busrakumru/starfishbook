import { Component, OnInit, Output } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { TodoPage } from '../modals/todo/todo.page';
import { Todo } from '../models/todo.model';
import { AuthService } from '../public/services/auth-service/auth.service';
import { TokenService } from '../public/services/token/token.service';
import { TodoService } from '../services/todo.service';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page  implements OnInit {
  


  todos: Todo[] = [];
  
  isLoggedIn = false;
  editing: boolean = false;
  editingTodo: Todo = new Todo();

  constructor(public authService: AuthService, private todoService: TodoService,public tokenService: TokenService,public modalController: ModalController) { }
 
  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    this.getTodo();
  }

  logout() {
    this.authService.logout();
   // window.location.reload();
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
     
    });console.log(todo.id)
    return await modal.present();
  }
}
