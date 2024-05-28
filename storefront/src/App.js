import './App.css';
import Navbar from './component/navbar';
import Login from './pages/login/login';
import ProductDetail from './pages/product detail/productDetail';
import Storefront from './pages/storefront/storefront';
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
import { useState, useEffect } from "react"
import { getToken } from './services/authService';


function App() {
  let [token, setToken] = useState();

  useEffect(()=>{
    setToken(getToken());
}, [])

  if(!token) {
    return <Login setToken = {setToken}/>
  }

  return (
    <Router>
        <div className="App">
          <Navbar/>
            <div className="content"> 
            <Routes>
              <Route path="/products/:id" element={<ProductDetail></ProductDetail>}></Route>
            </Routes>
            <Routes>
              <Route path="/" element={<><Storefront /></>}>
              </Route>
            </Routes>
            </div>
        </div>
    </Router>
  );
}

export default App;
