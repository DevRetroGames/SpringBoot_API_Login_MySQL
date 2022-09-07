import { environment as env } from '@environments/environment'
import axios from 'axios'

const api = env.api + env.auth ;


export const Token = async( username: string , password: string ) => {

  return await axios.post( api + '/login' , { username , password } )
    .then( ( jwt ) => {
      return { 'status': jwt.status , 'msg': jwt.data.token } 
    })
    .catch( ( error ) => { 
      return { 'status': error.response.status , 'msg': error.response.data.cause } 
    })

}