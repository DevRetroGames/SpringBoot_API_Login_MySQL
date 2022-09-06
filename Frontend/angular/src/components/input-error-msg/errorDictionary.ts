interface IErrorsDictionary {
  [ key: string ]: string ;
}

export const ErrorsDictionary: IErrorsDictionary = {
  required: 'El campo es obligatorio.' ,
  email:    'Debe ser un email válido.'
}