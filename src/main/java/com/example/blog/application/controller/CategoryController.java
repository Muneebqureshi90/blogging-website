package com.example.blog.application.controller;

import com.example.blog.application.payload.ApiResponse;
import com.example.blog.application.payload.CategoryDto;
import com.example.blog.application.payload.UserDto;
import com.example.blog.application.services.CategoryService;
import com.example.blog.application.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")

//Swagger
@SecurityRequirement(name = "scheme1")
@Tag(name = "Category Controller", description = "This is Category Controller")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
//    @PostMapping("/")
//    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
//        CategoryDto created= this.categoryService.createCategory(categoryDto);
//        return new ResponseEntity<>(created, HttpStatus.CREATED);
//    }
@PostMapping("/")
public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
    CategoryDto created = this.categoryService.createCategory(categoryDto);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
}


    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer cid) {
       CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, cid);
        if (updateCategory == null) {
            throw new RuntimeException("NO Category  Found: " + cid);
        }
        return ResponseEntity.ok(updateCategory);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);

        // Set appropriate values for the status and time fields
        String status = "OK"; // Set the appropriate status
        String time = "current_timestamp"; // Set the appropriate timestamp

        ApiResponse apiResponse = new ApiResponse("Category Deleted", true, status, time);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getAllCategory(){

        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> singleCategory(@PathVariable("categoryId") Integer categoryId) {

        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }
}
