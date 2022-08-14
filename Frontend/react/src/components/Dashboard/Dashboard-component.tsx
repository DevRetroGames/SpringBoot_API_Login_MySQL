import logo from '@assets/img/AdminLTELogo.png'

export default function Dashboard() {

  return ( 

    <aside 
      className="main-sidebar sidebar-dark-primary elevation-4"
    >
      
      <a href="index3.html" className="brand-link">

        <img 
          src={logo}
          alt="AdminLTE Logo" 
          className="brand-image img-circle elevation-3"
          style={{opacity:".8"}} 
        />

        <span 
          className="brand-text font-weight-light"
        >AdminLTE 3
        </span>

      </a>

    </aside>

  ) ;

}