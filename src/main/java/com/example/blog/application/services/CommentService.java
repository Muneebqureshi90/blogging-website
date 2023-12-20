package com.example.blog.application.services;

import com.example.blog.application.payload.CommentDto;
import com.example.blog.application.payload.PostDto;
import com.example.blog.application.payload.PostResponce;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deletePost(Integer commentId);

}
