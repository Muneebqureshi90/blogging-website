package com.example.blog.application.services;

import com.example.blog.application.entity.User;
import com.example.blog.application.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user);

//    userDto s comping for payload package to transfer data in db
    UserDto  createUser(UserDto user);
    UserDto  updateUser(UserDto user,Integer userId);
    UserDto  getUserById( Integer userId);
   List<UserDto>  getAllUsers();
   void deleteUser(Integer userId);


}
