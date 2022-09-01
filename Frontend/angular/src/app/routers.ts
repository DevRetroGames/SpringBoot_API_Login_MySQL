import { Routes } from '@angular/router';

export const Routers: Routes = [
  { 
    path: 'login' , 
    loadChildren: () => 
      import( `@modules/auth/auth.module` )
        .then( m => m.AuthModule ) 
  }
]