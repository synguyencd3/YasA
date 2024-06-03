import { useEffect, useState } from 'react';
import { categoryUrl, productUrl } from '../../static/const';
import { getStorage, ref, uploadBytes, getDownloadURL  } from "firebase/storage";
import { getToken } from '../../services/authService';

const NewProductForm = ({content,fetchFunc, toggleFunc}) => {
  const [productName, setProductName] = useState(content==null? '' : content.name);
  const [category, setCategory] = useState(content==null ? '' : content.category);
  const [image, setImage] = useState(null);
  const [description, setDescription] = useState(content == null ? '': content.description);
  const [price, setPrice] = useState(content==null?'':content.price);
  const [isFeatured, setIsFeatured] = useState(content==null ? false: content.featured)
  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };
  const [categories, setCategories] = useState(null);

  const fetchItem = () => {
    fetch(categoryUrl+`?size=99`).then(res => {
      return res.json()
  }).then((data) => {
      console.log(data)
      setCategories(data.content)
  })
  }

  useEffect(() => {
    fetchItem()
  }, [])

  const storage = getStorage();

  const postProduct = async () => {
    const imageRef = ref(storage, `Products/${image.name}`)
    await uploadBytes(imageRef, image)
    const url = await getDownloadURL(ref(imageRef))
    console.log("url "+url)
    const data = 
    {
      name: productName,
      price: price,
      description: description,
      image: url,
      category: category,
      featured: isFeatured,
    }
    console.log("fetching")
    console.log(data)
    await fetch(productUrl,
      {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${getToken()}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
      }
    )

    console.log("created")
  }

  const putProduct = async () => {
    let url=null
    if (image!=null) {
      const imageRef = ref(storage, `Products/${image.name}`)
      await uploadBytes(imageRef, image)
      url = await getDownloadURL(ref(imageRef))
      console.log("url "+url)
    }

    const data = 
    {
      name: productName,
      price: price,
      description: description,
      image: url,
      category: category,
      featured: isFeatured
    }
    console.log("fetching")
    console.log(data)
    await fetch(productUrl+`/${content.id}`,
      {
        method: 'PUT',
        headers: {
          Authorization: `Bearer ${getToken()}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
      }
    )
  }

  const handleSubmit = async (e) => {
    console.log("submit")
    e.preventDefault()
    content == null ? await postProduct(): await putProduct()
    fetchFunc()
    toggleFunc()
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
      <div className="form-group form-check">
        <input
          type="checkbox"
          className="form-check-input"
          id="isFeatured"
          checked={isFeatured}
          onChange={(e) => setIsFeatured(e.target.checked)}
        />
        <label className="form-check-label" htmlFor="isFeatured">Is Featured</label>
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
      {content == null ? 
      <button type="submit" className="btn btn-primary mt-3">Create Product</button>:
      <button type="submit" className="btn btn-primary mt-3">Edit Product</button>}
    </form>
  );
};

export default NewProductForm;
