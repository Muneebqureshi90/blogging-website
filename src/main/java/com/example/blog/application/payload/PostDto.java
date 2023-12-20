package com.example.blog.application.payload;

import com.example.blog.application.entity.Category;
import com.example.blog.application.entity.Comment;
import com.example.blog.application.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String content;
    private String title;

//    Not Using this right Now
    private String imageName;
    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

//    Showing Comments in Post
    private Set<CommentDto> comments = new HashSet<>();


}
