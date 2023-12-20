
//In this we have all the server thing which is related to User

import {myAxios, privateAxios} from "./helper";
import {post} from "axios";


// creating a Post
export const creatPost=(postData)=>{

    console.log(postData);

    //=>responce.data() we using this to use this in signup file

    return privateAxios.post(`/user/${postData.userId}/category/${postData.categoryId}/posts`,postData)
        .then((responce)=>responce.data)
}


//Getting All Posts

export const loadAllPosts=(pageNumber,pageSize)=>{

    return myAxios.get(`/posts?&pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=addedDate&sortDir=desc`)
        .then((responce)=>responce.data)

}

//Loading Single Post by Id

export const loadPost=(postId)=>{

    return myAxios.get(`/posts/`+postId)
        .then((responce)=>responce.data)

}

//Creating Comments

export const createComment=(comment,postId)=>{

    return myAxios.post(`/post/${postId}/comments`,comment)
        .then((responce)=>responce.data)

}

//Uploaind the Post image

export const uploadPostImage=(image,postId)=>{

    let formData=new FormData()
    FormData.append("image",image)

    return myAxios.post(`/post/image/upload/${postId}`,formData,{
        headers:{
            'Content-Type':'multipart/form-data'
        }
    })
        .then((responce)=>responce.data)

}

//get Category through Post

export const loadPostCategoryWise=(categoryId)=>{


    return privateAxios.get(`/category/${categoryId}/posts`)
        .then((responce)=>responce.data)

}

//get categories of user what he uploads

export const loadPostUserWise=(userId)=>{


    return privateAxios.get(`/user/${userId}/posts`)
        .then((responce)=>responce.data)

}

//delelte the post which is uploaed by user

export const deletePost=(postId)=>{


    return privateAxios.delete(`/posts/${postId}`)
        .then((responce)=>responce.data)

}

