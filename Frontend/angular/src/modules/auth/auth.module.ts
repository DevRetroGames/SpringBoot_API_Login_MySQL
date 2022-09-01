import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms'

import { AuthRoutingModule } from './auth-routing.module';

import { LoginComponent } from '@modules/auth/pages/login/login.component'
import { LoginFormComponent } from './components/login-form/login-form.component'
import { LoginButtonComponent } from './components/login-button/login-button.component'
import { LoginInputComponent } from './components/login-input/login-input.component'

@NgModule({
  declarations: [
    LoginComponent ,
    LoginFormComponent ,
    LoginButtonComponent ,
    LoginInputComponent
  ],
  imports: [
    CommonModule ,
    AuthRoutingModule ,
    FormsModule ,
    ReactiveFormsModule
  ]
})
export class AuthModule { }
