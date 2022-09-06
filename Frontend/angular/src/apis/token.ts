import { environment as env } from '@environments/environment'
import axios from 'axios'

const api = env.api + env.auth ;


export const Token = async( username: string , password: string ) => {

  try {
    return await ( 
      await axios.post( api + '/login' , { username , password } ) 
    ).data.token ;
  } catch( err ) {
    console.log( err ) ;
    return 'error' ;
  }

}