import { useState } from "react"

export default function SidebarHook () {

  const [ open , setOpen ] = useState( true )

  const handleSidebar = () => {
    setOpen( !open )
  }

  return { open , handleSidebar }

}