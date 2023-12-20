
import axios from "axios";
import {getToken} from "../auth";


export const BASE_URL='http://localhost:8080/api/v1';

export const myAxios=axios.create({
    baseURL:BASE_URL
});

//The URL which are private


// Now we Use privateAxios where we went to get token acess
export const privateAxios=axios.create({
    baseURL:BASE_URL,
    // withCredentials: true,
})

privateAxios.interceptors.request.use(config => {
    const token = getToken();

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    } else {
        console.warn("No authentication token available.");
    }

    return config;
}, error => Promise.reject(error));

