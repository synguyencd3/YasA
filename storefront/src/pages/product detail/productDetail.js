import { useParams } from 'react-router';
import { useState, useEffect } from "react"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { getToken } from '../../services/authService';
import { cartUrl, productUrl, ratingUrl } from '../../static/const';

const ProductDetail = () => {

    const { id } = useParams();

    const [product, setProduct] = useState(null)
	const [comments, setComments] = useState(null)
	let [page, setPage] = useState(0);
    let [lastPage, setLastPage] = useState(false);
    let [firstPage, setFirstPage] = useState(false);
	let [commentForm, setCommentForm] = useState("")
	let [stars, setStars] = useState(1); 

    const getProduct =() => {
        fetch(productUrl+`/${id}`).then(res => {
            return res.json()
        }).then((data) => {
            setProduct(data)
        })
    }

	const getComments = () => {
		fetch(ratingUrl+`?page=${page}&size=3&product=`+id).then(res => {
            return res.json()
        }).then((data) => {
            console.log(data);
			setLastPage(data.last)
            setFirstPage(data.first)
            setComments(data.content)
        })
	}

	const pageUp = () => {
        setPage(prevPage => prevPage + 1);
      };

    const pageDown = () => {
        if (page > 0) {
            setPage(prevPage => prevPage - 1);
          }
    }

	const rating = (e) => {
		e.preventDefault()
		console.log(commentForm)
		let data = {
			content : commentForm,
			score : 0,
			product: id
		}
		fetch(ratingUrl, {
			method: 'POST',
			headers: {
				Authorization: `Bearer ${getToken()}`,
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(data)
		}).then(()=>getComments())
	}

	const addToCart = () => {
        const data = {
            productId: id,
            quantity: 1
        }
        console.log(data)
        fetch(cartUrl, {
            method: 'POST',
            headers: {
                Authorization: `Bearer ${getToken()}`,
                'Content-Type': 'application/json',
              },
              body: JSON.stringify(data)
        })
    }

    useEffect(()=>{
        getProduct()
		getComments()
     }, [page])
    
    return (
        <div className="container">
		<div className="card">
			<div className="container-fliud">
				<div className="row">
					<div className="col-md-6">
						  <div className="tab-pane active" id="pic-1">
							<img className="product-image" src={product && product.image!= null? product.image :`https://cdn.shopify.com/s/files/1/0533/2089/files/placeholder-images-image_large.png?v=1530129081`} />
							</div>
					</div>
					<div className="text-start col-md-5">
                        {product && <h3 className="product-title mt-4">{product.name}</h3>}
						{product && <h5 className="product-title mt-4">{product.rating.toFixed(1)}<span class="fa fa-star checked"></span></h5>}
						{product && <p className="product-description mt-3">{product.description}</p>}
						{product && <h4 className="price">current price: <span>{product.price}</span></h4>}
						<button className="btn btn-primary mt-3" type="button" onClick={() =>addToCart()}>add to cart</button>
					</div>
				</div>
			</div>
		</div>


		<form onSubmit={rating}>
			<div class="form-group mt-3">
				<div class="col">
				<textarea 
				class="form-control" 
				value={commentForm}
				placeholder="Please enter your feedback here..." 
				onChange={(e) =>setCommentForm(e.target.value)}
				rows="5"></textarea>
				</div>
				
					<h6 className='col-2'>Your rating: {stars} <span class="fa fa-star checked"></span></h6>
					<div class="btn-group " role="group" aria-label="Basic example">
						<button type="button" class="btn btn-link" onClick={()=>setStars(1)}><span class="fa fa-star checked"></span></button>
						<button type="button" class="btn btn-link" onClick={()=>setStars(2)}><span class="fa fa-star checked"></span></button>
						<button type="button" class="btn btn-link" onClick={()=>setStars(3)}><span class="fa fa-star checked"></span></button>
						<button type="button" class="btn btn-link" onClick={()=>setStars(4)}><span class="fa fa-star checked"></span></button>
						<button type="button" class="btn btn-link" onClick={()=>setStars(5)}><span class="fa fa-star checked"></span></button>
					</div>
			</div>
		<button type="submit" class="btn btn-primary mt-2">Submit</button>
		</form>



		<h3 className="mt-3">Comments</h3>
		<ul className="pagination">
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