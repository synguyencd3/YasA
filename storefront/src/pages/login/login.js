import { useState} from "react"

async function loginUser(credentials) {
    return fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
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
        });
        console.log(token);
        setToken(token.accessKey);
      }

    return(
        <div className="login-wrapper">
            <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label for="exampleInputEmail1">Username</label>
                <input onChange={e => setUserName(e.target.value)} className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter username"/>
            </div>
            <div className="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" onChange={e => setPassword(e.target.value)} className="form-control" id="exampleInputPassword1" placeholder="Password"/>
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    )
}

export default Login;