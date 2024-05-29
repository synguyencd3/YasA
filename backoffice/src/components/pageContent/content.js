import { useState, useEffect } from "react";

const Content = () => {

  const [products, setProducts] = useState(null);

  useEffect( () => {
      fetch("http://localhost:8080/api/products").then(res => {
        return res.json()
    }).then((data) => {
        console.log(data)
        setProducts(data)
    })
  }, [])

    return( 
    <div>
        <div className="card" style={{height: "80vh"}}>
        {products && products.map((product) =>
          <div className="card m-4" key={product.id}>
            <div className="card-body m-2">
              <div className="card-title">
                <div className ="row">
                    <div className="col"><h5>Product name</h5></div>
                    <div className="col"><h5>Description</h5></div>
                    <div className="col"><h5>Category</h5></div>
                    <div className="col">Created On</div>
                    <div className="col">Updated On</div>
                </div>
              </div>
              <div className="card-text">
              <div className ="row">
                    <div className="col">{product.name}</div>
                    <div className="col">{product.description}</div>
                    <div className="col">{product.category.name}</div>
                    <div className="col">{Date(product.createdOn)}</div>
                    <div className="col">{Date(product.updatedOn)}</div>
                </div>
              </div>
              <a href="#" className="btn btn-primary">Go somewhere</a>
            </div>
          </div>
        )}
        </div>
    </div>
    )
}

export default Content;