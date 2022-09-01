import {
  createTheme ,
  CssBaseline , 
  Grid ,
  ThemeProvider
} from '@mui/material'

import LoginSideLogo from '@components/Login/LoginSideLogo'
import LoginForm from '@components/Login/LoginForm'

const theme = createTheme()

export default function LoginPage() {
  return (
    <ThemeProvider theme={ theme } >
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <LoginSideLogo />
        <LoginForm />
      </Grid>
    </ThemeProvider>
  )
}