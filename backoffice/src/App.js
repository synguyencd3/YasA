import './App.css';
import Header from './components/header/header';
import Sidemenu from './components/sideMenu/sideMenu';
import ProductCard from './components/pageContent/Product/productCard';
import List from './components/pageContent/List';
import { useState, useEffect } from 'react';
import Login from './login/login';
import { getToken } from './services/authService';
import CategoryCard from './components/pageContent/Category/CategoryCard';
import { categoryUrl, productUrl } from './static/const';
import NewProductForm from './components/form/productForm';
import NewCategoryForm from './components/form/categoryForm';

function App() {

  let object = {
    card : ProductCard,
    url: productUrl,
    form: NewProductForm
  }

  let object2 = {
    card : CategoryCard,
    url: categoryUrl,
    form: NewCategoryForm
  }

  

  let [token, setToken] = useState();

  useEffect(()=>{
    setToken(getToken());
}, [])

  if(!token) {
    return <Login setToken = {setToken}/>
  }

  return (
    <div className="App">
      <Header/>
      <div className="container-fluid mt-5">
        <div className="row">
          <div className="col-3">
              <Sidemenu/>
            </div>
            <div className="col-9 gx-5">
              <List Card={object2.card} url={object2.url} Form={object2.form}/>
              </div>
        </div>
      </div>
    </div>
  );
}

export default App;
