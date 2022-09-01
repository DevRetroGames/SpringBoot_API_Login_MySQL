import { Component, OnInit , Input } from '@angular/core';

import { ILoginButton } from './ILoginButton'

@Component({
  selector: 'app-login-button',
  templateUrl: './login-button.component.html',
  styleUrls: ['./login-button.component.css']
})
export class LoginButtonComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
