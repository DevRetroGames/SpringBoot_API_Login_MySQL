import Header from '@components/Header'

import useDashboard from '@contexts/useDashboard'

export default function Dashboard() {
  return(
    <useDashboard.Provider value='dashboard context'>
      <Header />
    </useDashboard.Provider>
  ) ;
}