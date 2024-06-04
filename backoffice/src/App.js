import './App.css';
import Header from './components/header/header';
import Sidemenu from './components/sideMenu/sideMenu';
import List from './components/pageContent/List';
import { useState, useEffect } from 'react';
import Login from './login/login';
import { getToken } from './services/authService';
import ProductGroup from './components/sideMenu/group objects/ProductGroup';
import AuthPage from './login/authPage';
import SortDropdown from './components/sideMenu/sort';



function App() {

  let [group, setGroup] = useState(ProductGroup)
  
  let [token, setToken] = useState();
  let [sort, setSort] = useState("asc");
  let [sortBy, setSortBy] = useState("id");

  useEffect(()=>{
    setToken(getToken());
}, [token])

  if(!token) {
    return <AuthPage setToken = {setToken}/>
  }

  return (
    <div className="App">
      <Header setToken={setToken}/>
      <div className="container-fluid mt-5">
        <div className="row">
          <div className="col-3">
              <Sidemenu setGroupFunc={setGroup}/>
              <SortDropdown setSort={setSort} setSortBy={setSortBy}/>
            </div>
            <div className="col-9 gx-5">
              <List Card={group.card} url={group.url} Form={group.form} sort={sort} sortBy={sortBy}/>
              </div>
        </div>
      </div>
    </div>
  );
}

export default App;
