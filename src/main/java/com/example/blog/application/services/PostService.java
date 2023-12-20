package com.example.blog.application.services;

import com.example.blog.application.entity.Post;
import com.example.blog.application.payload.PostDto;
import com.example.blog.application.payload.PostResponce;
import com.example.blog.application.payload.UserDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById( Integer postId);
    PostResponce getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    void deletePost(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);


    PostDto loadPost(Integer postId);
}