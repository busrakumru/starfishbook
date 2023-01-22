import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PublicRoutingModule } from './public-routing.module';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminBoardComponent } from './components/admin-board/admin-board.component';

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    AdminBoardComponent
  ],
  imports: [
    CommonModule,
    PublicRoutingModule,
    MatCardModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ]
})
export class PublicModule { }