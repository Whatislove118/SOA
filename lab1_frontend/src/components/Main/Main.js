import {Route, Router, Switch} from "react-router-dom";


import City from "../../pages/City/City";
import React from "react";


const Main = () => {
  return <button onClick={routing("/city")}>City</button>
};



const routing = (props) => {
    const {pageName} = props;
    console.log(pageName);
    return <Router>
    <Switch>
        <Route path={"/city"} component={City(1)}/>
    </Switch>
    </Router>

};
export default Main;
