import { Injectable } from '@angular/core';

import { Token } from '@apis/token'

interface ILogin {
  status  : number ;
  msg     : string ;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  token!: ILogin ;

  constructor() {}

  async login( username: string , password: string ) {

    this.token = await Token( username , password ) ;
    
    this.token.status === 200 
      ? localStorage.setItem( 'token' , this.token.msg ) 
      : console.log( 'error: ' , this.token.msg ) 
      ;
    

  }

}