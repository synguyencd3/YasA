import { useState, useEffect } from "react"
import CategoriesList from "./categories"
import ProductsList from "./products"
import Carousel from "../../component/carousel"
import { categoryUrl, productUrl } from "../../static/const"

const Storefront = ({handleCloseModal, showModal}) => {

    const [products, setProducts] = useState(null)
    const [featuredProducts, setFeaturedProducts] = useState(null)
    const [categories, setCategories] = useState(null);


    const getCategories = () => {
        fetch(categoryUrl).then(res => {
            return res.json()
        }).then((data) => {
            data.content.sort((a,b) => (a.id-b.id))
            setCategories(data.content)
        })
    }

    const getProducts = ()  => {
        fetch(productUrl).then(res => {
            return res.json()
        }).then((data) => {
            setProducts(data.content)
            filterIsFeaturedProducts(data.content)
        })
    }

    const filterByCategory = (category) => {
        fetch(productUrl+`?category=${category.id}`).then(res => {
            return res.json()
        }).then((data) => {
            setProducts(data.content)
        })
    }
    
    const filterIsFeaturedProducts = (products) =>{
        const filteredProduct = products.filter((product) => product.featured)
        setFeaturedProducts(filteredProduct)
    }

    useEffect( ()=>{
        getProducts()
        getCategories();
    }, [])

    const handleClick = (category) => {
        filterByCategory(category)
     }

    return (
        <div>
        <div className="container-fluid mt-5">
            <Carousel products={featuredProducts}/>
            <div className="row">
                <div className="col-3 mt-5 pt-4">
                    <CategoriesList categories={categories} selectCategory={handleClick}/>
                </div>

                <div className="col-9 gx-5">
                <ProductsList products={products}/>
            </div>
            </div>
        </div>
        </div>
    )
}

export default Storefront;