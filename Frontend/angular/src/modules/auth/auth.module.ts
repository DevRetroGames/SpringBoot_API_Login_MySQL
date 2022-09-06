import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms'

import { AuthRoutingModule } from './auth-routing.module';

import { LoginComponent } from '@modules/auth/pages/login/login.component'
import { LoginFormComponent } from './components/login-form/login-form.component'

import { InputErrorContainerComponent } from '@components/input-error-container/input-error-container.component'
import { InputErrorMsgComponent } from '@components/input-error-msg/input-error-msg.component'

import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { MatButtonModule } from '@angular/material/button'
import { MatCardModule } from '@angular/material/card'
import { MatGridListModule } from '@angular/material/grid-list'

@NgModule({
  declarations: [
    LoginComponent ,
    LoginFormComponent ,
    InputErrorContainerComponent ,
    InputErrorMsgComponent
  ],
  imports: [
    CommonModule ,
    AuthRoutingModule ,    
    FormsModule ,
    ReactiveFormsModule ,
    MatFormFieldModule ,
    MatInputModule , 
    MatButtonModule ,
    MatCardModule ,
    MatGridListModule
  ]
})
export class AuthModule { }
