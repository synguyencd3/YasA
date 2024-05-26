import { useEffect, useState } from "react";


const CategoriesList = () => {
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
        <ol class="list-group list-group-numbered">
            {categories && categories.map((category) =>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto" key={category.id}>
                    <div class="fw-bold">{category.name}</div>
                    {category.description} 
                </div>
            </li>
            )}
        </ol>
    )
}

export default CategoriesList