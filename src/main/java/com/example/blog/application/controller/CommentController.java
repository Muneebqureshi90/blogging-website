package com.example.blog.application.controller;

import com.example.blog.application.payload.ApiResponse;
import com.example.blog.application.payload.CommentDto;
import com.example.blog.application.services.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Swagger
@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Comment Controller", description = "This is Comment Controller")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable Integer postId){

        CommentDto comment = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }
    @DeleteMapping ("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(
                                                    @PathVariable Integer commentId){

        this.commentService.deletePost(commentId);

        String status = "OK"; // Set the appropriate status
        String time = "current_timestamp"; // Set the appropriate timestamp

        ApiResponse apiResponse = new ApiResponse("Post is Deleted", true, status, time);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
