import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { categoryUrl } from '../../static/const';
import { getToken } from '../../services/authService';

const NewCategoryForm = ({fetchFunc, toggleFunc, content}) => {
  const [name, setName] = useState(content== null?'' : content.name);
  const [description, setDescription] = useState(content==null? '' : content.name);

  const handleSubmit = (e) => {
    if (window.confirm("Are you sure to continue operation ?")) {
      e.preventDefault();
      const categoryData = { name, description };
      console.log('Category created:', categoryData);
      content== null? PostItem(categoryData) : PutItem(categoryData)
      fetchFunc()
      toggleFunc()
    } else {
      console.log("cancelled");
    }
  };

  const PostItem = (data) => {
    fetch(categoryUrl,
      {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${getToken()}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
      }
    ).then(res => {
      console.log("created")
      fetchFunc()
    })
  }

  const PutItem = (data) => {

    fetch(categoryUrl+`/${content.id}`,
      {
        method: 'PUT',
        headers: {
          Authorization: `Bearer ${getToken()}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
      }
    ).then(res => {
      console.log("created")
      fetchFunc()
    })
  }

  return (
    <form onSubmit={handleSubmit} className="container">
      <div className="form-group">
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          className="form-control"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
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
      {content==null? 
      <button type="submit" className="btn btn-primary mt-3">Create Category</button> : 
      <button type="submit" className="btn btn-primary mt-3">Edit Category</button>}

    </form>
  );
};

export default NewCategoryForm;
