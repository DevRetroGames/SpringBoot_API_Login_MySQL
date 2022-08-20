import { Box , Grid , Paper } from '@mui/material'

import LoginTitle from '@components/LoginTitle'
import LoginTextField from '@components/LoginTextField'
import LoginButton from '@components/LoginButton'

import { boxMain , inputUser , inputPwd } from '@utils/LoginFormUtil'

export default function LoginForm() {
  return (
    <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square >
      <Box sx={ boxMain } >
        
        <LoginTitle />

        <Box component="form" noValidate sx={{ mt: 1 }}>
          <LoginTextField params={ inputUser } />
          <LoginTextField params={ inputPwd } />
          <LoginButton />
        </Box>

      </Box>
    </Grid>
  ) ;
}