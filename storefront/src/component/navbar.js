const Navbar = () => {
    return (
        <nav className="navbar bg-body-tertiary">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            <a href="/"><h2 className="px-5">YasA</h2></a>
          </a>
          <button type="button" id="cartButton" className="btn btn-success mx-5">
            <i className="fa-solid fa-cart-shopping"></i> Cart</button>
        </div>
    </nav>
    );
}

export default Navbar;