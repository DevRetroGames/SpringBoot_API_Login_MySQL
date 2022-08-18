import { Button } from '@mui/material'

export default function CButton() {
  return (
    <>
    <Button
      fullWidth
      sx={{ mt: 3 , mb: 2 }}
      type = 'submit'
      variant = 'contained'
    >
      Sign In
    </Button>
    </>
  ) ;
}