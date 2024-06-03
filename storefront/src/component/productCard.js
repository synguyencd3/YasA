const ProductCard = ({product,addToCartFunc}) => {
    return (
        <div className="col-lg-4 product-item" key={product.id}>
        <div className="p-5">
            <div className="card">
                <img src={product.image ?? "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaBqXPJxDAvLfz-d0uNwJtxUSGKexAZfWzkknNlUdU0A&s"} className="card-img-top" alt="..."/>
                <div className="card-body">
                <div className="text-center">
                    <a href={`products/${product.id}`}><h5 className="card-title">{product.name}</h5></a>
                    <p className="card-text">{product.description}</p>
                    <p><b>{product.price}</b>VND</p>
                </div>
                <button type="button" className="btn btn-dark" onClick={() => addToCartFunc(product)}>Add to cart</button>
                </div>
            </div>
        </div>
    </div>
    )
}

export default ProductCard