import React from "react";
import {Navigate, Outlet} from "react-router-dom";
import {isLoggedIn} from "../auth";


{/*    Now using Outlwt to get the Data of DataBoard and other pages*/}
const PrivateRoute = () => {
    if (isLoggedIn()) {
        {/*    Now using Outlwt to get the Data of DataBoard and other pages*/}

        return <Outlet />;
    } else {
        return <Navigate to="/login" />;
    }

      // let loggin=false;
      // if (loggin){
      //       return <Outlet/>
      // }
      // else {
      //       return "";
      // }

      // return isLoggedIn() ? <Outlet/> : <Navigate to={"/login"} />

};
export default PrivateRoute