import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
  @Input() appointmentTime: string;
  @Input() todolist2: any;

  minDate = new Date().toISOString();
  maxDate: any = (new Date()).getFullYear() + 10;
  change = true;


  constructor(
    public reloadService: ReloadService, private fb: FormBuilder, public modalController: ModalController, private todolistService: TodolistService, private todoService: TodoService
  ) { }

  newTodo: FormGroup;
  todos: Todo = new Todo();


  ngOnInit(): void {

    this.newTodo = this.fb.group({
      title: new FormControl(''),
      appointmentTime: new FormControl(''),
      todolist: new FormArray([])//this.fb.array([])
    })

    this.addProduct();
  }

  get todolist() {
    return this.newTodo.get('todolist') as FormArray;
  }

  saveTodo(): void {
    if (this.id) {
      this.updateTodo();
    } else {
      this.create();
    }
    //this.reloadService.reload();
    this.modalController.dismiss();
  }

  addProduct() {

    this.todolist.push(this.fb.group({
      text: new FormControl(''),
      finished: new FormControl(false),
      id: new FormControl('')
    }));
  }

  delete() {
    this.todolist.removeAt(this.todolist.value.findIndex(todo => todo.id === this.todolist.value.id))
  }

  create() {

    this.todoService.createTodo(this.newTodo.value)
      .subscribe(response => {
        console.log(response);
      });
    this.newTodo.markAllAsTouched();
  }

  updateTodo(): void {

    this.todoService.updateTodo(this.id, this.newTodo.value).subscribe(
      (response) => console.log(response)
    );
  }

  abbrechen() {
    this.modalController.dismiss();
  }
}


