import { Component, OnInit } from '@angular/core';

import { Token } from '@apis/token'
import { __values } from 'tslib';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  token: Object = {} ;

  constructor() {
  }

  async ngOnInit() {
    this.token = await Token( 'correo@live.cl' , 'dev' ) ;
    console.log( 'token: ' , this.token ) ;
  }

}
