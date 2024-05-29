import { useState, useEffect } from "react";

const List = ({Card, url}) => {

    let [contents, setContents] = useState(null);
  
    let [page, setPage] = useState(0);

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
        fetch(`http://localhost:8080/api/products?size=4&page=${page}`).then(res => {
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
        <button type="button" className="btn btn-success mb-4">Add</button>
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
            <Card product={content} fetchFunc={fetchItem}/> 
          </div>
          )}
  
          </div>
      )
}
  
  export default List;