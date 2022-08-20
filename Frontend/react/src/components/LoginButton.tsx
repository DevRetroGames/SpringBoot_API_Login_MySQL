import { Button } from '@mui/material'

export default function LoginButton() {
  return (
    <Button
      type="submit"
      fullWidth
      variant="contained"
      sx={{ mt: 3, mb: 2 }}
    >
      Sign In
    </Button>
  ) ;
}