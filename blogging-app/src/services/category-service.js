
//In this we have all the server thing which is related to User

import {myAxios} from "./helper";

export const loadAllCategories=()=>{

    //=>responce.data() we using this to use this in signup file

    return myAxios.get('/categories/').then((responce)=>responce.data)
}
