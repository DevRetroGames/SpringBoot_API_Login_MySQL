import ArrowBackIcon from '@mui/icons-material/ArrowBack'
import MenuIcon from '@mui/icons-material/Menu'

import { Title } from '@environments/NameEnv'
import SidebarHook from '@hooks/SidebarHook'

export default function HeaderToggle() {

  const { open , handleSidebar } = SidebarHook()

  return (
    <>
    <span>{ open ? Title : '' }</span>
    { open 
        ? <ArrowBackIcon onClick={ handleSidebar } /> 
        : <MenuIcon onClick={ handleSidebar } /> 
    }
    </>
  )
}