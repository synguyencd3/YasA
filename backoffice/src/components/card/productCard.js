import { getToken } from "../../services/authService";
import { productUrl } from "../../static/const";


const ProductCard = ({content, fetchFunc, openModalFunc, editContentFunc, setPageFunc}) => {

    const handleDelete = (id) => {
      if (window.confirm("Are you sure you want to delete this item?")) {
        fetch(productUrl+`/${id}`, { 
          method: 'DELETE',
          headers: {Authorization: `Bearer ${getToken()}`}
         } ).then(
          () =>{
            setPageFunc(0)
            fetchFunc()
          }
        )
        console.log("Item deleted");
      } else {
        console.log("Deletion cancelled");
      }
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
            <div className="col-1">{content.id}</div>
            <div className="col">{content.name}</div>
            <div className="col">{content.description}</div>
            <div className="col">{content.category.name}</div>
            <div className="col">{Date(content.createdOn).slice(0, 24)}</div>
            <div className="col">{Date(content.updatedOn).slice(0, 24)}</div>
        </div>
      </div>
      <a href="#" className="btn btn-primary mx-1" onClick={() => {
        editContentFunc(content)
        openModalFunc()
        }}>Edit</a>
      <a href="#" className="btn btn-danger mx-1" onClick={() =>handleDelete(content.id)}>Delete</a>
    </div>
  </div>)
}

export default ProductCard;