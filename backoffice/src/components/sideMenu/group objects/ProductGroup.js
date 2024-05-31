import { productUrl } from "../../../static/const";
import ProductCard from "../../card/productCard";
import NewProductForm from "../../form/productForm";

let ProductGroup = {
    card : ProductCard,
    url: productUrl,
    form: NewProductForm
  }

export default ProductGroup;