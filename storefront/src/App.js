import './App.css';
import Navbar from './component/navbar';
import Storefront from './component/storefront';

function App() {
  return (
    <div className="App">
      <div className="content"> 
        <Navbar/>
        <Storefront/>
      </div>
    </div>
  );
}

export default App;
