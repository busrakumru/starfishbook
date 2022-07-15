import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, FormControl, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { TodoPageRoutingModule } from './todo-routing.module';
import {MatIconModule} from '@angular/material/icon';
import { TodoPage } from './todo.page';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    TodoPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [TodoPage]
})
export class TodoPageModule {}
