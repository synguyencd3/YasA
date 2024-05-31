import { useState, useEffect } from "react";

const List = ({Card, url, Form}) => {

    const [showModal, setShowModal] = useState(false);

    let [contents, setContents] = useState(null);
  
    let [page, setPage] = useState(0);

    let [editContent, setEditContent] = useState(null)

    const handleOpenModal = () => {
      setShowModal(true);
    };
  
    const handleCloseModal = () => {
      setShowModal(false);
    };

    const pageUp = () => {
        setPage(prevPage => prevPage + 1);
      };

    const pageDown = () => {
        if (page > 0) {
            setPage(prevPage => prevPage - 1);
          }
    }

    const fetchItem = () => {
        console.log("page "+page)
        fetch(url+`?size=4&page=${page}`).then(res => {
          return res.json()
      }).then((data) => {
          console.log(data)
          setContents(data)
      })
      }
  
    useEffect( () => {
      fetchItem();
    }, [page])
  
      return( 
      <div>

         {showModal && (
        <div className="modal show d-block" tabIndex="-1" role="dialog" style={{ display: 'block', backgroundColor: 'rgba(0, 0, 0, 0.5)' }}>
          <div className="modal-dialog" role="document">
            <div className="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Modal title</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={handleCloseModal}></button>
            </div>
              <div className="modal-body">
                <Form fetchFunc={fetchItem} toggleFunc={handleCloseModal} content={editContent}/>
              </div>
            </div>
          </div>
        </div>
      )}


        <button type="button" className="btn btn-success mb-4" onClick={() =>{setEditContent(null);handleOpenModal()}}>Add</button>
        <div className="Paging mx-4 mt-2">
            <nav aria-label="Page navigation example">
              <ul className="pagination">
                { page>0 ?  <li className="page-item" onClick={() => {pageDown()}}>
                  <a className="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>: <div></div>}

                {contents && contents.length >0 ?
                <li className="page-item" onClick={() => {pageUp()}}>
                  <a className="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li> :<div></div>}
              </ul>
            </nav>
          </div>
          {contents && contents.map((content) =>
          <div key = {content.id}>
            <Card content={content} fetchFunc={fetchItem} openModalFunc={handleOpenModal} editContentFunc={setEditContent}/> 
          </div>
          )}
  
          </div>
      )
}
  
  export default List;