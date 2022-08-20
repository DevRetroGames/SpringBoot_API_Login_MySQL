import Grid from '@mui/material/Grid'

import logo from '@assets/img/icon.png'

const sxLogo = {
  backgroundImage: `url(${logo})` ,
  backgroundRepeat: 'no-repeat',
  backgroundSize: 'cover',
  backgroundPosition: 'center',
}

export default function LoginSideLogo() {
  return(
    <Grid
      item
      xs={ false }
      sm={ 4 }
      md={ 7 }
      sx={ sxLogo }
    />
  ) ;
}