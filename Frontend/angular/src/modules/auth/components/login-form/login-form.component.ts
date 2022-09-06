import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { LoginService } from '@modules/auth/services/login.service'

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  data = {
    label: 'Email Address' ,
    placeholder: 'Email Address*' ,
    name: 'username'
  }

  loginForm = new FormGroup({
    username: new FormControl( '' , [ Validators.required , Validators.email ] ) ,
    password: new FormControl( '' , [ Validators.required ] )
  });

  get usernameControll(): FormControl {
    return this.loginForm.get( 'username' ) as FormControl ;
  }

  get passwordControll(): FormControl {
    return this.loginForm.get( 'password' ) as FormControl ;
  }

  constructor( private _service: LoginService ) {}

  login() {
    const credentials = this.loginForm.value ;
    this._service.login( credentials.username! , credentials.password! ) ;
  }

  ngOnInit(): void {}


}
