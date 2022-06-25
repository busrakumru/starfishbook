import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MoodPageRoutingModule } from './mood-routing.module';

import { MoodPage } from './mood.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MoodPageRoutingModule
  ],
  declarations: [MoodPage]
})
export class MoodPageModule {}
