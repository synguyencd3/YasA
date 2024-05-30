import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { categoryUrl } from '../../static/const';
import { getToken } from '../../services/authService';

const NewCategoryForm = ({fetchFunc, toggleFunc}) => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const categoryData = { name, description };
    console.log('Category created:', categoryData);
    // Handle form submission, e.g., send data to an API or display it
    PostItem(categoryData)
    toggleFunc()
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

  return (
    <form onSubmit={handleSubmit} className="container mt-5">
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
      <button type="submit" className="btn btn-primary">Create Category</button>
    </form>
  );
};

export default NewCategoryForm;
