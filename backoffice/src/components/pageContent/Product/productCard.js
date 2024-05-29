const ProductCard = ({product}) => {

    handleDelete = () => {
      
    }

    return (
    <div className="card mt-4 mx-3">
    <div className="card-body">
      <div className="card-title">
        <div className ="row">
            <div className="col-1"><h6>ID</h6></div>
            <div className="col"><h6>Product name</h6></div>
            <div className="col"><h6>Description</h6></div>
            <div className="col"><h6>Category</h6></div>
            <div className="col h6">Created On</div>
            <div className="col h6">Updated On</div>
        </div>
      </div>
      <div className="card-text">
      <div className ="row">
            <div className="col-1">{product.id}</div>
            <div className="col">{product.name}</div>
            <div className="col">{product.description}</div>
            <div className="col">{product.category.name}</div>
            <div className="col">{Date(product.createdOn).slice(0, 24)}</div>
            <div className="col">{Date(product.updatedOn).slice(0, 24)}</div>
        </div>
      </div>
      <a href="#" className="btn btn-primary mx-1">Edit</a>
      <a href="#" className="btn btn-danger mx-1">Delete</a>
    </div>
  </div>)
}

export default ProductCard;