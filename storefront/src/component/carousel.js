const Carousel = ({products}) => {
    return(
    <div className="container-fluid">
      <div id="carouselExampleIndicators" className="carousel slide">
        <div className="carousel-inner">
          {products && products.map((product)=>
          <div className="carousel-item active" key={product.id}>
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaBqXPJxDAvLfz-d0uNwJtxUSGKexAZfWzkknNlUdU0A&s" className="d-block w-100" />
          </div>)}
        </div>
        <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Previous</span>
        </button>
        <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Next</span>
        </button>
      </div>
      </div>
    )
}

export default Carousel;