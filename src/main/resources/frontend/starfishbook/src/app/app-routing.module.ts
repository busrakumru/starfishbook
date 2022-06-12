import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';



const routes: Routes = [
  {
    path: 'tabs',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  }, {
    path: '',
    loadChildren: () => import('./public/public.module').then(m => m.PublicModule)
  },
  {
    path: '**',
    redirectTo: 'public',
    pathMatch: 'full'
  },
  {
    path: 'note',
    loadChildren: () => import('./modals/notes/note/note.module').then(m => m.NotePageModule)
  },
  {
    path: 'todo',
    loadChildren: () => import('./modals/todo/todo.module').then( m => m.TodoPageModule)
  }

];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
