import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from "reactstrap";
import Base from "./components/Base";

// Importing for router
import {BrowserRouter, Router, Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Logout from "./pages/Logout";
import SignUp from "./pages/SignUp";
import About from "./pages/About";
import Service from "./pages/Service";

import { ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import UserDashBoard from "./pages/UserRoutes/UserDashBoard";
import PrivateRoute from "./components/PrivateRoute";
import ProfileInfo from "./pages/UserRoutes/ProfileInfo";
import PostPage from "./pages/UserRoutes/PostPage";
import UserProvider from "./context/UserProvider";
import Categories from "./pages/Categories";

function App() {
  return (

      // COntextAPi
      <UserProvider>

      <BrowserRouter>
          <ToastContainer position={"bottom-center"}/>
        <Routes>

          {/*  Public Pages*/}
          <Route path="/" element={<Home/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/logout" element={<Logout/>}/>
          <Route path="/signup" element={<SignUp/>}/>
          <Route path="/service" element={<Service/>}/>
          <Route path="/post/:postId" element={<PostPage/>}/>
          <Route path="/about" element={<About/>}/>
            <Route path="/categories/:cid" element={<Categories />} />

        {/*    Private Pages User Page*/}
            <Route path="/user/*" element={<PrivateRoute />}>
                <Route path="dashboard" element={<UserDashBoard />} />
                <Route path="profile-info/:userId" element={<ProfileInfo />} />
            </Route>

        </Routes>

      </BrowserRouter>

      </UserProvider>
          )
}

export default App;
