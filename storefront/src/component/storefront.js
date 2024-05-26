import CategoriesList from "./categories"
import ProductsList from "./products"

const Storefront = () => {
    return (
        <div className="storeFront" class="container-fluid mt-5">
            <div class="row">
                <div class="col-3 mt-5 pt-4">
                    <CategoriesList/>
                </div>

                <div className="productList" class="col-9 gx-5">
                <ProductsList/>
            </div>
            </div>
        </div>
    )
}

export default Storefront;