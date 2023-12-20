
//Is User is is loggin or Not



export const isLoggedIn=()=>{
    let data=localStorage.getItem("data");
    if (data==null){
        return false;
    }
    else {
        return true;
    }
};


//doLogin => storage the token in local memery
// If user want to do login or else thing to do so we use "next"
export const doLogin=(data,next)=>{

    localStorage.setItem("data",JSON.stringify(data));
    next();
};

// logout => token remove from local storage

export const doLogout=(next)=>{

    localStorage.removeItem("data");
    next();
};

//get current User

export const getCurrentUserDetails = () => {
    const userDataString = localStorage.getItem("data");
    if (userDataString) {
        return JSON.parse(userDataString).user;
    } else {
        return {};
    }
};



//For Getting Token to access the private URLs

export const getToken = () => {
    const userDataString = localStorage.getItem("data");
    if (userDataString) {
        return JSON.parse(userDataString).token;
    } else {
        return null;
    }
};