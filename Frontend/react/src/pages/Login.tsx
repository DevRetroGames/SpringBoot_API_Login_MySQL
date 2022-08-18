import { 
  Box , 
  Container , 
  CssBaseline 
} from '@mui/material'
import { createTheme, ThemeProvider } from '@mui/material/styles'

import SignIn from '@components/SignIn/SignIn'
import InputSignIn from '@components/InputSignIn/InputSignIn'
import CButton from '@components/CButton/CButton'
import ForgotPassword from '@components/ForgotPassword/ForgotPassword'

const theme = createTheme();

const boxMain = {
  marginTop: 8 , 
  display: 'flex' , 
  flexDirection: 'column' , 
  alignItems: 'center'
}

export default function LoginPage() {

  return (
    <ThemeProvider theme={ theme }>
      <Container component="main" maxWidth="xs">
        <CssBaseline />

        <Box sx = { boxMain } >

          <SignIn />

          <Box component="form" noValidate sx={{ mt: 1 }}>
            <InputSignIn />
            <CButton />
            <ForgotPassword />
          </Box>

        </Box>

      </Container>
    </ThemeProvider>
  );
}