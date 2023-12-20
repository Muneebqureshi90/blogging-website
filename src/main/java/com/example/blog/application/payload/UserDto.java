package com.example.blog.application.payload;

import com.example.blog.application.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor


//This class is used for transfer user data .,not using User ENtity

public class UserDto  {

    private Integer id;
//    EMpty will check both not null or blank
    @NotEmpty(message = "Please Fill the Name")
    @Size(min = 4,message = "Your Name Minium Characters Should Be 4")
    private String name;
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = "Incomplete Email")
    @NotNull
    @Column(unique = true)
    private String  email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$",
            message = "Password must be 6-20 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character (@$!%*?&)"
    )
    private String  password;
    @NotNull
    private String  about;
    @NotNull
    private Set<Role> roles= new HashSet<>();


    @JsonIgnore
    public String getPassword(){

        return this.password;
    }
    @JsonProperty
    public void setPassword(String password){
        this.password=password;
    }

}
