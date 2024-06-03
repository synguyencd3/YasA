import { useState, useEffect, useMemo } from "react";

const List = ({Card, url, Form}) => {

    const [showModal, setShowModal] = useState(false);
    let [contents, setContents] = useState(null);
    let [page, setPage] = useState(0);
    let [editContent, setEditContent] = useState(null);
    let [lastPage, setLastPage] = useState(false);
    let [firstPage, setFirstPage] = useState(false);

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
          setLastPage(data.last)
          setFirstPage(data.first)
          setContents(data.content)
      })
      }

    useMemo(() => {
      setContents(null)
      setPage(0)
    }, [Card])
  
    useEffect( () => {
      fetchItem();
    }, [page, Card])
  
      return(
      <div>
        {console.log("rendering")}
         {showModal && (
        <div className="modal show d-block" tabIndex="-1" role="dialog" style={{ display: 'block', backgroundColor: 'rgba(0, 0, 0, 0.5)' }}>
          <div className="modal-dialog" role="document">
            <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title">Modal title</h5>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={handleCloseModal}></button>
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

          { contents && contents.map((content) =>
          <div key = {content.id}>
            <Card content={content} fetchFunc={fetchItem} openModalFunc={handleOpenModal} editContentFunc={setEditContent}/> 
          </div>
          )}
  
          </div>
      )
}
  
  export default List;