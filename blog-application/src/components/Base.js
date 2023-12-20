
// Its is Working like parent as i did in Thymeleeaf


import CustomNavBar from "./CustomNavBar";

const Base=({title="Welcome TO Our Website",children})=>{

    return(
        <div className="container-fluid p-0 m-0">
            <CustomNavBar/>


            {children}


            <h1>This is footer</h1>
        </div>
    )

}

export default Base;
