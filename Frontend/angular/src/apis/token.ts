import { environment as env } from '@environments/environment'

const axios = require( 'axios' ).default ;

const api = env.api + env.auth ;


export const Token = async( username: string , password: string ) => {

  try {

    const token = await axios.post( api + '/login' , {
      username ,
      password
    } ) ;

  } catch( err ) {
    console.log( err ) ;
  }

}