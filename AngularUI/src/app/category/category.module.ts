import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListCategoryComponent } from './list-category/list-category.component';

@NgModule({
  declarations: [ListCategoryComponent],
  imports: [CommonModule],
  exports: [ListCategoryComponent]
})
export class CategoryModule {}
