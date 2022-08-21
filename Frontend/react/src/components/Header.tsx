import AppBar from '@mui/material/AppBar'
import IconButton from '@mui/material/IconButton'
import Toolbar from '@mui/material/Toolbar'
import Typography from '@mui/material/Typography'

import AccountCircleIcon from '@mui/icons-material/AccountCircle'

import HeaderToggle from '@components/HeaderToggle'

export default function Header() {

  return (
    <>
    <AppBar position="fixed" color="primary">
      <Toolbar>

        <IconButton color="inherit" >
          <HeaderToggle />
        </IconButton>

        <Typography variant='h6'>
          React Material Admin
        </Typography>

        <IconButton>
          <AccountCircleIcon />
        </IconButton>

      </Toolbar>
    </AppBar>
    </>
  ) ;
}