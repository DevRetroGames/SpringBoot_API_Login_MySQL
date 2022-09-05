import { environment as env } from '@environments/environment'
import axios from 'axios'

const api = env.api + env.auth ;


export const Token = async( username: string , password: string ) => {

  try {
    const response = await axios.post( api + '/login' , { username , password } ) ;
    return response.data.token ;
  } catch( err ) {
    console.log( err ) ;
    return 'error' ;
  }

}