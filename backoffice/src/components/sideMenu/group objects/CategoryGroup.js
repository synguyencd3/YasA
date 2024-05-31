import { categoryUrl } from "../../../static/const";
import CategoryCard from "../../card/CategoryCard";
import NewCategoryForm from "../../form/categoryForm";

let CategoryGroup = {
    card : CategoryCard,
    url: categoryUrl,
    form: NewCategoryForm,
  }

export default CategoryGroup;