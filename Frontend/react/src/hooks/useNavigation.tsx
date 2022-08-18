import { useState } from 'react'

export function useToggleNavigation() {

  const [ isOpen , setIsOpen ] = useState( false ) ;

  const toggle = () => setIsOpen( !isOpen ) ;

  return { isOpen , toggle }

}