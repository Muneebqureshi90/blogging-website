
//In this we have all the server thing which is related to User

import {myAxios} from "./helper";

export const signUp=(user)=>{

    //=>responce.data() we using this to use this in signup file

    return myAxios.post('/auth/register',user).then((responce)=>responce.data)
}

export const loginUser=(loginDetails)=>{

    //=>responce.data() we using this to use this in signup file

    return myAxios.post('/auth/login',loginDetails).then((responce)=>responce.data)
}

//Getting user

export const getUser=(userId)=>{

    //=>responce.data() we using this to use this in signup file

    return myAxios.get(`/users/${userId}`,userId).then((responce)=>responce.data)
}