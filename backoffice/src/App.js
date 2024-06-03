import './App.css';
import Header from './components/header/header';
import Sidemenu from './components/sideMenu/sideMenu';
import List from './components/pageContent/List';
import { useState, useEffect } from 'react';
import Login from './login/login';
import { getToken } from './services/authService';
import ProductGroup from './components/sideMenu/group objects/ProductGroup';



function App() {

  let [group, setGroup] = useState(ProductGroup)
  
  let [token, setToken] = useState();

  useEffect(()=>{
    setToken(getToken());
}, [token])

  if(!token) {
    return <Login setToken = {setToken}/>
  }

  return (
    <div className="App">
      <Header setToken={setToken}/>
      <div className="container-fluid mt-5">
        <div className="row">
          <div className="col-3">
              <Sidemenu setGroupFunc={setGroup}/>
            </div>
            <div className="col-9 gx-5">
              <List Card={group.card} url={group.url} Form={group.form}/>
              </div>
        </div>
      </div>
    </div>
  );
}

export default App;
