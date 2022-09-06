import { Injectable } from '@angular/core';

import { Token } from '@apis/token'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  token: string ;

  constructor() {
    this.token = '' ;
  }

  async login( username: string , password: string ) {
    this.token = await Token( username , password ) ;
    console.log( 'token: ' , this.token ) ;
  }

}