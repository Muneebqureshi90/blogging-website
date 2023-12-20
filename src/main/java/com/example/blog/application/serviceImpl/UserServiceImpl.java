package com.example.blog.application.serviceImpl;

import com.example.blog.application.configuration.AppConstants;
import com.example.blog.application.entity.Role;
import com.example.blog.application.entity.User;
import com.example.blog.application.exceptions.ResourceNotFoundException;
import com.example.blog.application.payload.UserDto;
import com.example.blog.application.repository.RoleRepository;
import com.example.blog.application.repository.UserRepository;
import com.example.blog.application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    //    Applying here modle mapper for dto class
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

//    public UserServiceImpl(UserRepository theUserRepository){
//        this.userRepository=theUserRepository;
//    }


//    Role
//    @Override
//    public UserDto registerNewUser(UserDto userDto) {
//
//      User user=  this.modelMapper.map(userDto,User.class);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        Role role = this.roleRepository.findById(AppConstants.ADMIN_USER).get();
//        user.getRoles().add(role);
//
//        User saveUser= this.userRepository.save(user);
//        return modelMapper.map(saveUser, UserDto.class);
//    }


    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assuming AppConstants.ADMIN_USER is the role's ID
        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER)
                .orElseThrow(() -> new RuntimeException("Role not found")); // Handle role not found as needed

        // Set the user's role
        user.setRole(role);

        User savedUser = this.userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

//    @Override
//    public UserDto registerNewUser(UserDto userDto) {
//        User user = this.modelMapper.map(userDto, User.class);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Assuming AppConstants.ADMIN_USER is the role's ID
//        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER)
//                .orElseThrow(() -> new RuntimeException("Role not found")); // Handle role not found as needed
//
//        // Set the user's role
//        user.setRole(role);
//
//        User savedUser = this.userRepository.save(user);
//        return modelMapper.map(savedUser, UserDto.class);
//    }

//    @Override
//    public UserDto registerNewUser(UserDto userDto) {
//        User user = this.modelMapper.map(userDto, User.class);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Assuming AppConstants.ADMIN_USER is the role's ID
//        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER)
//                .orElseThrow(() -> new RuntimeException("Role not found")); // Handle role not found as needed
//
//        // Set the user's role
//        user.setRole(role);
//
//        User savedUser = this.userRepository.save(user);
//        return modelMapper.map(savedUser, UserDto.class);
//    }
//
//    @Override
//    public UserDto registerNewUser(UserDto userDto) {
//        User user = this.modelMapper.map(userDto, User.class);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Assuming AppConstants.ADMIN_USER is the role's ID
//        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER)
//                .orElseThrow(() -> new RuntimeException("Role not found")); // Handle role not found as needed
//
//        // Set the user's role
//        user.setRole(role);
//
//        User savedUser = this.userRepository.save(user);
//        return modelMapper.map(savedUser, UserDto.class);
//    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(user);
        return modelMapper.map(saveUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        // Find the user by ID (assuming findById returns an Optional)
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            // Update the user with the new data
            User existingUser = userOptional.get();
            existingUser.setName(userDto.getName()); // Update other fields as needed
            existingUser.setEmail(userDto.getEmail()); // Update other fields as needed
            existingUser.setPassword(userDto.getPassword()); // Update other fields as needed
            existingUser.setAbout(userDto.getAbout()); // Update other fields as needed

            // Save the updated user
            User updatedUser = userRepository.save(existingUser);

            return userTodto(updatedUser);
        } else {
            // Handle the case where the user with the given ID is not found
            throw new ResourceNotFoundException("User", "id", userId.toString());
        }
    }


    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId.toString()));

        // Convert the found user to a UserDto and return it
        return userTodto(user);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        // Convert the list of User entities to a list of UserDto objects
        List<UserDto> userDtos = users.stream()
                .map(this::userTodto)
                .collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        // Check if the user exists
        if (userRepository.existsById(userId)) {
            // Delete the user by ID
            userRepository.deleteById(userId);
        } else {
            // Handle the case where the user with the given ID is not found
            throw new ResourceNotFoundException("User", "id", userId.toString());
        }
    }

    //    For Converting DtoUser to USer Entity mean getter and setters
    private User dtoToUser(UserDto userDto) {
        User userEntity = this.modelMapper.map(userDto, User.class);


//    It is very Difficult to  make for many class like in this way
//
//    User userEntity = new User();
//    userEntity.setId(userDto.getId());
//    userEntity.setName(userDto.getName());
//    userEntity.setEmail(userDto.getEmail());
//    userEntity.setPassword(userDto.getPassword());
//    userEntity.setAbout(userDto.getAbout());
        // Set other properties as needed

        return userEntity;
    }

    private UserDto userTodto(User user) {

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//
//
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        // Set other properties as needed

        return userDto;
    }
}
