const Products = () => {
    return (
        <div className="productList">
             <h1>New Product</h1>
                <div class="container overflow-hidden text-center">
                    <div class="row gx-1">
                        <div class="col">
                            <div class="p-3">
                                <div class="card product-item">
                                    <img src="/images/product-1.png" class="card-img-top" alt="..."/>
                                    <div class="card-body">
                                    <div class="text-center">
                                        <a href="#"><h5 class="card-title">Product 01</h5></a>
                                        <p class="card-text">This is description of product 01</p>
                                        <p><b>50,000</b>VND</p>
                                    </div>
                                    <button type="button" class="btn btn-dark">Add to cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="p-3">
                                <div class="card product-item">
                                    <img src="/images/product-2.png" class="card-img-top" alt="..."/>
                                    <div class="card-body">
                                    <div class="text-center">
                                        <a href="#"><h5 class="card-title">Product 02</h5></a>
                                        <p class="card-text">This is description of product 02</p>
                                        <p><b>50,000</b>VND</p>
                                    </div>
                                    <button type="button" class="btn btn-dark">Add to cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="p-3">
                                <div class="card product-item">
                                    <img src="/images/product-3.png" class="card-img-top" alt="..."/>
                                    <div class="card-body">
                                    <div class="text-center">
                                        <a href="#"><h5 class="card-title">Product 03</h5></a>
                                        <p class="card-text">This is description of product 03</p>
                                        <p><b>50,000</b>VND</p>
                                    </div>
                                    <button type="button" class="btn btn-dark">Add to cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    )
}

export default Products;