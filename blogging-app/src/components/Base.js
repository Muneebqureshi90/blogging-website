
// Its is Working like parent as i did in Thymeleeaf


import CustomNavBar from "./CustomNavBar";
import Footer from "./Footer";

const Base=({title="Welcome TO Our Website",children})=>{

    return(
        <div className="container-fluid g-0">
            <CustomNavBar/>

            {children}

            <Footer/>
        </div>
    )

}

export default Base;
