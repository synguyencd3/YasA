import { useState} from "react"
import { registerUrl } from "../static/const";
import { redirect } from "react-router-dom";

async function registerUser(credentials) {
    return fetch(registerUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(credentials)
    })
   }

const Regsiter = () => {
    const [username, setUserName] = useState();
    const [name, setName] = useState();
    const [password, setPassword] = useState();
    const [confirmPassword, setConfirmPassword] = useState();
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async e => {
        e.preventDefault();
        console.log("submitting./....")
        if (password !== confirmPassword) {
          setErrorMessage("Passwords don't match");
            alert(errorMessage)
        } else {
            await registerUser({
                name,
                username,
                password
              })
            redirect("/")
        }
      }

    return(
        <div className="container-sm mt-5 " style={{width: 500}}>
            <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label for="name">Name</label>
                <input onChange={e => setName(e.target.value)} className="form-control" id="Name" aria-describedby="emailHelp" placeholder="Enter name"/>
            </div>
            <div className="form-group">
                <label for="username">Username</label>
                <input onChange={e => setUserName(e.target.value)} className="form-control" id="Username" aria-describedby="emailHelp" placeholder="Enter username"/>
            </div>
            <div className="form-group">
                <label for="Password">Password</label>
                <input type="password" onChange={e => setPassword(e.target.value)} className="form-control" id="Password" placeholder="Password"/>
            </div>
            <div className="form-group">
                <label for="confirmPassword">Confirm assword</label>
                <input type="password" onChange={e => setConfirmPassword(e.target.value)} className="form-control" id="ConfirmPassword" placeholder="Confirm Password"/>
            </div>
            <button type="submit" className="btn btn-primary mt-3">Submit</button>
            </form>
        </div>
    )
}

export default Regsiter;