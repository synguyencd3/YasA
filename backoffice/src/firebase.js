// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyB_lqgEBwOMk8uFoZcJMHg5lPY13Y35K-o",
  authDomain: "yasa-acea6.firebaseapp.com",
  projectId: "yasa-acea6",
  storageBucket: "yasa-acea6.appspot.com",
  messagingSenderId: "830024605813",
  appId: "1:830024605813:web:6efb9f36d692c3604bd83c",
  measurementId: "G-M3M6KJVJTR"
};

// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const storage = getStorage(app)