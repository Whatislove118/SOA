import './App.css';
import {React} from "react";
import Switch from "react-router-dom/es/Switch";
import Route from "react-router-dom/es/Route";
import Main from "./pages/Main/Main";
import NavigationBar from "./components/NavBar/NavBar";
import 'bootstrap/dist/css/bootstrap.min.css';
import Coordinates from "./pages/Coordinates/Coordinates";
import CityCreate from "./pages/CityCreate/CityCreate";
import CityGet from "./pages/CityGet/CityGet";

export const backUrl = 'http://localhost:8080/city'

function App() {
  return (
    <div className="App">
      <NavigationBar/>
      <Switch>
        <Route exact path={'/'} component={Main}/>
        <Route exact path={"/city/create"} component={CityCreate}/>
        <Route exact path={"/city/get"} component={CityGet}/>
      </Switch>
    </div>
  );
}

export default App;
