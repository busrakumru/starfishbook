import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { BoardAdminComponent } from './board-admin/board-admin.component';

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
    path: 'category',
    loadChildren: () => import('./modals/category/category.module').then( m => m.CategoryPageModule)
  },
  {
    path: 'todo',
    loadChildren: () => import('./modals/todo/todo.module').then( m => m.TodoPageModule)
  },
  {
    path: 'mood',
    loadChildren: () => import('./modals/mood/mood.module').then(m => m.MoodPageModule)
  },
  {
    path: 'settings',
    loadChildren: () => import('./settings/settings.module').then(m => m.SettingsPageModule)
  },
  {
    path: 'category-list',
    loadChildren: () => import('./modals/category-list/category-list.module').then( m => m.CategoryListPageModule)
  }

];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
