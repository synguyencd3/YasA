import CategoryGroup from "./group objects/CategoryGroup";
import ProductGroup from "./group objects/ProductGroup";

const Sidemenu = ({setGroupFunc}) => {
    return( 
        <div className="list-group">
            <button type="button" className="list-group-item list-group-item-action" onClick={() =>setGroupFunc(ProductGroup)}>Products</button>
            <button type="button" className="list-group-item list-group-item-action" onClick={() =>setGroupFunc(CategoryGroup)}>Categories</button>
            <button type="button" className="list-group-item list-group-item-action">Orders</button>
            <button type="button" className="list-group-item list-group-item-action">Customers</button>
        </div>
    )
}

export default Sidemenu;