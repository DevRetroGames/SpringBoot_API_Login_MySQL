import { BrowserRouter , Routes , Route } from 'react-router-dom'

import HomePages from './pages/HomePages'
import LoginPages from './pages/LoginPages'

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path='/' element={ <HomePages /> } />
      <Route path='/login' element={ <LoginPages /> } />
    </Routes>
    </BrowserRouter>
  )
}

export default App
