package com.example.blog.application.serviceImpl;

import com.example.blog.application.entity.Category;
import com.example.blog.application.entity.User;
import com.example.blog.application.exceptions.ResourceNotFoundException;
import com.example.blog.application.payload.CategoryDto;
import com.example.blog.application.payload.UserDto;
import com.example.blog.application.repository.CategoryRepository;
import com.example.blog.application.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto category) {

        Category cat = this.modelMapper.map(category, Category.class);
        Category save = this.categoryRepository.save(cat);

        return this.modelMapper.map(save,CategoryDto.class  );
    }

    @Override
    public CategoryDto updateCategory(CategoryDto category, Integer categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            // Update the category with the new data
            Category existingCategory = categoryOptional.get();
            existingCategory.setCategoryTitle(category.getCategoryTitle()); // Update other fields as needed
            existingCategory.setCategoryDescription(category.getCategoryDescription()); // Update other fields as needed
            // Update other fields as needed

            // Save the updated category
            Category updatedCategory = categoryRepository.save(existingCategory);

            return modelMapper.map(updatedCategory, CategoryDto.class);
        } else {
            // Handle the case where the category with the given ID is not found
            throw new ResourceNotFoundException("Category", "id", categoryId.toString());
        }
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", categoryId.toString()));

        // Convert the found user to a UserDto and return it
        return categoryTodto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();

        // Convert the list of User entities to a list of UserDto objects
        List<CategoryDto> categoryDtos = categories.stream()
                .map(this::categoryTodto)
                .collect(Collectors.toList());

        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            // Delete the user by ID
            categoryRepository.deleteById(categoryId);
        } else {
            // Handle the case where the user with the given ID is not found
            throw new ResourceNotFoundException("User", "id", categoryId.toString());
        }
    }
    private Category dtoToCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto,Category.class);


//    It is very Difficult to  make for many class like in this way
//
//    User userEntity = new User();
//    userEntity.setId(userDto.getId());
//    userEntity.setName(userDto.getName());
//    userEntity.setEmail(userDto.getEmail());
//    userEntity.setPassword(userDto.getPassword());
//    userEntity.setAbout(userDto.getAbout());
        // Set other properties as needed

        return category;
    }
    private CategoryDto categoryTodto(Category category) {

        CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
//
//
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        // Set other properties as needed

        return categoryDto;
    }
}
