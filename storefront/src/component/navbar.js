const Navbar = () => {
    return (
        <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">
            <h2 class="px-5">YasA</h2>
          </a>
          <button type="button" id="cartButton" class="btn btn-success mx-5">
            <i class="fa-solid fa-cart-shopping"></i> Cart</button>
        </div>
    </nav>
    );
}

export default Navbar;