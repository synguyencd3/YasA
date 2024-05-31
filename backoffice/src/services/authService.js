import Login from "../login/login";

export function getToken() {
    const tokenString = sessionStorage.getItem('token');
    const userToken = JSON.parse(tokenString);
    return userToken
}

export function setTokenToStorage(userToken) {
    sessionStorage.setItem('token', JSON.stringify(userToken));
    console.log("sent to storage"+userToken);
  }

export function logout() {
    console.log("logging out")
    sessionStorage.removeItem('token');
}