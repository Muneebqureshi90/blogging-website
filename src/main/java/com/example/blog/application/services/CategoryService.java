package com.example.blog.application.services;

import com.example.blog.application.payload.CategoryDto;
import com.example.blog.application.payload.UserDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);
    CategoryDto  updateCategory(CategoryDto category,Integer categoryId);
    CategoryDto getCategoryById( Integer categoryId);
    List<CategoryDto> getAllCategory();
    void deleteCategory(Integer categoryId );
}
