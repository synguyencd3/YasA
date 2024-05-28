import { useParams } from 'react-router';
import { useState, useEffect } from "react"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const ProductDetail = () => {

    const { id } = useParams();

    const [product, setProduct] = useState(null)
	const [comments, setComments] = useState(null)

    const getProduct =() => {
        fetch("http://localhost:8080/api/products/"+id).then(res => {
            return res.json()
        }).then((data) => {
            setProduct(data)
        })
    }

	const getComments = () => {
		fetch("http://localhost:8080/api/ratings?product="+id).then(res => {
            return res.json()
        }).then((data) => {
            console.log(data);
            setComments(data)
        })
	}

    useEffect(()=>{
        getProduct()
		getComments()
     }, [])
    
    return (
        <div className="container">
		<div className="card">
			<div className="container-fliud">
				<div className="row">
					<div className="col-md-6">
						  <div className="tab-pane active" id="pic-1"><img src="https://cdn.shopify.com/s/files/1/0533/2089/files/placeholder-images-image_large.png?v=1530129081" /></div>
					</div>
					<div className="text-start col-md-5">
                        {product && <h3 className="product-title mt-4">{product.name}</h3>}
						<div className="rating">
							<div className="stars">
								<span className="fa fa-star checked"></span>
								<span className="fa fa-star checked"></span>
								<span className="fa fa-star checked"></span>
								<span className="fa fa-star"></span>
								<span className="fa fa-star"></span>
							</div>
						</div>
						{product && <p className="product-description mt-3">{product.description}</p>}
						{product && <h4 className="price">current price: <span>{product.price}</span></h4>}
						<button className="btn btn-primary mt-3" type="button">add to cart</button>
					</div>
				</div>
			</div>
		</div>
		<h3 className="mt-3">Comments</h3>
		{comments && comments.map((comment) =>
		<div className="card mt-2">
		<div className="card-body">
			<h5 className="card-title">User: {comment.userId}</h5>
			<h6 className="card-subtitle mb-2 text-muted">score: {comment.score}/5</h6>
			<p className="card-text">comment: "{comment.content}"</p>
		</div>
		</div>
		)}
	</div>
    )
}

export default ProductDetail;