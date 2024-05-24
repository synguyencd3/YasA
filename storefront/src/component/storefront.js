import Categories from "./categories"
import Products from "./products"

const Storefront = () => {
    return (
        <div className="storeFront" class="container-fluid mt-5">
            <div class="row">
                <div class="col-3 mt-5 pt-4">
                    <Categories/>
                </div>

                <div className="productList" class="col-9 gx-5">
                <Products/>
            </div>
            </div>
        </div>
    )
}

export default Storefront;