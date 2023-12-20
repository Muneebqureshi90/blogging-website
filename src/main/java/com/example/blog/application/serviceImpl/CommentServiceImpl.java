package com.example.blog.application.serviceImpl;

import ch.qos.logback.core.model.Model;
import com.example.blog.application.entity.Category;
import com.example.blog.application.entity.Comment;
import com.example.blog.application.entity.Post;
import com.example.blog.application.exceptions.ResourceNotFoundException;
import com.example.blog.application.payload.CommentDto;
import com.example.blog.application.payload.PostDto;
import com.example.blog.application.repository.CommentRepository;
import com.example.blog.application.repository.PostRepository;
import com.example.blog.application.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postId.toString()));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment save = this.commentRepository.save(comment);
        // Perform other necessary operations and return the created post
        return this.modelMapper.map(save, CommentDto.class);


    }

    /*
@Override
public CommentDto createComment(CommentDto commentDto, Integer postId) {
    Post post = this.postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));

    Comment comment = this.modelMapper.map(commentDto, Comment.class);
    comment.setPost(post);

    // Save the comment to the repository
    Comment savedComment = this.commentRepository.save(comment);

    // Perform other necessary operations and return the created comment
    return this.modelMapper.map(savedComment, CommentDto.class);
}

*/
    @Override
    public void deletePost(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));

        this.commentRepository.delete(comment);
    }
}
