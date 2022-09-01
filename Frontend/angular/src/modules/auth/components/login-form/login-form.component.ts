import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  inputUser = {
    class : 'form-control' ,
    type : 'email' ,
    id : 'user' ,
    placeholder : 'Email Address*' ,
    label : 'Email Address*'
  }

  inputPass = {
    class : 'form-control' ,
    type : 'password' ,
    id : 'pwd' ,
    placeholder : 'Password*' ,
    label : 'Password*'
  }

  constructor() { }

  ngOnInit(): void {
  }

}
