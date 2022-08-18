import { useState } from 'react'

export function useToggleSidebar() {

  const [ collapsed , setCollapsed ] = useState( false ) ;

  const toggleNavbar = () => setCollapsed( !collapsed ) ;

  return { collapsed , toggleNavbar }

}