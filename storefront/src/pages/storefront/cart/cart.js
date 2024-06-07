import { useCallback, useEffect, useState } from 'react';
import { cartUrl } from '../../../static/const';
import { getToken } from '../../../services/authService';
import debounce from 'lodash.debounce';


const CartItems = () => {

  let [cart, setCart] = useState(null)
  const handleDelete = (id) => {
    fetch(cartUrl+`/products/${id}`, {
      method: 'DELETE',
      headers: {
        Authorization: `Bearer ${getToken()}`,
        'Content-Type': 'application/json',
      }
    }).then(res => getCart())
  };

  const updateQuantity = (id, quantity) => {
    fetch(cartUrl+`/products/${id}`, {
      method: 'PATCH',
      headers: {
        Authorization: `Bearer ${getToken()}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        quantity: quantity
      })
    })
  };

  const debouncedUpdateQuantity = useCallback(debounce(updateQuantity, 300), []);

  const handleQuantityChange = (id, quantity) => {
    debouncedUpdateQuantity(id, quantity);
  };

  const getCart = () => {
    fetch(cartUrl, {
        headers: {
            Authorization: `Bearer ${getToken()}`,
            'Content-Type': 'application/json',
          },
    }).then(res => {
        return res.json()
    }).then((data) => {
        console.log("cart"+data)
        data.products.sort((a,b) => (a.productId - b.productId))
        setCart(data)
    })
  }

  useEffect(() => {
    getCart()
  }, [])

  return (
    <div className="container">
      <h6>Items</h6>
      <ul className="list-group">
        {cart && cart.products.map(item => (
          <li key={item.id} className="list-group-item d-flex justify-content-between align-items-center">
            <div>
              <h5 className="mb-1">{item.product.name}</h5>
              {/* <p className="mb-1" >{item.product.description}</p> */}
              <div className='row'>
              <small className='col-3'>Quantity:</small>
                <div className='col-3'>
                <input
                    type="number"
                    className="form-control form-control-sm"
                    defaultValue={item.quantity}
                    onChange={(e) => handleQuantityChange(item.product.id, e.target.value)}
                    min="1"
                  />
                </div>  
              </div>
            </div>
            <button className="btn btn-danger btn-sm" onClick={() => handleDelete(item.product.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CartItems

