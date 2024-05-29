const CategoryCard = ({category}) => {
    return (
        <div className="card mt-4 mx-3">
    <div className="card-body">
      <div className="card-title">
        <div className ="row">
            <div className="col"><h6>Category</h6></div>
            <div className="col"><h6>Description</h6></div>
            <div className="col h6">Created On</div>
            <div className="col h6">Updated On</div>
        </div>
      </div>
      <div className="card-text">
      <div className ="row">
            <div className="col">{category.name}</div>
            <div className="col">{category.description}</div>
            <div className="col">{Date(category.createdOn).slice(0, 24)}</div>
            <div className="col">{Date(category.updatedOn).slice(0, 24)}</div>
        </div>
      </div>
      <a href="#" className="btn btn-primary mx-1">Edit</a>
      <a href="#" className="btn btn-danger mx-1">Delete</a>
    </div>
  </div>
    )
}

export default CategoryCard;