import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



import { NotePageRoutingModule } from './note-routing.module';

import { NotePage } from './note.page';
import { NgxColorsModule } from 'ngx-colors';
import { IonicModule } from '@ionic/angular';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NotePageRoutingModule,
    ReactiveFormsModule,
    NgxColorsModule
  ],
  declarations: [NotePage]
})
export class NotePageModule {}
