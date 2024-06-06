
const CategoriesList = ({categories, selectCategory}) => {

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