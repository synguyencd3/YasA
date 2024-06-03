import { useEffect, useState } from "react";
import CartItems from "../pages/storefront/cart/cart";
import { cartUrl } from "../static/const";
import { getToken, logout } from "../services/authService";


const Navbar = () => {

  
  let [showModal, setShowModal] = useState(false);


    const handleOpenModal = () => {
        setShowModal(true);
      };
    
    const handleCloseModal = () => {
      setShowModal(false);
    };


    return (
      <div>
        {showModal && (
        <div className="modal show d-block" tabIndex="-1" role="dialog" style={{ display: 'block', backgroundColor: 'rgba(0, 0, 0, 0.5)' }}>
          <div className="modal-dialog" role="document">
            <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title">Cart</h5>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={handleCloseModal}></button>
            </div>
              <div className="modal-body">
                <CartItems/>
              </div>
            </div>
          </div>
        </div>
      )}
        <nav className="navbar bg-body-tertiary">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            <a href="/"><h2 className="px-5">YasA</h2></a>
          </a>
          <div>
          <button type="button" id="cartButton" className="btn btn-success" onClick={()=>handleOpenModal()}>Cart</button>
          <button type="button" id="cartButton" className="btn btn-secondary mx-3" onClick={()=>logout()}>Logout</button>
          </div>
        </div>
    </nav>
    </div>
    );
}

export default Navbar;