import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile/profile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserRoutingModule } from './user-routing.module';
import { ListSemesterComponent } from './list-semester/list-semester.component';

@NgModule({
  declarations: [ProfileComponent, ListSemesterComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule, UserRoutingModule
  ],
  exports: [ProfileComponent]
})
export class UserModule { }
