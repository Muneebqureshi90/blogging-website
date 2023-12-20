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

function App() {
  return (
      <BrowserRouter>
        <Routes>

          <Route path="/" element={<Home/>}></Route>
          <Route path="/login" element={<Login/>}></Route>
          <Route path="/logout" element={<Logout/>}></Route>
          <Route path="/signup" element={<SignUp/>}></Route>
          <Route path="/about" element={<About/>}></Route>


        </Routes>

      </BrowserRouter>)
}

export default App;
