import { useEffect, useState } from 'react';
import { categoryUrl } from '../../static/const';

const NewProductForm = ({content}) => {
  const [productName, setProductName] = useState(content==null? '' : content.name);
  const [category, setCategory] = useState(content==null ? '' : content.name);
  const [image, setImage] = useState(null);
  const [description, setDescription] = useState(content == null ? '': content.description);
  const [price, setPrice] = useState(content==null?'':content.price);

  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };
  const [categories, setCategories] = useState(null);

  const fetchItem = () => {

    fetch(categoryUrl+`?size=99`).then(res => {
      return res.json()
  }).then((data) => {
      console.log(data)
      setCategories(data)
  })
  }

  useEffect(() => {
    fetchItem()
  }, [])


  const handleSubmit = (e) => {
    console.log("submit")
    e.preventDefault();
    // Handle form submission, e.g., send data to an API or display it
    const formData = new FormData();
    formData.append('productName', productName);
    formData.append('category', category);
    formData.append('image', image);
    formData.append('description', description);
    formData.append('price', price);

    // Log form data to console (for demonstration purposes)
    for (let [key, value] of formData.entries()) {
      console.log(`${key}: ${value}`);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="container">
      <div className="form-group">
        <label htmlFor="productName">Product Name:</label>
        <input
          type="text"
          className="form-control"
          id="productName"
          value={productName}
          onChange={(e) => setProductName(e.target.value)}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="category">Category:</label>
        <select
          className="form-control"
          id="category"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          required
        >
          {categories && categories.map((category) => <option key={category.id} value={category.id}>{category.name}</option>)}
        </select>
      </div>
      <div className="form-group">
        <label htmlFor="image">Image : </label>
        <input
          type="file"
          className="form-control-file"
          id="image"
          accept="image/*"
          onChange={handleImageChange}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="description">Description:</label>
        <textarea
          className="form-control"
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        ></textarea>
      </div>
      <div className="form-group">
        <label htmlFor="price">Price:</label>
        <input
          type="number"
          className="form-control"
          id="price"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          required
        />
      </div>
      <button type="submit" className="btn btn-primary mt-3">Create Product</button>
    </form>
  );
};

export default NewProductForm;
