import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TodocardComponent } from '../component/todocard/todocard.component';
import { Tab2Page } from './tab2.page';

const routes: Routes = [
  {
    path: '',
    component: Tab2Page,
  },
  {
    path: 'todocard',
    component: TodocardComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Tab2PageRoutingModule {}
