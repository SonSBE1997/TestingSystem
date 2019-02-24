import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './not-found/not-found.component';
import { InternalServerComponent } from './internal-server/internal-server.component';

@NgModule({
  declarations: [NotFoundComponent, InternalServerComponent],
  imports: [CommonModule],
  exports: [NotFoundComponent, InternalServerComponent]
})
export class ErrorsModule {}
