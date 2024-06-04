import { getToken } from "../../services/authService"
import { getAll } from "../../static/const"

const CustomerCard = ({content, fetchFunc, openModalFunc, editContentFunc, setPageFunc}) => {

    const handleBan = (id) => {
      if (window.confirm("Are you sure you want to ban this user?")) {
        fetch(getAll+`/${id}`, { 
          method: 'Post' ,
          headers: {Authorization: `Bearer ${getToken()}`}
        }).then(
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
              <div className="col"><h6>Name</h6></div>
              <div className="col"><h6>Usernamer</h6></div>
              <div className="col"><h6>Status</h6></div>
              <div className="col"><h6>Role</h6></div>
              <div className="col h6">Created On</div>
              <div className="col h6">Updated On</div>
          </div>
        </div>
        <div className="card-text">
        <div className ="row">
              <div className="col-1">{content.id}</div>
              <div className="col">{content.name}</div>
              <div className="col">{content.username}</div>
              <div className="col">{content.status}</div>
              <div className="col">{content.role}</div>
              <div className="col">{Date(content.createdOn).slice(0, 24)}</div>
              <div className="col">{Date(content.updatedOn).slice(0, 24)}</div>
          </div>
        </div>
        {content.status == "active" ?
        <a href="#" className="btn btn-danger mx-1" onClick={() => handleBan(content.id)}>Ban</a>:
        <a href="#" className="btn btn-danger mx-1" onClick={() => handleBan(content.id)}>Unban</a>}
      </div>
    </div>
      )
  }
  
  export default CustomerCard;