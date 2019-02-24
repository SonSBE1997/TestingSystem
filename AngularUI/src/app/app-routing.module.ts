import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { InternalServerComponent } from './errors/internal-server/internal-server.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'exam', pathMatch: 'full', redirectTo: '/exam' },
  { path: 'question', pathMatch: 'full', redirectTo: '/question' },
  { path: 'user', pathMatch: 'full', redirectTo: '/user' },
  { path: 'home', pathMatch: 'full', redirectTo: '/home' },
  { path: '404', component: NotFoundComponent },
  { path: '500', component: InternalServerComponent }
  // { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
