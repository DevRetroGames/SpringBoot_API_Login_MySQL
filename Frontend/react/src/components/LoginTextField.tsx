import { TextField } from '@mui/material'

import { ILoginTextField } from "./ILoginTextField";

export default function LoginTextField( { params } : ILoginTextField ) {
  return (
    <TextField
      required
      fullWidth
      margin='normal'
      name={params.name}
      label={params.label}
      type={params.type}
      id={params.id}
      autoComplete={params.autoComplete}
      autoFocus={params.autoFocus}
    />
  ) ;
}