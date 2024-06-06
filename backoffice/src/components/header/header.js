import { logout } from "../../services/authService";

const Header = ({setToken}) => {
    return( 
        <nav className="navbar bg-body-tertiary">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            <a href="/"><h2 className="px-5">YasA</h2></a>
          </a>
          <button type="button" id="cartButton" className="btn btn-secondary mx-5" onClick={()=>{setToken(null);logout()}}>Logout</button>
        </div>
    </nav>
    )
}

export default Header;