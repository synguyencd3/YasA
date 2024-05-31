import { useEffect, useState } from "react";
import { categoryUrl } from "../../static/const";


const CategoriesList = ({selectCategory}) => {
    const [categories, setCategories] = useState(null);

    useEffect(()=>{
        fetch(categoryUrl).then(res => {
            return res.json()
        }).then((data) => {
            console.log(data);
            data.sort((a,b) => (a.id-b.id))
            setCategories(data)
        })
    }, [])


    return (
        <ol className="list-group list-group-numbered">
            {categories && categories.map((category) =>
            <li className="list-group-item d-flex justify-content-between align-items-start listCategory" key={category.id} onClick={() =>{selectCategory(category)}}>
                <div className="ms-2 me-auto">
                    <div className="fw-bold">{category.name}</div>
                    {category.description} 
                </div>
            </li>
            )}
        </ol>
    )
}

export default CategoriesList