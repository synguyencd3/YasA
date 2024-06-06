import ProductCard from "./productCard";
import 'bootstrap/dist/css/bootstrap.css'; 
import {Carousel, Card, Button} from "react-bootstrap"; 



const FeaturedCarousel = ({products}) => {

    const reduceProducts = (acc, cur, index) => {
        const groupIndex = Math.floor(index / 3);
        if (!acc[groupIndex]) acc[groupIndex] = [];
        acc[groupIndex].push(cur);
        console.log(acc);
        return acc;
      };

    return(
    <Carousel>
      {products && products.reduce(reduceProducts, []).map((item, index) => (
        <Carousel.Item key={index}>
          <div className="d-flex justify-content-center" key={index}>
            {item.map((item, index) => {
              return (
                <ProductCard product={item}/>
              );
            })}
          </div>
        </Carousel.Item>
      ))}
    </Carousel>
    
    )
}

export default FeaturedCarousel;