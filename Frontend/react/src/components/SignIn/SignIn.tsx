import { Avatar } from '@mui/material'
import { Typography } from '@mui/material'
import { LockOutlined } from '@mui/icons-material'

export default function SignIn() {
  return (
    <>
    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
      <LockOutlined />
    </Avatar>

    <Typography component="h1" variant="h5">
      Sign in
    </Typography>
    </>
  ) ;
}