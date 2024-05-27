
const ProductsList = ({products}) => {

    return (
        <div className="productList">
             <h1>New Product</h1>
                <div className="container overflow-hidden text-center">
                    <div className="row gx-1">
                    {products && products.map((product) =>
                         <div className="col-4" key={product.id}>
                         <div className="p-5">
                             <div className="card product-item">
                                 <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaBqXPJxDAvLfz-d0uNwJtxUSGKexAZfWzkknNlUdU0A&s" className="card-img-top" alt="..."/>
                                 <div className="card-body">
                                 <div className="text-center">
                                     <a href={`products/${product.id}`}><h5 className="card-title">{product.name}</h5></a>
                                     <p className="card-text">{product.description}</p>
                                     <p><b>{product.price}</b>VND</p>
                                 </div>
                                 <button type="button" className="btn btn-dark">Add to cart</button>
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