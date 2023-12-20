package com.example.blog.application.controller;

import com.example.blog.application.configuration.AppConstants;
import com.example.blog.application.entity.Category;
import com.example.blog.application.entity.Post;
import com.example.blog.application.payload.ApiResponse;
import com.example.blog.application.payload.PostDto;
import com.example.blog.application.payload.PostResponce;
import com.example.blog.application.repository.CategoryRepository;
import com.example.blog.application.services.FileService;
import com.example.blog.application.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;


//Swagger
@SecurityRequirement(name = "scheme1")

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Post Controller", description = "This is Post Controller")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId, @PathVariable Integer categoryId
    ) {

        PostDto createdPost = postService.createPost(postDto, userId, categoryId);

        // Map the created post back to a PostDto


        // Return a ResponseEntity with the created PostDto and HTTP status code 201 (Created)
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

    }





    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {

        List<PostDto> postByUser = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        System.out.println("Received categoryId: " + categoryId);
        Category cat = this.categoryRepository.findById(categoryId).orElse(null); // Change to `orElse(null)` for debugging
        if (cat != null) {
            List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
            System.out.println("Posts for category: " + postByCategory.size());
            return new ResponseEntity<>(postByCategory, HttpStatus.OK);
        } else {
            System.out.println("Category not found for ID: " + categoryId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/posts")
    public ResponseEntity<PostResponce> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ) {

        PostResponce allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponce>(allPost, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getAllPostById(@PathVariable Integer postId) {

        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);

        String status = "OK"; // Set the appropriate status
        String time = "current_timestamp"; // Set the appropriate timestamp

        ApiResponse apiResponse = new ApiResponse("Post is Deleted", true, status, time);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
//        postService.deletePost(postId);
//        ApiResponse apiResponse = new ApiResponse("Post is Deleted", true, "OK", "current_timestamp");
//        return ResponseEntity.ok(apiResponse);
//    }


    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        // Call the service method to update the post and get the updated PostDto
        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);

        // Return the updated PostDto in the response with HTTP status OK
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keywords) {
        List<PostDto> searchResults = postService.searchPosts(keywords);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

//    For Image

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image") MultipartFile file,
            @PathVariable Integer postId
    ) throws IOException {
        PostDto postById = this.postService.getPostById(postId);
        String originalFileName = this.fileService.uploadImage(path, file);
        postById.setImageName(originalFileName);

        PostDto updatePost = this.postService.updatePost(postById, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}