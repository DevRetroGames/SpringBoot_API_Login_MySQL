import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Routers } from './routers'

@NgModule({
  imports: [ RouterModule.forRoot( Routers ) ] ,
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
