import { useState, useEffect } from "react"
import CategoriesList from "./categories"
import ProductsList from "./products"
import FeaturedCarousel from "../../component/carousel"
import { categoryUrl, productUrl } from "../../static/const"
import Dropdown from 'react-bootstrap/Dropdown';
import SortDropdown from "../../component/sort"

const Storefront = ({handleCloseModal, showModal}) => {

    const [products, setProducts] = useState(null)
    const [featuredProducts, setFeaturedProducts] = useState(null)
    const [categories, setCategories] = useState(null);
    const [category, setCategory] = useState(null);
    let [page, setPage] = useState(0);
    let [lastPage, setLastPage] = useState(false);
    let [firstPage, setFirstPage] = useState(false);
    let [sort, setSort] = useState("asc");
    let [sortBy, setSortBy] = useState("id");

    const getCategories = () => {
        fetch(categoryUrl).then(res => {
            return res.json()
        }).then((data) => {
            data.content.sort((a,b) => (a.id-b.id))
            setCategories(data.content)
        })
    }

    const getProducts = ()  => {
        let fetchUrl = productUrl+`?size=6&page=${page}&sort=${sort}&sortBy=${sortBy}`
        if (category != null ) fetchUrl=fetchUrl +`&category=${category.id}`
        fetch(fetchUrl).then(res => {
            return res.json()
        }).then((data) => {
            console.log(data)
            setLastPage(data.last)
            setFirstPage(data.first)
            setProducts(data.content)

        })
    }

    const getFeaturedProducts = () =>{
        let fetchUrl = productUrl+`?size=12&page=0`
        fetch(fetchUrl).then(res => {
            return res.json()
        }).then((data) => {
            console.log(data)
            const filteredProduct = data.content.filter((data) => data.featured)
        setFeaturedProducts(filteredProduct)
        })
    }

    useEffect( ()=>{
        getProducts()
        getCategories()
        getFeaturedProducts()
    }, [page, category, sort, sortBy])

    const handleClick = (category) => {
        setCategory(category)
     }

     const pageUp = () => {
        setPage(prevPage => prevPage + 1);
      };

    const pageDown = () => {
        if (page > 0) {
            setPage(prevPage => prevPage - 1);
          }
    }

    return (
        <div className="container-fluid mt-5">
            <FeaturedCarousel products={featuredProducts}/>
            <div className="row">
                <div className="col-3 mt-5 pt-4">
                <CategoriesList categories={categories} selectCategory={handleClick}/>
                <SortDropdown setSort={setSort} setSortBy={setSortBy}/>
                </div>

                <div className="col-9 gx-5">
                <ProductsList products={products}/>

                

                <div className="Paging mx-4 mt-2">
                <nav aria-label="Page navigation example">
                <ul className="pagination">
                    {console.log(firstPage)}
                    {console.log(lastPage)}
                    { !firstPage ?  <li className="page-item" onClick={() => {pageDown()}}>
                    <a className="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                    </li>: <div></div>}

                    { !lastPage ?
                    <li className="page-item" onClick={() => {pageUp()}}>
                    <a className="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                    </li> :<div></div>}
                </ul>
                </nav>
                </div>
            </div>
            </div>
        </div>
    )
}

export default Storefront;