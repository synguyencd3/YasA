import { useEffect, useState } from "react";


const CategoriesList = ({selectCategory}) => {
    const [categories, setCategories] = useState(null);

    useEffect(()=>{
        fetch("http://localhost:8080/api/categories").then(res => {
            return res.json()
        }).then((data) => {
            console.log(data);
            setCategories(data)
        })
    }, [])


    return (
        <ol className="list-group list-group-numbered">
            {categories && categories.map((category) =>
            <li className="list-group-item d-flex justify-content-between align-items-start" key={category.id} onClick={() =>{selectCategory(category)}}>
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