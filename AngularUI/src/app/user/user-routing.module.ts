import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { ListSemesterComponent } from './list-semester/list-semester.component';
import { ListTestComponent } from './list-test/list-test.component';

const routes: Routes = [
  {
    path: 'user',
    children: [
      {
        path: '',
        component: ProfileComponent,
        pathMatch: 'full'
      },
      {
        path: 'list-semester',
        component: ListSemesterComponent
      },
      {
        path: 'list-semester/:id/list-test',
        component: ListTestComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {}
