package com.example.blog.application.serviceImpl;

import com.example.blog.application.entity.Category;
import com.example.blog.application.entity.Post;
import com.example.blog.application.entity.User;
import com.example.blog.application.exceptions.ResourceNotFoundException;
import com.example.blog.application.payload.PostDto;
import com.example.blog.application.payload.PostResponce;
import com.example.blog.application.repository.CategoryRepository;
import com.example.blog.application.repository.PostRepository;
import com.example.blog.application.repository.UserRepository;
import com.example.blog.application.services.PostService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        // Retrieve the user by ID, or throw a ResourceNotFoundException if not found
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId.toString()));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png"); // Corrected the typo in "default"
        post.setAddedDate(new Date());

        // You can also set the user for this post, assuming you have a relationship between Post and User entities
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepository.save(post);
        // Perform other necessary operations and return the created post
        return this.modelMapper.map(newPost,PostDto.class);
    }


    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postId.toString()));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(post.getImageName());

        Post updated = this.postRepository.save(post);
        return  this.modelMapper.map(updated,PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postId.toString()));

        return this.modelMapper.map(post,PostDto.class);


    }

    @Override
    public PostResponce getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort=null;
        if (sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }
        else {
            sort=Sort.by(sortBy).descending();
        }


        Pageable p= PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost= this.postRepository.findAll(p);
        List<Post> allPost = pagePost.getContent();

        List<PostDto> postDtos=   allPost.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponce postResponce= new PostResponce();
        postResponce.setContent(postDtos);
        postResponce.setPageNumber(pagePost.getNumber());
        postResponce.setPageSize(pagePost.getSize());
        postResponce.setTotalElements(pagePost.getTotalElements());
        postResponce.setTotalPages(pagePost.getTotalPages());
        postResponce.setLastPage(pagePost.isLast());

        return postResponce;

    }

//    @Override
//    public void deletePost(Integer postId) {
//        Post post = this.postRepository.findById(postId)
//                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postId.toString()));
//
//        this.postRepository.delete(post);
//
//    }

//    @Transactional
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        this.postRepository.delete(post);
    }



    @Transactional
    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId.toString()));

        List<Post> posts = this.postRepository.findByCategory(cat);

        List<PostDto> postDtos = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }


//

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user= this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));;

        List<Post> posts=this.postRepository.findByUser(user);
        List<PostDto> postDtos=   posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepository.searchByTitle("%"+keyword+"%");
        List<PostDto> postDtos = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }
    @Override
    public PostDto loadPost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));

        // Eagerly fetch the Category association
        post.getCategory().getCategoryDescription(); // You can access any attribute to trigger the load

        // Map the post entity to a DTO
        PostDto postDto = modelMapper.map(post, PostDto.class);

        return postDto;
    }

}
