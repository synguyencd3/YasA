import './App.css';
import Header from './components/header/header';
import Sidemenu from './components/sideMenu/sideMenu';
import ProductCard from './components/pageContent/Product/productCard';
import List from './components/pageContent/List';
import { useState } from 'react';

function App() {

  let card = ProductCard
  let url =""

  return (
    <div className="App">
      <Header/>
      <div className="container-fluid mt-5">
        <div className="row">
          <div className="col-3">
              <Sidemenu/>
            </div>
            <div className="col-9 gx-5">
              <List Card={card} url={url}/>
              </div>
        </div>
      </div>
    </div>
  );
}

export default App;
