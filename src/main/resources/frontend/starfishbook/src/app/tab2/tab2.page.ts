import { Component, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { AlertController, ModalController } from '@ionic/angular';
import { TodoPage } from '../modals/todo/todo.page';
import { Todo } from '../models/todo.model';
import { AuthService } from '../public/services/auth-service/auth.service';
import { TokenService } from '../public/services/token/token.service';
import { TodoService } from '../services/todo.service';
import { TodolistService } from '../services/todolist.service';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit {

  todos: Todo[] = [];

  newTodo: FormGroup = new FormGroup({
    title: new FormControl(''),
    todolist: new FormArray([])

  })

  isLoggedIn = false;

  constructor(public authService: AuthService,  public alertController: AlertController,private todoService: TodoService,public tokenService: TokenService,public modalController: ModalController) { }
 
  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    this.getTodo();
  }

  getTodo(): void {
    this.todoService.getTodos()
      .subscribe((data: Todo[]) => {
        this.todos = data
      });
     
  }

  async openCard() {
    const modal = await this.modalController.create({
      component: TodoPage,
    });
    return await modal.present();
  }

  async deleteTodo(todo) {
    const alert = await this.alertController.create({
      header: 'Achtung!',
      message: 'Möchtest du deine Todo  wirklich <strong>löschen</strong>?',
      buttons: [
        {
          text: 'Abbrechen',
          role: 'cancel',
          cssClass: 'secondary',
          id: 'cancel-button',
          handler: () => {
            console.log('Abgebrochen');
          }
        }, {
          text: 'Löschen',
          id: 'confirm-button',
          handler: () => {
            this.todoService.deleteTodo(todo.id)
              .subscribe(() => {
                this.todos = this.todos.filter(todos => todos.id != todo.id);
              });
          }
        }
      ]
    });
    await alert.present();
  }

  async update(todo) {
    const modal = await this.modalController.create({
      component: TodoPage,
      componentProps: {
        'id': todo.id,
        'title': todo.title,
        'appointmentTime': todo.appointmentTime,
        'todolist2': todo.todolist
      }
    });
    return await modal.present();
  }
}
