import {
  createTheme ,
  CssBaseline , 
  Grid ,
  ThemeProvider
} from '@mui/material'

import LoginSideLogo from '@components/LoginSideLogo'
import LoginForm from '@components/LoginForm'

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