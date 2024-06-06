import ProductCard from "../../component/productCard";
import { getToken } from "../../services/authService";
import { cartUrl } from "../../static/const";


const ProductsList = ({products}) => {

    const addToCart = (product) => {

        const data = {
            productId: product.id,
            quantity: 1
        }
        console.log(data)
        fetch(cartUrl, {
            method: 'POST',
            headers: {
                Authorization: `Bearer ${getToken()}`,
                'Content-Type': 'application/json',
              },
              body: JSON.stringify(data)
        })
    }

    return (
        <div className="productList">
             <h1>New Product</h1>
                <div className="container-fluid overflow-hidden text-center">
                    <div className="row gx-1">
                        {console.log(products)}
                    {products && products.map((product) =>
                     <ProductCard product = {product} addToCartFunc={addToCart}/>
                    )}
                    </div>
                </div>
        </div>
    )
}

export default ProductsList;