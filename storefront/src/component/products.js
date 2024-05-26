import { useEffect, useState } from "react";

const ProductsList = () => {

    const [products, setProducts] = useState(null);

    useEffect(()=>{
        fetch("http://localhost:8080/api/products").then(res => {
            return res.json()
        }).then((data) => {
            console.log(data);
            setProducts(data)
        })
    }, [])

    return (
        <div className="productList">
             <h1>New Product</h1>
                <div class="container overflow-hidden text-center">
                    <div class="row gx-1">
                    {products && products.map((product) =>
                         <div class="col-4">
                         <div class="p-5">
                             <div class="card product-item">
                                 <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaBqXPJxDAvLfz-d0uNwJtxUSGKexAZfWzkknNlUdU0A&s" class="card-img-top" alt="..."/>
                                 <div class="card-body">
                                 <div class="text-center">
                                     <a href="#"><h5 class="card-title">{product.name}</h5></a>
                                     <p class="card-text">{product.description}</p>
                                     <p><b>{product.price}</b>VND</p>
                                 </div>
                                 <button type="button" class="btn btn-dark">Add to cart</button>
                                 </div>
                             </div>
                         </div>
                     </div>
                    )}
                    </div>
                </div>
        </div>
    )
}

export default ProductsList;