import { Space} from 'antd';
import './App.css';
import Header from './components/header/header';
import Sidemenu from './components/sideMenu/sideMenu';
import Content from './components/pageContent/content';

function App() {
  return (
    <div className="App">
      <Header/>
      <div className="container-fluid mt-5">
        <div className="row">
          <div className="col-3">
              <Sidemenu/>
            </div>
            <div className="col-9 gx-5">
              <Content/>
              </div>
        </div>
      </div>
    </div>
  );
}

export default App;
