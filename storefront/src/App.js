import './App.css';
import Navbar from './component/navbar';
import Login from './pages/login/login';
import ProductDetail from './pages/product detail/productDetail';
import Storefront from './pages/storefront/storefront';
import { BrowserRouter as Router, Routes, Route, redirect} from "react-router-dom";
import { useState, useEffect } from "react"
import { getToken } from './services/authService';
import AuthPage from './pages/login/authPage';



function App() {
  let [token, setToken] = useState(null);

  useEffect(()=>{
    setToken(getToken());
}, [token])

  if(!token) {
    //return <Login setToken = {setToken}/>
    return <AuthPage setToken = {setToken}/>
  }

  return (
    <Router>
        <div className="App">
          <Navbar setToken={setToken}/>
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
