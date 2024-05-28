import { useState, useEffect } from "react"
import CategoriesList from "./categories"
import ProductsList from "./products"
import Carousel from "../../component/carousel"

const Storefront = () => {

    const [products, setProducts] = useState(null)
    const [featuredProducts, setFeaturedProducts] = useState(null)

    const getProducts = ()  => {
        fetch("http://localhost:8080/api/products").then(res => {
            return res.json()
        }).then((data) => {
            console.log(data)
            setProducts(data)
            filterProducts(data)
        })
    }

    const filterByCategory = (category) => {
        fetch(`http://localhost:8080/api/products?category=${category.id}`).then(res => {
            return res.json()
        }).then((data) => {
            setProducts(data)
        })
    }
    
    const filterProducts = (products) =>{
        const filteredProduct = products.filter((product) => product.featured)
        setFeaturedProducts(filteredProduct)
        console.log(filteredProduct);
    }


    useEffect( ()=>{
        getProducts()
    }, [])

    const handleClick = (category) => {
        filterByCategory(category)
     }

    return (
        <div className="container-fluid mt-5">
            <Carousel products={featuredProducts}/>
            <div className="row">
                <div className="col-3 mt-5 pt-4">
                    <CategoriesList selectCategory={handleClick}/>
                </div>

                <div className="col-9 gx-5">
                <ProductsList products={products}/>
            </div>
            </div>
        </div>
    )
}

export default Storefront;