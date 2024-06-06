import { useState} from "react"
import { setTokenToStorage } from "../services/authService";
import { loginUrl } from "../static/const";


async function loginUser(credentials) {
    return fetch(loginUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
    .then(response => {
        if (response.ok) return response.json()
        return Promise.reject(response)
    })
   }

const Login = ({setToken}) => {
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();

    const handleSubmit = async e => {
        console.log(username);
        console.log(password);
        e.preventDefault();
        const token = await loginUser({
          username,
          password
        }).then((token) =>{
            console.log(token);
            setToken(token.accessKey);
            setTokenToStorage(token.accessKey);
        }).catch((response) => {
           response.json().then((json) => {
             alert(json.message);
           })
        })
      }

    return(
        <div className="container-sm mt-5 " style={{width: 500}}>
            <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label for="exampleInputEmail1">Username</label>
                <input onChange={e => setUserName(e.target.value)} className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter username"/>
            </div>
            <div className="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" onChange={e => setPassword(e.target.value)} className="form-control" id="exampleInputPassword1" placeholder="Password"/>
            </div>
            <button type="submit" className="btn btn-primary mt-3">Submit</button>
            </form>
            <a href={`/register`}>Register?</a>
        </div>
    )
}

export default Login;