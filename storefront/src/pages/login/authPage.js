import { Route,BrowserRouter as Router, Routes } from "react-router-dom";
import Login from "./login";
import Regsiter from "./register";

const AuthPage = ({setToken}) => {
    return (
        <Router>
            <Routes>
              <Route path="/" element={<Login setToken={setToken}/>}></Route>
            </Routes>
            <Routes>
              <Route path="/register" element={<Regsiter/>}>
              </Route>
            </Routes>
    </Router>
    )
}

export default AuthPage;