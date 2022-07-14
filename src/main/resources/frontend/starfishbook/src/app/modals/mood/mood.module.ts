import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MoodPageRoutingModule } from './mood-routing.module';
import {MatIconModule} from '@angular/material/icon';
import { MoodPage } from './mood.page';
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
    MoodPageRoutingModule
  ],
  declarations: [MoodPage]
})
export class MoodPageModule {}
